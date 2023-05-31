rootProject.name = "callsite-nbt"
include("nms")
include("test-plugin")

buildscript {
  repositories {
    mavenCentral()
    gradlePluginPortal()
  }
  dependencies {
    classpath("com.github.johnrengelman:shadow:8.1.1")
    classpath("com.guardsquare:proguard-gradle:7.1.0")
  }
}