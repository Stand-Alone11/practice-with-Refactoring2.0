import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10"
    application
}

group = "me.ms"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    // kotest setting
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.2.0")
    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.3.0")
    testImplementation("io.kotest:kotest-assertions-core:4.6.3")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

// kotest setting
tasks.withType<Test>() {
    useJUnitPlatform()
}

application {
    mainClass.set("MainKt")
}