import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.Properties
import java.io.FileInputStream
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.javac.resolve.classId
import java.util.Date

plugins {
    kotlin("jvm") version "1.4.10"
    id("com.jfrog.bintray") version "1.8.5"
    id("org.jetbrains.dokka") version "0.10.0"
    `maven-publish`
}

group = "com.toninelli"
version = "1.0.1"

repositories {
    mavenCentral()
}
dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.5")
    implementation("com.google.code.gson:gson:2.8.6")

    testImplementation(kotlin("test-junit"))
}
tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

val sourceJar by tasks.creating(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.getByName("main").allSource)
}


tasks {
    dokka {
        outputFormat = "html"
        outputDirectory = "$buildDir/javadoc"
    }
}

fun getBintrayUser(): String = project.findProperty("bintrayUser").toString()
fun getBintrayApiKey(): String = project.findProperty("bintrayKey").toString()


publishing {
    publications {
        create<MavenPublication>("default") {
            artifactId = project.name
            groupId = project.group.toString()
            version = project.version.toString()
            from(components["java"])
            artifact(tasks["sourceJar"])
            pom {
                name.set(rootProject.name)
                url.set("https://github.com/alessandroToninelli/ton-library")
                licenses {
                    license {
                        name.set("The Apache Software License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        name.set("Alessandro Toninelli")
                        id.set("alessandroToninelli")
                    }
                }
                scm {
                    url.set("https://github.com/alessandroToninelli/ton-library")
                }
            }
        }
    }
}

bintray {
    user = getBintrayUser()
    key = getBintrayApiKey()
    setPublications("default")
    publish = true
    pkg.apply {
        repo = "toninelli-library"
        name = rootProject.name
        setLicenses("Apache-2.0")
        vcsUrl = "https://github.com/alessandroToninelli/ton-library"
        version.apply {
            name = "${project.version}"
            desc = "Ton library"
            released = "${Date()}"
            vcsTag = project.version.toString()
        }
    }
}

