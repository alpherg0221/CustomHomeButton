buildscript {
    // Library version
    val kotlinVersion by extra("1.7.10")
    val coroutinesVersion by extra("1.6.4")
    val composeVersion by extra("1.3.0-rc01")
    val lifecycleVersion by extra("2.5.1")
    val accompanistVersion by extra("0.26.5-rc")

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.3.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.google.android.gms:oss-licenses-plugin:0.10.5")
    }
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}