import bot.inker.bukkit.nbtbuild.ProcessJarTask
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import proguard.gradle.ProGuardTask

plugins {
  id("java")
  id("com.github.johnrengelman.shadow")
  id("maven-publish")
}

allprojects {
  apply(plugin = "java")

  group = "bot.inker.bukkit"
  val buildNumber = System.getenv("BUILD_NUMBER")
  version = if (buildNumber == null) {
    "1.0-dev-SNAPSHOT"
  } else {
    "1.0-$buildNumber"
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

  compileOnly("org.spigotmc:spigot-api:1.16.5-R0.1-SNAPSHOT")
}

tasks.create<ProcessJarTask>("processJar") {
  addSpigotBuildInfo(
    "v1_17",
    "https://launcher.mojang.com/v1/objects/f6cae1c5c1255f68ba4834b16a0da6a09621fe13/server.txt",
    "https://hub.spigotmc.org/stash/projects/SPIGOT/repos/builddata/raw/mappings/bukkit-1.17.1-cl.csrg?at=a4785704979a469daa2b7f6826c84e7fe886bb03",
    "https://hub.spigotmc.org/stash/projects/SPIGOT/repos/builddata/raw/mappings/bukkit-1.17.1-members.csrg?at=a4785704979a469daa2b7f6826c84e7fe886bb03"
  )
  addSpigotBuildInfo(
    "v1_18",
    "https://launcher.mojang.com/v1/objects/e562f588fea155d96291267465dc3323bfe1551b/server.txt",
    "https://hub.spigotmc.org/stash/projects/SPIGOT/repos/builddata/raw/mappings/bukkit-1.18.2-cl.csrg?at=641cb0c939c7c2a3c4b42f2fd7bca7c8b34254ae",
    null
  )
  addSpigotBuildInfo(
    "v1_19",
    "https://piston-data.mojang.com/v1/objects/73c8bb982e420b33aad9632b482608c5c33e2d13/server.txt",
    "https://hub.spigotmc.org/stash/projects/SPIGOT/repos/builddata/raw/mappings/bukkit-1.19.4-cl.csrg?at=4d9436f7b66190ad21fe4e3975b73a36b7ad2a7e",
    null
  )
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

tasks.create<ProGuardTask>("proguard") {
  dependsOn(tasks["fatJar"])
  injars(tasks["fatJar"])
  outputs.file("build/tmp/proguard-opt.jar")
  outjars("build/tmp/proguard-opt.jar")
  configuration("configuration.pro")
  libraryjars(configurations.compileClasspath)
  libraryjars("${System.getProperty("java.home")}/lib/rt.jar")
  libraryjars("${System.getProperty("java.home")}/jmods/java.base.jmod")
}

tasks.create<ShadowJar>("proguardJar") {
  from(tasks["proguard"])
  archiveClassifier.set("opt")
}

tasks.assemble {
  dependsOn(tasks.shadowJar)
  dependsOn(tasks["proguardJar"])
}

publishing {
  repositories {
    if (project.version.toString().endsWith("-SNAPSHOT")) {
      maven("https://repo.inker.bot/repository/maven-snapshots/") {
        credentials {
          username = System.getenv("NEXUS_USERNAME")
          password = System.getenv("NEXUS_PASSWORD")
        }
      }
    } else {
      maven("https://repo.inker.bot/repository/maven-releases/") {
        credentials {
          username = System.getenv("NEXUS_USERNAME")
          password = System.getenv("NEXUS_PASSWORD")
        }
      }
      maven("https://s0.blobs.inksnow.org/maven/") {
        credentials {
          username = System.getenv("REPO_USERNAME")
          password = System.getenv("REPO_PASSWORD")
        }
      }
    }
  }

  publications {
    create<MavenPublication>("mavenJar") {
      from(components["java"])
    }
    create<MavenPublication>("fatJar") {
      artifactId += "-fat"
      artifact(tasks["fatJar"])
    }
    create<MavenPublication>("proguardJar") {
      artifactId += "-obf"
      artifact(tasks["proguardJar"])
    }
  }
}