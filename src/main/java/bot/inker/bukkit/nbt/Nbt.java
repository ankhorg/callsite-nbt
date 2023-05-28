package bot.inker.bukkit.nbt;

import bot.inker.bukkit.nbt.loader.ref.RefNbtBase;
import bot.inker.bukkit.nbt.loader.ref.RefNbtTagByte;

public abstract class Nbt<NMS extends RefNbtBase> {
  static {
    if (RefNbtBase.class.getName().equals("bot.inker.bukkit.nbt.loader.ref.RefNbtBase")) {
      throw new IllegalStateException("CallSiteNbt loaded before CallSiteNbt installed, " +
          "you should invoke CallSiteNbt#install before load it.");
    }
  }

  protected final NMS delegate;

  Nbt(NMS delegate) {
    this.delegate = delegate;
  }

  @SuppressWarnings("unchecked")
  static <T extends RefNbtBase> Nbt<T> fromNms(T source) {
    return (Nbt<T>) fromNmsImpl(source);
  }

  private static Nbt<?> fromNmsImpl(RefNbtBase source) {
    if (source == null) {
      return null;
    } else if (source instanceof RefNbtTagByte) {
      return NbtByte.valueOf(((RefNbtTagByte) source).asByte());
    }
    throw new UnsupportedOperationException("unknown source: " + source.getClass());
  }

  public byte getId() {
    return delegate.getTypeId();
  }

  @Override
  public String toString() {
    return delegate.toString();
  }

  @Override
  public int hashCode() {
    return delegate.hashCode();
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof Nbt && delegate.equals(((Nbt<?>) o).delegate);
  }

  @Override
  public abstract Nbt<NMS> clone();

  @SuppressWarnings("unchecked")
  protected NMS cloneNms() {
    return (NMS) delegate.rClone();
  }
}
