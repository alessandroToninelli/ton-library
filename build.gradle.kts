import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
}
group = "it.toninelli"
version = "1.0.0"

repositories {
    mavenCentral()
}
dependencies {
    testImplementation(kotlin("test-junit"))
}
tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

fun getBintrayUser(): String = project.findProperty("bintrayUser").toString()
fun getBintrayApiKey(): String = project.findProperty("bintrayKey").toString()