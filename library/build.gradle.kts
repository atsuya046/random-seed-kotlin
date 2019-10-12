import org.gradle.kotlin.dsl.*

plugins {
    kotlin("multiplatform") version "1.3.50"
    id("maven-publish")
}

repositories {
    mavenCentral()
}

kotlin {
    jvm()
//    js {
//        browser {
//        }
//        nodejs {
//        }
//    }
//    // For ARM, should be changed to iosArm32 or iosArm64
//    // For Linux, should be changed to e.g. linuxX64
//    // For MacOS, should be changed to e.g. macosX64
//    // For Windows, should be changed to e.g. mingwX64
//    macosX64("macos")
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("reflect"))
                implementation(kotlin("stdlib-common"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
            }
        }
//        jsMain {
//            dependencies {
//                implementation kotlin('stdlib-js')
//            }
//        }
//        jsTest {
//            dependencies {
//                implementation kotlin('test-js')
//            }
//        }
//        macosMain {
//        }
//        macosTest {
//        }
    }
}
