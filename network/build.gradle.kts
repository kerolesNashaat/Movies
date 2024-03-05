plugins {
    `android-library`
    `kotlin-android`
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
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
    gson()
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}