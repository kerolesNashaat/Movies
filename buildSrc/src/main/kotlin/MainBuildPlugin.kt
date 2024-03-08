import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project

private val Project.android: LibraryExtension
    get() {
        return extensions.getByType(LibraryExtension::class.java)
    }

class MainBuildPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.addPlugins()
        project.android.addAndroidSection()
    }
}

private fun Project.addPlugins() {
    this.apply {
        plugin("com.android.library")
        plugin("org.jetbrains.kotlin.android")
    }
}

private fun LibraryExtension.addAndroidSection() {
    apply {
        compileSdk = Versions.COMPILE_SDK_VERSION

        defaultConfig {
            minSdk = Versions.MIN_SDK_VERSION

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            consumerProguardFiles("consumer-rules.pro")
        }

        buildTypes {
            release {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }

        buildFeatures {
            compose = true
            buildConfig = true
        }

        buildFeatures {
            compose = true
            buildConfig = true
        }
        composeOptions {
            kotlinCompilerExtensionVersion = Versions.KOTLIN_COMPILER_EXTENSION_VERSION
        }
    }
}