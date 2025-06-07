// build.gradle.kts (raiz)

plugins {
    // Definindo as versões dos plugins mas sem aplicar
    kotlin("jvm") version "1.8.22" apply false
    kotlin("android") version "1.8.22" apply false
    id("com.android.application") version "8.1.0" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
