package bot.inker.bukkit.nbt;

import bot.inker.bukkit.nbt.loader.ref.RefNbtBase;
import bot.inker.bukkit.nbt.loader.ref.RefNbtTagList;

public final class NbtList extends NbtCollection<RefNbtTagList, RefNbtBase, Nbt<RefNbtBase>> {
  NbtList(RefNbtTagList delegate) {
    super(delegate);
  }

  public NbtList(){
    super(new RefNbtTagList());
  }

  @Override
  public Nbt<RefNbtTagList> clone() {
    return new NbtList(cloneNms());
  }
}
