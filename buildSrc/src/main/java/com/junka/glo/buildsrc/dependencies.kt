package com.junka.glo.buildsrc

object Libs{

    const val androidGradlePlugin = "com.android.tools.build:gradle:7.2.0"
    const val gradleVersionsPlugin = "com.github.ben-manes:gradle-versions-plugin:0.42.0"

    object Kotlin {
        private const val version = "1.6.21"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"

        object Coroutines {
            private const val version = "1.6.1"
            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        }
    }

    object AndroidX {

        const val coreKtx = "androidx.core:core-ktx:1.8.0"
        const val appCompat = "androidx.appcompat:appcompat:1.4.1"
        const val recyclerView = "androidx.recyclerview:recyclerview:1.2.1"
        const val material = "com.google.android.material:material:1.7.0-alpha02"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.3"

        object Activity {
            private const val version = "1.4.0"
            const val ktx = "androidx.activity:activity-ktx:1.4.0"
        }

        object Lifecycle {
            private const val version = "2.4.1"
            const val viewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
        }

        object Navigation {
            private const val version = "2.4.2"
            const val fragmentKtx = "androidx.navigation:navigation-fragment-ktx:$version"
            const val uiKtx = "androidx.navigation:navigation-ui-ktx:$version"
            const val gradlePlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
        }

        object Room {
            private const val version = "2.4.2"
            const val runtime = "androidx.room:room-runtime:$version"
            const val ktx = "androidx.room:room-ktx:$version"
            const val compiler = "androidx.room:room-compiler:$version"
        }

        object Test {
            object Ext {
                private const val version = "1.1.3"
                const val junit = "androidx.test.ext:junit:$version"
            }
            object Espresso{
                private const val version="3.4.0"
                const val core = "androidx.test.espresso:espresso-core:$version"
            }
        }
    }

    object Coil {
        private const val version = "2.1.0"
        const val coil = "io.coil-kt:coil:$version"
    }

    object OkHttp3 {
        private const val version = "4.9.3"
        const val loginInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
    }

    object Retrofit {
        private const val version = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val converterMoshi = "com.squareup.retrofit2:converter-moshi:$version"
    }

    object Moshi{
        private const val version = "1.13.0"
        const val moshi = "com.squareup.moshi:moshi-kotlin:$version"
    }

    object Arrow {
        private const val version = "1.1.2"
        const val core = "io.arrow-kt:arrow-core:$version"
    }

    object Hilt {
        private const val version = "2.42"
        const val android = "com.google.dagger:hilt-android:$version"
        const val compiler = "com.google.dagger:hilt-compiler:$version"
        const val gradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
        const val work = "androidx.hilt:hilt-work:1.0.0"
        const val androidCompiler = "androidx.hilt:hilt-compiler:1.0.0"
    }

    object JUnit {
        private const val version = "4.13.2"
        const val junit = "junit:junit:$version"
    }

    object Mockito {
        const val kotlin = "org.mockito.kotlin:mockito-kotlin:4.0.0"
        const val inline = "org.mockito:mockito-inline:4.4.0"
    }

    object JavaX {
        const val inject = "javax.inject:javax.inject:1"
    }
}