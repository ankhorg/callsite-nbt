import bot.inker.bukkit.nbtbuild.ProcessJarTask
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
  id("java")
  id("com.github.johnrengelman.shadow")
  id("maven-publish")
}

allprojects {
  apply(plugin = "java")

  group = "bot.inker.bukkit"
  val buildNumber = System.getenv("GITHUB_RUN_NUMBER")
  version = if (buildNumber == null) {
    "1.1-dev-SNAPSHOT"
  } else {
    "1.1-$buildNumber"
  }

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

java {
  withSourcesJar()
  withJavadocJar()

  sourceCompatibility = JavaVersion.VERSION_1_8
  targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
  implementation("org.ow2.asm:asm:9.4")
  implementation("org.ow2.asm:asm-commons:9.4")

  compileOnly("org.jetbrains:annotations:20.1.0")
  compileOnly("org.spigotmc:spigot-api:1.16.5-R0.1-SNAPSHOT")
}

tasks.create<ProcessJarTask>("processJar") {
  addSpigotBuildInfo(
    "v1_17_R1",
    "https://launcher.mojang.com/v1/objects/f6cae1c5c1255f68ba4834b16a0da6a09621fe13/server.txt",
    "https://hub.spigotmc.org/stash/projects/SPIGOT/repos/builddata/raw/mappings/bukkit-1.17.1-cl.csrg?at=a4785704979a469daa2b7f6826c84e7fe886bb03",
    "https://hub.spigotmc.org/stash/projects/SPIGOT/repos/builddata/raw/mappings/bukkit-1.17.1-members.csrg?at=a4785704979a469daa2b7f6826c84e7fe886bb03"
  )
  addSpigotBuildInfo(
    "v1_18_R2",
    "https://launcher.mojang.com/v1/objects/e562f588fea155d96291267465dc3323bfe1551b/server.txt",
    "https://hub.spigotmc.org/stash/projects/SPIGOT/repos/builddata/raw/mappings/bukkit-1.18.2-cl.csrg?at=641cb0c939c7c2a3c4b42f2fd7bca7c8b34254ae",
    null
  )
  addSpigotBuildInfo(
    "v1_19_R1",
    "https://piston-data.mojang.com/v1/objects/ed5e6e8334ad67f5af0150beed0f3d156d74bd57/server.txt",
    "https://hub.spigotmc.org/stash/projects/SPIGOT/repos/builddata/raw/mappings/bukkit-1.19.2-cl.csrg?at=d96ad8e1e64b7c35bb632339c23621353be1f028",
    null
  )
  addSpigotBuildInfo(
    "v1_19_R3",
    "https://piston-data.mojang.com/v1/objects/73c8bb982e420b33aad9632b482608c5c33e2d13/server.txt",
    "https://hub.spigotmc.org/stash/projects/SPIGOT/repos/builddata/raw/mappings/bukkit-1.19.4-cl.csrg?at=4d9436f7b66190ad21fe4e3975b73a36b7ad2a7e",
    null
  )
  addSpigotBuildInfo(
    "v1_20_R1",
    "https://piston-data.mojang.com/v1/objects/0b4dba049482496c507b2387a73a913230ebbd76/server.txt",
    "https://hub.spigotmc.org/stash/projects/SPIGOT/repos/builddata/raw/mappings/bukkit-1.20.1-cl.csrg?at=faff587dcbe915bc565bf01f2d54c6af86039414",
    null
  )
  addSpigotBuildInfo(
    "v1_20_R2",
    "https://piston-data.mojang.com/v1/objects/dced504a9b5df367ddd3477adac084cea8024ba4/server.txt",
    "https://hub.spigotmc.org/stash/projects/SPIGOT/repos/builddata/raw/mappings/bukkit-1.20.2-cl.csrg?at=172197ceb99364701937947ea7fc424ecf1bb694",
    null
  )
  addSpigotBuildInfo(
    "v1_20_R3",
    "https://piston-data.mojang.com/v1/objects/c1cafe916dd8b58ed1fe0564fc8f786885224e62/server.txt",
    "https://hub.spigotmc.org/stash/projects/SPIGOT/repos/builddata/raw/mappings/bukkit-1.20.4-cl.csrg?at=17eb87e5cd509ff55d691b74902ac19c3b62ca0c",
    null
  )
}

tasks.javadoc {
  options.encoding = "UTF-8"
}

tasks.compileJava {
  options.encoding = "UTF-8"
}

tasks.jar {
  from(tasks["processJar"])
}

tasks.shadowJar {
  enabled = false
}

tasks.create<ShadowJar>("fatJar") {
  dependsOn(tasks.jar)
  archiveClassifier.set("fat")
  from(tasks.jar)
  configurations.add(project.configurations.runtimeClasspath.get())
  relocate("org.objectweb.asm", "bot.inker.bukkit.nbt.internal.asm")
}

tasks.assemble {
  dependsOn(tasks.shadowJar)
}

publishing {
  repositories {
    maven("https://s0.blobs.inksnow.org/maven/") {
      credentials {
        username = System.getenv("IREPO_USERNAME")
        password = System.getenv("IREPO_PASSWORD")
      }
    }
  }

  publications {
    create<MavenPublication>("mavenJar") {
      from(components["java"])
    }
    create<MavenPublication>("fatJar") {
      artifactId += "-fat"
      artifact(tasks["fatJar"]) {
        classifier = null
      }
    }
  }
}