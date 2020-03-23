import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform") version "1.3.70"
    id("maven-publish")
}

repositories {
    maven("https://kotlin.bintray.com/kotlinx")
    google()
    jcenter()
}

group = "com.xplatform"
version = "1.0"

kotlin {
    targetAndroid()
    targetIOS()

    sourceSets{
        commonMain{
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }
        commonTest{
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
    }
}

/*
* This should be moved to buildSrc
* */
fun org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension.targetAndroid() {
    jvm("android")

    sourceSets["androidMain"].dependencies{
        implementation("org.jetbrains.kotlin:kotlin-stdlib")
        implementation(kotlin("test"))
        implementation(kotlin("test-junit"))
    }
}

/*
* This is copied from original kotlin tutorial and needs to be refactored
* This should be moved to buildSrc
* */
fun org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension.targetIOS() {
    //select iOS target platform depending on the Xcode environment variables
    val iOSTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            ::iosArm64
        else
            ::iosX64

    iOSTarget("ios") {
        binaries {
            framework {
                baseName = "${project.name}"
            }
        }
    }
}

/*
* Don't know if this is necessary
* */
val packForXcode by tasks.creating(Sync::class) {
    val targetDir = File(buildDir, "xcode-frameworks")

    /// selecting the right configuration for the iOS
    /// framework depending on the environment
    /// variables set by Xcode build
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val framework = kotlin.targets
        .getByName<KotlinNativeTarget>("ios")
        .binaries.getFramework(mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)

    from({ framework.outputDirectory })
    into(targetDir)

    /// generate a helpful ./gradlew wrapper with embedded Java path
    doLast {
        val gradlew = File(targetDir, "gradlew")
        gradlew.writeText("#!/bin/bash\n"
                + "export 'JAVA_HOME=${System.getProperty("java.home")}'\n"
                + "cd '${rootProject.rootDir}'\n"
                + "./gradlew \$@\n")
        gradlew.setExecutable(true)
    }
}

tasks.getByName("build").dependsOn(packForXcode)