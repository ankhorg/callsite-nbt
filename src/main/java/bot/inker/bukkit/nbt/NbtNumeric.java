package bot.inker.bukkit.nbt;

import bot.inker.bukkit.nbt.loader.ref.RefNbtNumber;

public abstract class NbtNumeric<NMS extends RefNbtNumber> extends Nbt<NMS> {
  NbtNumeric(NMS delegate) {
    super(delegate);
  }

  public long getAsLong() {
    return delegate.asLong();
  }

  public int getAsInt() {
    return delegate.asInt();
  }

  public short getAsShort() {
    return delegate.asShort();
  }

  public byte getAsByte() {
    return delegate.asByte();
  }

  public double getAsDouble() {
    return delegate.asDouble();
  }

  public float getAsFloat() {
    return delegate.asFloat();
  }

  public Number getAsNumber() {
    return delegate.asNumber();
  }
}
