object Version {
    const val mock_web_server = "4.10.0"
    const val retrofit = "2.9.0"
    const val converter_gson = "2.9.0"
    const val coroutines = "1.6.3"
    const val okhttp = "4.10.0"
    const val core_testing = "2.1.0"
    const val mockito_inline = "4.6.0"
    const val mockito_kotlin = "2.2.0"
    const val hilt = "2.43.2"

}

object Deps {
    const val mock_web_server = "com.squareup.okhttp3:mockwebserver:${Version.mock_web_server}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
    const val converter_gson = "com.squareup.retrofit2:converter-gson:${Version.converter_gson}"
    const val coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"
    const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Version.okhttp}"
    const val core_testing = "androidx.arch.core:core-testing:${Version.core_testing}"
    const val mockito_inline = "org.mockito:mockito-inline:${Version.mockito_inline}"
    const val mockito_kotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Version.mockito_kotlin}"
    const val hilt_android = "com.google.dagger:hilt-android:${Version.hilt}"
    const val hilt_compile = "com.google.dagger:hilt-compiler:${Version.hilt}"
}
