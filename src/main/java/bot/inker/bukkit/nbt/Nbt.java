package bot.inker.bukkit.nbt;

import bot.inker.bukkit.nbt.loader.CallSiteNbt;
import bot.inker.bukkit.nbt.loader.ref.*;

public abstract class Nbt<NMS extends RefNbtBase> {
  static {
    if (RefNbtBase.class.getName().equals("bot.inker.bukkit.nbt.loader.ref.RefNbtBase")) {
      CallSiteNbt.Spy.throwException("CallSiteNbt loaded before CallSiteNbt installed, you should invoke CallSiteNbt#install before load it.");
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
      return NbtByte.fromNmsImpl((RefNbtTagByte) source);
    } else if (source instanceof RefNbtTagByteArray) {
      return new NbtByteArray((RefNbtTagByteArray) source);
    } else if (source instanceof RefNbtTagCompound) {
      return new NbtCompound((RefNbtTagCompound) source);
    } else if (source instanceof RefNbtTagDouble) {
      return NbtDouble.fromNmsImpl((RefNbtTagDouble) source);
    } else if (source instanceof RefNbtTagEnd) {
      return NbtEnd.INSTANCE;
    } else if (source instanceof RefNbtTagFloat) {
      return NbtFloat.fromNmsImpl((RefNbtTagFloat) source);
    } else if (source instanceof RefNbtTagInt) {
      return NbtInt.fromNmsImpl((RefNbtTagInt) source);
    } else if (source instanceof RefNbtTagIntArray) {
      return new NbtIntArray((RefNbtTagIntArray) source);
    } else if (source instanceof RefNbtTagList) {
      return new NbtList((RefNbtTagList) source);
    } else if (source instanceof RefNbtTagLong) {
      return NbtLong.fromNmsImpl((RefNbtTagLong) source);
    } else if (source instanceof RefNbtTagLongArray) {
      return new NbtLongArray((RefNbtTagLongArray) source);
    } else if (source instanceof RefNbtTagShort) {
      return NbtShort.fromNmsImpl((RefNbtTagShort) source);
    } else if (source instanceof RefNbtTagString) {
      return NbtString.fromNmsImpl((RefNbtTagString) source);
    } else {
      throw new UnsupportedOperationException("unknown source: " + source.getClass());
    }
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
