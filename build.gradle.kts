plugins {
    kotlin("jvm") version "2.3.0"
    id("com.typewritermc.module-plugin") version "2.1.0"
}

group = "dev.bn2k9"
version = "0.1"

typewriter {
    namespace = "bn2k9"

    extension {
        name = "ItemsAdder"
        shortDescription = "Add ItemsAdder support to Typewriter"
        description = "Very long and cool description that no one is ever gonna read. So why would i need to write it! And yet it isn't long enough...."
        engineVersion = "0.9.0-beta-174"
        channel = com.typewritermc.moduleplugin.ReleaseChannel.BETA

        paper {
            // Optional - If you want to make sure a plugin is required to be installed to use this extension
            dependency("ItemsAdder")
        }
    }
}

dependencies {
    compileOnly("dev.lone:api-itemsadder:4.0.10")
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.devs.beer/")
    }
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}