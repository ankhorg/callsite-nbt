plugins {
  id("java")
  id("com.github.johnrengelman.shadow") version "7.1.2"
}

allprojects {
  apply(plugin = "java")

  group = "org.inksnow"
  version = "1.0-SNAPSHOT"

  java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
}

repositories {
  mavenCentral()
  maven("https://oss.sonatype.org/content/repositories/snapshots")
  maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots")
}

dependencies {
  implementation("org.ow2.asm:asm:9.4")
  implementation("org.ow2.asm:asm-commons:9.4")

  compileOnly("org.spigotmc:spigot-api:1.16.5-R0.1-SNAPSHOT")
}

tasks.shadowJar {
  relocate("org.objectweb.asm", "bot.inker.bukkit.nbt.loader.asm")
}

tasks.assemble {
  dependsOn(tasks.shadowJar)
}