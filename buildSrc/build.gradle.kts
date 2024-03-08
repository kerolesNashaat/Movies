plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22")
    implementation("com.android.tools.build:gradle:8.1.3")
    implementation("com.squareup:javapoet:1.13.0")
}
