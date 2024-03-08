object Versions {
    const val ANDROID_CORE_VERSION = "1.12.0"
    const val APP_COMPAT_VERSION = "1.6.1"
    const val MATERIAL_VERSION = "1.11.0"
    const val JUNIT_VERSION = "4.13.2"
    const val EXT_JUNIT_VERSION = "1.1.5"
    const val ESPRESSO_VERSION = "3.5.1"
    const val COMPOSE_BOM_VERSION = "2024.01.00"
    const val ACTIVITY_COMPOSE_VERSION = "1.8.2"
    const val COMPILE_SDK_VERSION = 34
    const val TARGET_SDK_VERSION = 34
    const val MIN_SDK_VERSION = 24
    const val KOTLIN_COMPILER_EXTENSION_VERSION = "1.5.10"
    const val LIFECYCLE_VERSION = "2.7.0"
    const val DAGGER_HILT_VERSION = "2.50"
    const val GSON_VERSION = "2.9.0"
    const val NAVIGATION_VERSION = "2.7.7"
    const val COMPOSE_MATERIAL3_ANDROID_VERSION = "1.2.0"
    const val HILT_NAVIGATION_COMPOSE_VERSION = "1.0.0"
    const val RETROFIT_VERSION = "2.9.0"
    const val COIL_VERSION = "2.6.0"
    const val ROOM_VERSION = "2.6.1"
    const val CRYPTO_VERSION = "1.0.0"
}

object Dependencies {
    const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT_VERSION}"
    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL_VERSION}"
    const val JUNIT = "junit:junit:${Versions.JUNIT_VERSION}"
    const val EXT_JUNIT = "androidx.test.ext:junit:${Versions.EXT_JUNIT_VERSION}"
    const val ESPRESSO = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_VERSION}"
    const val COMPOSE_BOM = "androidx.compose:compose-bom:${Versions.COMPOSE_BOM_VERSION}"
    const val COMPOSE_UI = "androidx.compose.ui:ui"
    const val COMPOSE_UI_GRAPHICS = "androidx.compose.ui:ui-graphics"
    const val COMPOSE_UI_TOOLING = "androidx.compose.ui:ui-tooling"
    const val COMPOSE_UI_TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview"
    const val COMPOSE_MATERIAL3_ANDROID =
        "androidx.compose.material3:material3-android:${Versions.COMPOSE_MATERIAL3_ANDROID_VERSION}"
    const val COMPOSE_UI_TEST_MANIFEST = "androidx.compose.ui:ui-test-manifest"

    const val ACTIVITY_COMPOSE =
        "androidx.activity:activity-compose:${Versions.ACTIVITY_COMPOSE_VERSION}"
    const val COMPOSE_UI_TEST_JUNIT4 = "androidx.compose.ui:ui-test-junit4"
    const val LIFECYCLE = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE_VERSION}"
    const val DAGGER_HILT = "com.google.dagger:hilt-android:${Versions.DAGGER_HILT_VERSION}"
    const val DAGGER_HILT_COMPILIER =
        "com.google.dagger:hilt-android-compiler:${Versions.DAGGER_HILT_VERSION}"
    const val HILT_NAVIGATION_COMPOSE =
        "androidx.hilt:hilt-navigation-compose:${Versions.HILT_NAVIGATION_COMPOSE_VERSION}"
    const val LIFECYCLE_VIEWMODEL_COMPOSE = "androidx.lifecycle:lifecycle-viewmodel-compose"
    const val LIFECYCLE_RUNTIME_COMPOSE =
        "androidx.lifecycle:lifecycle-runtime-compose:${Versions.LIFECYCLE_VERSION}"
    const val NAVIGATION_COMPOSE =
        "androidx.navigation:navigation-compose:${Versions.NAVIGATION_VERSION}"
    const val NAVIGATION_FRAGMENT =
        "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION_VERSION}"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION_VERSION}"
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT_VERSION}"
    const val GSON = "com.squareup.retrofit2:converter-gson:${Versions.GSON_VERSION}"
    const val COIL = "io.coil-kt:coil-compose:${Versions.COIL_VERSION}"
    const val ROOM_PAGING = "androidx.room:room-paging:${Versions.ROOM_VERSION}"
    const val ROOM_TESTING = "androidx.room:room-testing:${Versions.ROOM_VERSION}"
    const val ROOM_GUAVA = "androidx.room:room-guava:${Versions.ROOM_VERSION}"
    const val ROOM_KTX = "androidx.room:room-ktx:${Versions.ROOM_VERSION}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${Versions.ROOM_VERSION}"
    const val ROOM_RUNTIME = "androidx.room:room-runtime:${Versions.ROOM_VERSION}"
    const val ANDROID_CORE = "androidx.core:core-ktx:${Versions.ANDROID_CORE_VERSION}"
    const val CRYPTO = "androidx.security:security-crypto:${Versions.CRYPTO_VERSION}"
}

