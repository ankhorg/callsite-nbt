package bot.inker.bukkit.nbtbuild;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;

public class StringBufferOutputStream extends OutputStream {
  private final StringWriter writer;
  private char[] buf = new char[0];

  public StringBufferOutputStream(StringWriter writer) {
    this.writer = writer;
  }

  @Override
  public void write(int b) throws IOException {
    writer.append((char) b);
  }

  @Override
  public void write(byte[] b, int off, int len) throws IOException {
    char[] bufCopy = buf;
    if (bufCopy.length < len) {
      bufCopy = new char[len];
      buf = bufCopy;
    }
    for (int i = 0; i < len; i++) {
      bufCopy[i] = (char) b[off + i];
    }
    writer.write(bufCopy);
  }
}
