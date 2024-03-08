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
}

dependencies {
    test()
    core()
    appcompat()
    material()
    hilt()
    retrofit()
    crypto()
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}