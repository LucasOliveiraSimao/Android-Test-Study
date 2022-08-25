object Version {
    const val mock_web_server = "4.10.0"
    const val retrofit = "2.9.0"
    const val converter_gson = "2.9.0"
    const val coroutines = "1.6.3"
    const val okhttp = "4.10.0"
}

object Deps {
    const val mock_web_server = "com.squareup.okhttp3:mockwebserver:${Version.mock_web_server}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
    const val converter_gson = "com.squareup.retrofit2:converter-gson:${Version.converter_gson}"
    const val coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"
    const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Version.okhttp}"
}
