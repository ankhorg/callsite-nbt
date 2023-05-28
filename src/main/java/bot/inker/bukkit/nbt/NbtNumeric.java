package bot.inker.bukkit.nbt;

import bot.inker.bukkit.nbt.loader.annotation.CbVersion;
import bot.inker.bukkit.nbt.loader.ref.RefNbtNumber;

public abstract class NbtNumeric<NMS extends RefNbtNumber> extends Nbt<NMS> {
  private static final boolean AS_NUMBER_SUPPORTED = CbVersion.v1_16_R3.isSupport();

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
    if (AS_NUMBER_SUPPORTED) {
      return delegate.asNumber();
    } else if (this instanceof NbtByte) {
      return delegate.asByte();
    } else if (this instanceof NbtDouble) {
      return delegate.asDouble();
    } else if (this instanceof NbtFloat) {
      return delegate.asFloat();
    } else if (this instanceof NbtInt) {
      return delegate.asInt();
    } else if (this instanceof NbtLong) {
      return delegate.asLong();
    } else if (this instanceof NbtShort) {
      return delegate.asLong();
    } else {
      throw new IllegalStateException("Unknown implementation for numeric found: " + this.getClass());
    }
  }
}
