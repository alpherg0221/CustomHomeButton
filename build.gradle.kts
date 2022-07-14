buildscript {
    // Library version
    val kotlinVersion by extra("1.7.0")
    val coroutinesVersion by extra("1.6.4")
    val composeVersion by extra("1.3.0-alpha01")
    val lifecycleVersion by extra("2.5.0")
    val accompanistVersion by extra("0.24.13-rc")

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.2.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.google.android.gms:oss-licenses-plugin:0.10.5")
    }
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}