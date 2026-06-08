import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.tasks.compile.JavaCompile

plugins {
    id("java")
}

group = "com.redesky"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

subprojects {
    tasks.withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
    }
}

gradle.projectsEvaluated {
    subprojects {
        fun collectShadowProjectDependencies(
            startPath: String,
            visited: MutableSet<String> = mutableSetOf()
        ): Set<String> {
            if (!visited.add(startPath)) {
                return emptySet()
            }

            val startProject = project(startPath)
            val result = mutableSetOf<String>()

            if (startProject.plugins.hasPlugin("com.github.johnrengelman.shadow")) {
                result.add(startPath)
            }

            startProject.configurations.named("implementation").get()
                .dependencies
                .withType<ProjectDependency>()
                .map { it.path }
                .forEach { path ->
                    result.addAll(collectShadowProjectDependencies(path, visited))
                }

            return result
        }

        val shadowProjectDependencies = configurations.named("implementation").get()
            .dependencies
            .withType<ProjectDependency>()
            .flatMap { collectShadowProjectDependencies(it.path) }
            .toSet()

        if (shadowProjectDependencies.isEmpty()) {
            return@subprojects
        }

        tasks.withType<JavaCompile>().configureEach {
            shadowProjectDependencies.forEach { path ->
                dependsOn("$path:shadowJar")
            }
        }

        plugins.withId("com.github.johnrengelman.shadow") {
            tasks.named("shadowJar") {
                shadowProjectDependencies.forEach { path ->
                    dependsOn("$path:shadowJar")
                }
            }
        }
    }
}