object Dependencies {
    const val buildGradle = "com.android.tools.build:gradle:${Versions.build_gradle}"
    const val kotlinVersion = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin_version}"
    const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_version}"
    const val dagger = "com.google.dagger:dagger:${Versions.dagger_version}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger_version}"
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_version}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_version}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
    const val retrofitConverterGson =
        "com.squareup.retrofit2:converter-gson:${Versions.retrofit_version}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber_version}"
    const val navigation = "androidx.navigation:navigation-fragment-ktx:${Versions.nav_version}"
    const val navigationKotlin = "androidx.navigation:navigation-ui-ktx:${Versions.nav_version}"
    const val androidxCore = "androidx.core:core-ktx:${Versions.androidx_core}"
    const val androidxAppCompat = "androidx.appcompat:appcompat:${Versions.androidx_app_compat}"
    const val androidMaterial = "com.google.android.material:material:${Versions.android_material}"
    const val constraintlayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout}"
    const val androidLegacy = "androidx.legacy:legacy-support-v4:${Versions.android_legacy}"
    const val navigationSafeArgs =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.nav_version}"
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room_version}"
    const val room = "androidx.room:room-ktx:${Versions.room_version}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room_version}"
    const val rxAndroid = "io.reactivex.rxjava3:rxandroid:${Versions.rx_android}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide_version}"
    const val glideCompile = "com.github.bumptech.glide:compiler:${Versions.glide_version}"
    const val carouselview = "com.synnapps:carouselview:${Versions.carousel_view_version}"
    const val googleServices = "com.google.gms:google-services:${Versions.google_services}"
    const val firebasePlatform = "com.google.firebase:firebase-bom:${Versions.firebase_platform}"
    const val firebaseCrashlyticsKtx = "com.google.firebase:firebase-crashlytics-ktx"
    const val firebaseAnalyticsKtx = "com.google.firebase:firebase-analytics-ktx"
    const val firebaseCrashlyticsGradle =
        "com.google.firebase:firebase-crashlytics-gradle:${Versions.firebase_crashlytics_gradle}"
}

object TestImplementationDependencies {
    const val jUnit = "junit:junit:${TestVersions.jUnit}"
    const val mockito = "org.mockito:mockito-core:${TestVersions.mockito}"
    const val nhaarmanMockitokot =
        "com.nhaarman.mockitokotlin2:mockito-kotlin:${TestVersions.nhaarman_mockito}"
    const val androidxArchTesting =
        "androidx.arch.core:core-testing:${TestVersions.androidx_arch_testing}"
    const val coroutinesTesting =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${TestVersions.coroutines_testing}"
    const val mockitoInline = "org.mockito:mockito-inline:${TestVersions.mockito_inline}"
}

object AndroidTestImplementationDependencies {
    const val junitExt = "androidx.test.ext:junit:${TestVersions.junit_ext}"
    const val espresso = "androidx.test.espresso:espresso-core:${TestVersions.espresso}"
    const val espressoContrib =
        "androidx.test.espresso:espresso-contrib:${TestVersions.espresso_contrib}"
    const val testRules = "androidx.test:rules:${TestVersions.android_implementation_test_rules}"
}

object Modules {
    const val data = ":data"
    const val domain = ":domain"
    const val requestManager = ":framework:requestmanager"
    const val databaseManager = ":framework:databasemanager"
    const val imageManager = ":framework:imagemanager"
}