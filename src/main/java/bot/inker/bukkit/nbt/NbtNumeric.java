package bot.inker.bukkit.nbt;

import bot.inker.bukkit.nbt.api.NbtNumericLike;
import bot.inker.bukkit.nbt.internal.annotation.CbVersion;
import bot.inker.bukkit.nbt.internal.ref.RefNbtNumber;

public abstract class NbtNumeric<NMS extends RefNbtNumber> extends Nbt<NMS> implements NbtNumericLike {
  private static final boolean AS_NUMBER_SUPPORTED = CbVersion.v1_13_R1.isSupport();

  NbtNumeric(NMS delegate) {
    super(delegate);
  }

  @Override
  public long getAsLong() {
    return delegate.asLong();
  }

  @Override
  public int getAsInt() {
    return delegate.asInt();
  }

  @Override
  public short getAsShort() {
    return delegate.asShort();
  }

  @Override
  public byte getAsByte() {
    return delegate.asByte();
  }

  @Override
  public double getAsDouble() {
    return delegate.asDouble();
  }

  @Override
  public float getAsFloat() {
    return delegate.asFloat();
  }

  @Override
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

  @Override
  public abstract NbtNumeric<NMS> clone();
}
