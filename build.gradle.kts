// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        gradlePluginPortal()
        google()
        maven(url = "https://jitpack.io")
        mavenCentral()
        maven(url = "https://plugins.gradle.org/m2/")
    }

    dependencies {
        classpath("com.android.tools.build:gradle:${Version.gradle}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:${Version.hilt}")
        classpath("com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:${Version.ksp}")
    }
}

plugins {
    id("com.google.devtools.ksp") version Version.ksp apply false
    kotlin("android") version Version.kotlin apply false
}

allprojects {
    repositories {
        gradlePluginPortal()
        google()
        maven(url = "https://jitpack.io")
        mavenCentral()
    }
}

val clean by tasks.registering(Delete::class) {
    delete(rootProject.buildDir)
}
