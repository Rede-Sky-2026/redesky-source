plugins {
        java
        id("com.github.johnrengelman.shadow") version "8.1.1"
        }

        group = "dev.slickcollections.kiwizin"
        version = "1.9"

        val protocollibVersion = "4.5.0"
        val placeholderApiVersion = "2.10.5"

        java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
        }

        repositories {
        // Spigot
        maven {
        name = "nms-repo"
        url = uri("https://repo.codemc.org/repository/nms/")
        }

        // ProtocolLib
        maven {
        name = "dmulloy2-repo"
        url = uri("https://repo.dmulloy2.net/nexus/repository/public/")
        }

        // PlaceholderAPI
        maven {
        name = "placeholderapi"
        url = uri("http://repo.extendedclip.com/content/repositories/placeholderapi/")
        isAllowInsecureProtocol = true
        }

        mavenCentral()
        }

        dependencies {
        implementation("io.socket:socket.io-client:1.0.0")

        implementation("org.mariadb.jdbc:mariadb-java-client:2.6.0")

        implementation("com.zaxxer:HikariCP:3.4.5")

        implementation("org.mongodb:mongodb-driver-sync:4.0.5")

        compileOnly("org.spigotmc:spigot:1.8.8-R0.1-SNAPSHOT")

        // BungeeCord local
        compileOnly(files("C:/Users/guilherme/Desktop/Rede Sky 2026/RedeSky/libs/Zartema.jar"))

                compileOnly(files("C:/Users/guilherme/Desktop/Rede Sky 2026/RedeSky/libs/ProtocolLib.jar"))

                compileOnly(files("C:/Users/guilherme/Desktop/Rede Sky 2026/RedeSky/libs/PlaceholderAPI.jar"))
        }

        tasks {
        processResources {
        filteringCharset = "UTF-8"
        }

        shadowJar {
        archiveClassifier.set("")

        dependencies {
        exclude(dependency("org.spigotmc:.*"))
        exclude(dependency("net.md_5:.*"))
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