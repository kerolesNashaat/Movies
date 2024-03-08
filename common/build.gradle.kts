plugins {
    `android-library`
    `kotlin-android`
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

apply<MainBuildPlugin>()

android {
    namespace = "com.kirollos.common"
}

dependencies {
    test()
    core()
    appcompat()
    material()
    hilt()
    compose()
    coil()
}

kapt {
    correctErrorTypes = true
}