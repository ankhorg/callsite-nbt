package bot.inker.bukkit.nbt.loader.annotation;

import org.bukkit.Bukkit;

public enum MinecraftVersion {
  v1_16_R3,
  v1_19_R3;

  private static final MinecraftVersion CURRENT = valueOf(Bukkit.getServer().getClass().getName().split("\\.")[3]);

  public static MinecraftVersion current(){
    return CURRENT;
  }

  public static HandleBy match(HandleBy[] handles){
    if(handles.length == 0){
      return null;
    }
    HandleBy currentHandle = null;
    for (HandleBy handle : handles) {
      if(handle.version().ordinal() > CURRENT.ordinal()){
        continue;
      }
      if(currentHandle == null || handle.version().ordinal() > currentHandle.version().ordinal()){
        currentHandle = handle;
      }
    }
    return currentHandle;
  }
}
