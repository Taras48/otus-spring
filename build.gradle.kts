import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    java
    kotlin("jvm") version "1.7.10"
    id("org.jetbrains.kotlin.plugin.spring") version "1.7.10"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.7.10"
    application
}

group = "org.otus-spring"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    //Spring
    implementation("org.springframework:spring-context:5.3.24")

    // Other
    implementation("com.opencsv:opencsv:5.7.1")

    //Test
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}