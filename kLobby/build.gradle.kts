plugins {
        java
        id("com.github.johnrengelman.shadow") version "8.1.1"
        }

        group = "dev.slickcollections.kiwizin.lobby"
        version = "1.2"

        val protocollibVersion = "4.5.0"
        val placeholderApiVersion = "2.10.5"

        java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
        }

        repositories {
        maven {
        name = "nms-repo"
        url = uri("https://repo.codemc.org/repository/nms/")
        }

        maven {
        name = "dmulloy2-repo"
        url = uri("https://repo.dmulloy2.net/nexus/repository/public/")
        }

        maven {
        name = "placeholderapi"
        url = uri("http://repo.extendedclip.com/content/repositories/placeholderapi/")
        isAllowInsecureProtocol = true
        }

        mavenCentral()
        }

        dependencies {
        implementation(project(":kCore"))

        compileOnly("org.spigotmc:spigot:1.8.8-R0.1-SNAPSHOT")

                compileOnly(files("../libs/ProtocolLib.jar"))
                compileOnly(files("../libs/PlaceholderAPI.jar"))
        }

        tasks {
        processResources {
        filteringCharset = "UTF-8"
        }

        shadowJar {
        archiveClassifier.set("")

        dependencies {
        exclude(dependency("org.spigotmc:.*"))
        exclude(dependency("cglib:.*"))
        exclude(dependency("com.comphenix.protocol:.*"))
        exclude(dependency("me.clip:.*"))
        }

        exclude("META-INF/**")
        }

        build {
        dependsOn(shadowJar)
        }
        }