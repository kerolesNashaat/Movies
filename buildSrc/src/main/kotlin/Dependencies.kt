import org.gradle.kotlin.dsl.DependencyHandlerScope

object Configuration {
    const val IMPLEMENTATION = "implementation"
    const val TEST_IMPLEMENTATION = "testImplementation"
    const val ANDROID_TEST_IMPLEMENTATION = "androidTestImplementation"
    const val DEBUG_IMPLEMENTATION = "debugImplementation"
    const val KAPT = "kapt"

}

fun DependencyHandlerScope.test() {
    add(Configuration.TEST_IMPLEMENTATION, Dependencies.JUNIT)
    add(Configuration.ANDROID_TEST_IMPLEMENTATION, Dependencies.EXT_JUNIT)
    add(Configuration.ANDROID_TEST_IMPLEMENTATION, Dependencies.ESPRESSO)
}

fun DependencyHandlerScope.core() {
    add(Configuration.IMPLEMENTATION, Dependencies.ANDROID_CORE)
    add(Configuration.IMPLEMENTATION, Dependencies.LIFECYCLE)
}

fun DependencyHandlerScope.appcompat() {
    add(Configuration.IMPLEMENTATION, Dependencies.APP_COMPAT)
}

fun DependencyHandlerScope.material() {
    add(Configuration.IMPLEMENTATION, Dependencies.MATERIAL)
}

fun DependencyHandlerScope.compose() {
    add(Configuration.IMPLEMENTATION, Dependencies.ACTIVITY_COMPOSE)
    add(Configuration.IMPLEMENTATION, platform(Dependencies.COMPOSE_BOM))
    add(Configuration.IMPLEMENTATION, Dependencies.COMPOSE_UI)
    add(Configuration.IMPLEMENTATION, Dependencies.COMPOSE_UI_GRAPHICS)
    add(Configuration.IMPLEMENTATION, Dependencies.COMPOSE_UI_TOOLING_PREVIEW)
    add(Configuration.IMPLEMENTATION, Dependencies.COMPOSE_MATERIAL3_ANDROID)
    add(Configuration.ANDROID_TEST_IMPLEMENTATION, Dependencies.COMPOSE_BOM)
    add(Configuration.DEBUG_IMPLEMENTATION, Dependencies.COMPOSE_UI_TOOLING)
    add(Configuration.DEBUG_IMPLEMENTATION, Dependencies.COMPOSE_UI_TEST_MANIFEST)
    add(Configuration.ANDROID_TEST_IMPLEMENTATION, Dependencies.COMPOSE_UI_TEST_JUNIT4)
    add(Configuration.IMPLEMENTATION, Dependencies.LIFECYCLE_VIEWMODEL_COMPOSE)
    add(Configuration.IMPLEMENTATION, Dependencies.LIFECYCLE_RUNTIME_COMPOSE)
}

fun DependencyHandlerScope.hilt() {
    add(Configuration.IMPLEMENTATION, Dependencies.DAGGER_HILT)
    add(Configuration.KAPT, Dependencies.DAGGER_HILT_COMPILIER)
    add(Configuration.IMPLEMENTATION, Dependencies.HILT_NAVIGATION_COMPOSE)
}

fun DependencyHandlerScope.gson() {
    add(Configuration.IMPLEMENTATION, Dependencies.GSON)
}

fun DependencyHandlerScope.navigation() {
    add(Configuration.IMPLEMENTATION, Dependencies.NAVIGATION_COMPOSE)
    add(Configuration.IMPLEMENTATION, Dependencies.NAVIGATION_FRAGMENT)
    add(Configuration.IMPLEMENTATION, Dependencies.NAVIGATION_UI)
}