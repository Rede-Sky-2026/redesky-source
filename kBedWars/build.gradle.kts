plugins {
        java
        id("com.github.johnrengelman.shadow") version "8.1.1"
        }

        group = "dev.slickcollections.kiwizin.bedwars"
        version = "2.1"

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
        implementation(project(":kMysteryBox"))
        implementation(project(":kCosmetics"))

        compileOnly("org.spigotmc:spigot:1.8.8-R0.1-SNAPSHOT")

                compileOnly(files("C:/Users/guilherme/Desktop/Rede Sky 2026/RedeSky/libs/ProtocolLib.jar"))

        compileOnly(files("C:/Users/guilherme/Desktop/Rede Sky 2026/RedeSky/libs/PlaceholderAPI.jar"))
        }

        tasks {
        processResources {
        filteringCharset = "UTF-8"
        }

        shadowJar {
        archiveClassifier.set("")

        destinationDirectory.set(
        file("C:/Users/SuaMae/Documents/localpost/plugins")
        )

        archiveFileName.set("${project.name}.jar")

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