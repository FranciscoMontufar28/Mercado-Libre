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
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout}"
    const val androidLegacy = "androidx.legacy:legacy-support-v4:${Versions.android_legacy}"
    const val navigationSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.nav_version}"
}

object TestDependencies {
    const val jUnit = "junit:junit:${TestVersions.jUnit}"
    const val junitExt = "androidx.test.ext:junit:${TestVersions.junit_ext}"
    const val espresso = "androidx.test.espresso:espresso-core:${TestVersions.espresso}"
}