import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    java
    application

    kotlin("jvm") version "1.7.10"
    id("org.jetbrains.kotlin.plugin.spring") version "1.7.10"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.7.10"

    id("org.springframework.boot") version "2.6.13"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "org.otus-spring"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    //Kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    //Spring
    implementation("org.springframework:spring-context:5.3.24")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // Other
    implementation("com.opencsv:opencsv:5.7.1")

    //Test
    testImplementation(kotlin("test"))
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
}

application {
    mainClass.set("ApplicationKt")
}