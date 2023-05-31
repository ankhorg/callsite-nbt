package bot.inker.bukkit.nbtbuild;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.CacheableTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.OutputDirectory;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;

@CacheableTask
public class ProcessJarTask extends DefaultTask {
  private List<SpigotBuildInfo> spigotBuildInfo = new ArrayList<>();
  private File outputClassRoot;

  public ProcessJarTask() {
    outputClassRoot = new File(getProject().getBuildDir(), "tmp/mappings");

    doLast(it -> {
      try {
        DevMain.run(spigotBuildInfo, outputClassRoot);
      } catch (IOException e) {
        throw new UncheckedIOException(e);
      }
    });
  }

  public void addSpigotBuildInfo(String craftBukkitVersion, String mojang, String bukkitCl, String bukkitMembers) {
    spigotBuildInfo.add(new SpigotBuildInfo(craftBukkitVersion, mojang, bukkitCl, bukkitMembers));
  }

  @Input
  public List<SpigotBuildInfo> getSpigotBuildInfo() {
    return spigotBuildInfo;
  }

  @OutputDirectory
  public File getOutputClassRoot() {
    return outputClassRoot;
  }
}
