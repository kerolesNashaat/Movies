import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    `android-library`
    `kotlin-android`
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    `kotlin-parcelize`
}
apply<MainBuildPlugin>()

android {
    namespace = "com.kirollos.network"
    defaultConfig {
        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())

        buildConfigField("String", "API_KEY", properties["API_KEY"].toString())
    }
}

dependencies {
    test()
    core()
    appcompat()
    material()
    hilt()
    retrofit()
    crypto()
    room()
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}