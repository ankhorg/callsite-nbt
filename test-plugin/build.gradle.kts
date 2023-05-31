plugins {
  id("com.github.johnrengelman.shadow")
}

repositories {
  mavenCentral()
  maven("https://oss.sonatype.org/content/repositories/snapshots")
  maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots")
}

dependencies {
  implementation(rootProject.tasks["proguardJar"].outputs.files)
  compileOnly("org.spigotmc:spigot-api:1.16.5-R0.1-SNAPSHOT")
}

tasks.shadowJar {
  dependsOn(project(":").tasks["proguardJar"])
  // relocate("bot.inker.bukkit.nbt", "bot.inker.bukkit.test.libs.nbt")
}

tasks.assemble {
  dependsOn(tasks.shadowJar)
}