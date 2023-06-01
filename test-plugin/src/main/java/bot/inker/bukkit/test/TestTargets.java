package bot.inker.bukkit.test;

import bot.inker.bukkit.nbt.*;
import com.google.common.collect.ImmutableList;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class TestTargets {
  public void run() {
    try {
      assert false;
      throw new IllegalStateException("assert is not enabled, to run test, you should add '-ea' to vm options");
    } catch (AssertionError e) {
      System.out.println();
    }
    runTest("NbtByte", this::testNbtByte);
    runTest("NbtByteArray", this::testNbtByteArray);
    runTest("NbtCompound", this::testNbtCompound);
    runTest("NbtDouble", this::testNbtDouble);
    runTest("NbtEnd", this::testNbtEnd);
    runTest("NbtFloat", this::testNbtFloat);
    runTest("NbtInt", this::testNbtInt);
    runTest("NbtIntArray", this::testNbtIntArray);
    runTest("NbtList", this::testNbtList);
    runTest("NbtLong", this::testNbtLong);
    runTest("NbtLongArray", this::testNbtLongArray);
    runTest("NbtShort", this::testNbtShort);
    runTest("NbtString", this::testNbtString);
  }

  private void runTest(String name, Runnable action) {
    System.out.println("====== start run test " + name);
    Exception e = null;
    long startTime = System.nanoTime();
    try {
      action.run();
    } catch (Exception exception) {
      e = exception;
    }
    long endTime = System.nanoTime();
    long passedTime = endTime - startTime;

    if (e == null) {
      System.out.println("====== test " + name + " passed in " + passedTime + "ns");
    } else {
      System.out.println("=== test " + name + " failed in " + passedTime + "ns");
      e.printStackTrace(System.out);
      System.out.println("====== test " + name + " report end");
    }
    System.out.println();
  }

  private void testNbtByte() {
    final byte VALUE_A = 11;
    final byte VALUE_B = 22;
    assert NbtByte.valueOf(VALUE_A).getAsByte() == VALUE_A;
    assert NbtByte.valueOf(VALUE_A).equals(NbtByte.valueOf(VALUE_A));
    assert !NbtByte.valueOf(VALUE_A).equals(NbtByte.valueOf(VALUE_B));
    assert !NbtByte.valueOf(VALUE_A).toString().isEmpty();
    assert NbtByte.valueOf(true) == NbtByte.valueOf((byte) 1);
    assert NbtByte.valueOf(VALUE_A).hashCode() == NbtByte.valueOf(VALUE_A).hashCode();
    ;
    assert NbtByte.valueOf(VALUE_A).getId() == 1;
    NbtByte instanceA = NbtByte.valueOf(VALUE_A);
    assert instanceA.clone() == instanceA;
  }

  private void testNbtByteArray() {
    final byte[] VALUE_A = new byte[]{1, 2, 3, 4};
    final List<Byte> VALUE_A_LIST = ImmutableList.of(VALUE_A[0], VALUE_A[1], VALUE_A[2], VALUE_A[3]);
    final byte[] VALUE_B = new byte[]{2, 3, 4, 5};

    assert Arrays.equals(VALUE_A, new NbtByteArray(VALUE_A).getAsByteArray());
    assert Arrays.equals(VALUE_A, new NbtByteArray(VALUE_A_LIST).getAsByteArray());
    assert new NbtByteArray(VALUE_A).equals(new NbtByteArray(VALUE_A));
    assert !new NbtByteArray(VALUE_A).equals(new NbtByteArray(VALUE_B));
    assert Arrays.equals(VALUE_A, new NbtByteArray(VALUE_A).clone().getAsByteArray());
    assert new NbtByteArray(VALUE_A).getId() == 7;
  }

  private void testNbtCompound() {
    NbtCompound compound = new NbtCompound();

    compound.set("Hello", NbtString.valueOf("World"));
    assert NbtString.valueOf("World").equals(compound.get("Hello"));
    assert compound.getAllKeys().contains("Hello");
    assert compound.getAllKeys().size() == 1;
    assert compound.size() == 1;
    assert NbtString.valueOf("World").equals(compound.clone().get("Hello"));

    Nbt oldNbtValue = compound.put("Hello", NbtString.valueOf("Minecraft"));
    assert NbtString.valueOf("World").equals(oldNbtValue);
    assert NbtString.valueOf("Minecraft").equals(compound.get("Hello"));
    compound.remove("Hello");
    assert !compound.contains("Hello");
    assert compound.isEmpty();

    compound.putByte("TestByte", (byte) 123);
    assert NbtByte.valueOf((byte) 123).equals(compound.get("TestByte"));
    assert compound.getByte("TestByte") == 123;
    assert compound.getTagType("TestByte") == 1;
    assert compound.contains("TestByte", 1);

    compound.putShort("TestShort", (short) 123);
    assert NbtShort.valueOf((short) 123).equals(compound.get("TestShort"));
    assert compound.getShort("TestShort") == 123;
    assert compound.getTagType("TestShort") == 2;
    assert compound.contains("TestShort", 2);

    compound.putInt("TestInt", 123);
    assert NbtInt.valueOf(123).equals(compound.get("TestInt"));
    assert compound.getInt("TestInt") == 123;
    assert compound.getTagType("TestInt") == 3;
    assert compound.contains("TestInt", 3);

    compound.putLong("TestLong", 123L);
    assert NbtLong.valueOf(123L).equals(compound.get("TestLong"));
    assert compound.getLong("TestLong") == 123L;
    assert compound.getTagType("TestLong") == 4;
    assert compound.contains("TestLong", 4);

    compound.putUUID("TestUUID", new UUID(123L, 234L));
    assert new UUID(123L, 234L).equals(compound.getUUID("TestUUID"));

    compound.putFloat("TestFloat", 12.3F);
    assert NbtFloat.valueOf(12.3F).equals(compound.get("TestFloat"));
    assert compound.getFloat("TestFloat") == 12.3F;
    assert compound.getTagType("TestFloat") == 5;
    assert compound.contains("TestFloat", 5);

    compound.putDouble("TestDouble", 12.3D);
    assert NbtDouble.valueOf(12.3D).equals(compound.get("TestDouble"));
    assert compound.getDouble("TestDouble") == 12.3D;
    assert compound.getTagType("TestDouble") == 6;
    assert compound.contains("TestDouble", 6);

    compound.putString("TestString", "123");
    assert NbtString.valueOf("123").equals(compound.get("TestString"));
    assert "123".equals(compound.getString("TestString"));
    assert compound.getTagType("TestString") == 8;
    assert compound.contains("TestString", 8);

    compound.putByteArray("TestByteArray", new byte[]{1, 2, 3});
    assert new NbtByteArray(new byte[]{1, 2, 3}).equals(compound.get("TestByteArray"));
    assert Arrays.equals(new byte[]{1, 2, 3}, compound.getByteArray("TestByteArray"));
    compound.putByteArray("TestByteArray2", ImmutableList.of((byte) 2, (byte) 3, (byte) 4));
    assert Arrays.equals(new byte[]{2, 3, 4}, compound.getByteArray("TestByteArray2"));
    assert compound.getTagType("TestByteArray") == 7;
    assert compound.contains("TestByteArray", 7);

    compound.putIntArray("TestIntArray", new int[]{1, 2, 3});
    assert new NbtIntArray(new int[]{1, 2, 3}).equals(compound.get("TestIntArray"));
    assert Arrays.equals(new int[]{1, 2, 3}, compound.getIntArray("TestIntArray"));
    compound.putIntArray("TestIntArray2", ImmutableList.of(2, 3, 4));
    assert Arrays.equals(new int[]{2, 3, 4}, compound.getIntArray("TestIntArray2"));
    assert compound.getTagType("TestIntArray") == 11;
    assert compound.contains("TestIntArray", 11);

    compound.putLongArray("TestLongArray", new long[]{1L, 2L, 3L});
    assert new NbtLongArray(new long[]{1L, 2L, 3L}).equals(compound.get("TestLongArray"));
    assert Arrays.equals(new long[]{1L, 2L, 3L}, compound.getLongArray("TestLongArray"));
    compound.putLongArray("TestLongArray2", ImmutableList.of(2L, 3L, 4L));
    assert Arrays.equals(new long[]{2L, 3L, 4L}, compound.getLongArray("TestLongArray2"));
    assert compound.getTagType("TestLongArray") == 12;
    assert compound.contains("TestLongArray", 12);

    NbtCompound otherCompound = new NbtCompound();
    otherCompound.putString("123", "234");
    compound.put("TestCompound", otherCompound);
    assert "234".equals(compound.getCompound("TestCompound").getString("123"));
    assert compound.getTagType("TestCompound") == 10;
    assert compound.contains("TestCompound", 10);

    NbtList otherList = new NbtList();
    otherList.add(NbtString.valueOf("123"));
    compound.put("TestList", otherList);
    assert NbtString.valueOf("123").equals(compound.getList("TestList", 8).get(0));
    assert compound.getTagType("TestList") == 9;
    assert compound.contains("TestList", 9);

    compound.putBoolean("TestBoolean", true);
    assert NbtByte.valueOf(true).equals(compound.get("TestBoolean"));
    assert compound.getBoolean("TestBoolean");
    assert compound.getTagType("TestBoolean") == 1;
  }

  private void testNbtDouble() {
    final double VALUE_A = 12.3D;
    final double VALUE_B = 23.4D;
    assert NbtDouble.valueOf(VALUE_A).getAsDouble() == VALUE_A;
    assert NbtDouble.valueOf(VALUE_A).equals(NbtDouble.valueOf(VALUE_A));
    assert !NbtDouble.valueOf(VALUE_A).equals(NbtDouble.valueOf(VALUE_B));
    assert !NbtDouble.valueOf(VALUE_A).toString().isEmpty();
    assert NbtDouble.valueOf(VALUE_A).hashCode() == NbtDouble.valueOf(VALUE_A).hashCode();
    ;
    assert NbtDouble.valueOf(VALUE_A).getId() == 6;
    NbtDouble instanceA = NbtDouble.valueOf(VALUE_A);
    assert instanceA.clone() == instanceA;
  }

  private void testNbtEnd() {
    assert NbtEnd.instance() == NbtEnd.instance().clone();
    assert NbtEnd.instance().getId() == 0;
    assert NbtEnd.instance().hashCode() == NbtEnd.instance().hashCode();
    assert NbtEnd.instance().equals(NbtEnd.instance());
  }

  private void testNbtFloat() {
    final float VALUE_A = 12.3F;
    final float VALUE_B = 23.4F;
    assert NbtFloat.valueOf(VALUE_A).getAsFloat() == VALUE_A;
    assert NbtFloat.valueOf(VALUE_A).equals(NbtFloat.valueOf(VALUE_A));
    assert !NbtFloat.valueOf(VALUE_A).equals(NbtFloat.valueOf(VALUE_B));
    assert !NbtFloat.valueOf(VALUE_A).toString().isEmpty();
    assert NbtFloat.valueOf(VALUE_A).hashCode() == NbtFloat.valueOf(VALUE_A).hashCode();
    ;
    assert NbtFloat.valueOf(VALUE_A).getId() == 5;
    NbtFloat instanceA = NbtFloat.valueOf(VALUE_A);
    assert instanceA.clone() == instanceA;
  }

  private void testNbtInt() {
    final int VALUE_A = 123;
    final int VALUE_B = 234;
    assert NbtInt.valueOf(VALUE_A).getAsInt() == VALUE_A;
    assert NbtInt.valueOf(VALUE_A).equals(NbtInt.valueOf(VALUE_A));
    assert !NbtInt.valueOf(VALUE_A).equals(NbtInt.valueOf(VALUE_B));
    assert !NbtInt.valueOf(VALUE_A).toString().isEmpty();
    assert NbtInt.valueOf(VALUE_A).hashCode() == NbtInt.valueOf(VALUE_A).hashCode();
    ;
    assert NbtInt.valueOf(VALUE_A).getId() == 3;
    NbtInt instanceA = NbtInt.valueOf(VALUE_A);
    assert instanceA.clone() == instanceA;
  }

  private void testNbtIntArray() {
    final int[] VALUE_A = new int[]{1, 2, 3, 4};
    final List<Integer> VALUE_A_LIST = ImmutableList.of(VALUE_A[0], VALUE_A[1], VALUE_A[2], VALUE_A[3]);
    final int[] VALUE_B = new int[]{2, 3, 4, 5};

    assert Arrays.equals(VALUE_A, new NbtIntArray(VALUE_A).getAsIntArray());
    assert Arrays.equals(VALUE_A, new NbtIntArray(VALUE_A_LIST).getAsIntArray());
    assert new NbtIntArray(VALUE_A).equals(new NbtIntArray(VALUE_A));
    assert !new NbtIntArray(VALUE_A).equals(new NbtIntArray(VALUE_B));
    assert Arrays.equals(VALUE_A, new NbtIntArray(VALUE_A).clone().getAsIntArray());
    assert new NbtIntArray(VALUE_A).getId() == 11;
  }

  private void testNbtList() {
    NbtList instanceA = new NbtList();
    instanceA.add(NbtInt.valueOf(123));
    assert instanceA.size() == 1;
    assert NbtInt.valueOf(123).equals(instanceA.get(0));

    instanceA.add(NbtInt.valueOf(234));
    assert instanceA.size() == 2;
    assert NbtInt.valueOf(123).equals(instanceA.get(0));
    assert NbtInt.valueOf(234).equals(instanceA.get(1));

    instanceA.add(NbtInt.valueOf(345));
    instanceA.add(1, NbtInt.valueOf(222));
    assert instanceA.size() == 4;
    assert NbtInt.valueOf(123).equals(instanceA.get(0));
    assert NbtInt.valueOf(222).equals(instanceA.get(1));
    assert NbtInt.valueOf(234).equals(instanceA.get(2));
    assert NbtInt.valueOf(345).equals(instanceA.get(3));

    NbtList instanceB = new NbtList();
    instanceB.add(NbtInt.valueOf(123));
    instanceB.add(NbtInt.valueOf(111));
    instanceB.add(NbtInt.valueOf(234));
    instanceB.add(NbtInt.valueOf(444));

    assert !instanceB.equals(instanceA);
    assert instanceA.equals(instanceA.clone());

    instanceB.add(NbtInt.valueOf(555));
    instanceB.remove(4);
    assert instanceB.size() == 4;

    instanceB.remove(NbtInt.valueOf(444));
    instanceB.add(NbtInt.valueOf(345));
    assert NbtInt.valueOf(345).equals(instanceB.get(3));

    instanceB.set(1, NbtInt.valueOf(222));
    assert NbtInt.valueOf(222).equals(instanceB.get(1));
    assert instanceB.equals(instanceA);
    assert instanceB.equals(instanceA.clone());
  }

  private void testNbtLong() {
    final long VALUE_A = 123L;
    final long VALUE_B = 234L;
    assert NbtLong.valueOf(VALUE_A).getAsLong() == VALUE_A;
    assert NbtLong.valueOf(VALUE_A).equals(NbtLong.valueOf(VALUE_A));
    assert !NbtLong.valueOf(VALUE_A).equals(NbtLong.valueOf(VALUE_B));
    assert !NbtLong.valueOf(VALUE_A).toString().isEmpty();
    assert NbtLong.valueOf(VALUE_A).hashCode() == NbtLong.valueOf(VALUE_A).hashCode();
    ;
    assert NbtLong.valueOf(VALUE_A).getId() == 4;
    NbtLong instanceA = NbtLong.valueOf(VALUE_A);
    assert instanceA.clone() == instanceA;
  }

  private void testNbtLongArray() {
    final long[] VALUE_A = new long[]{1, 2, 3, 4};
    final List<Long> VALUE_A_LIST = ImmutableList.of(VALUE_A[0], VALUE_A[1], VALUE_A[2], VALUE_A[3]);
    final long[] VALUE_B = new long[]{2, 3, 4, 5};

    assert Arrays.equals(VALUE_A, new NbtLongArray(VALUE_A).getAsLongArray());
    assert Arrays.equals(VALUE_A, new NbtLongArray(VALUE_A_LIST).getAsLongArray());
    assert new NbtLongArray(VALUE_A).equals(new NbtLongArray(VALUE_A));
    assert !new NbtLongArray(VALUE_A).equals(new NbtLongArray(VALUE_B));
    assert Arrays.equals(VALUE_A, new NbtLongArray(VALUE_A).clone().getAsLongArray());
    assert new NbtLongArray(VALUE_A).getId() == 12;
  }

  private void testNbtShort() {
    final short VALUE_A = 123;
    final short VALUE_B = 234;
    assert NbtShort.valueOf(VALUE_A).getAsShort() == VALUE_A;
    assert NbtShort.valueOf(VALUE_A).equals(NbtShort.valueOf(VALUE_A));
    assert !NbtShort.valueOf(VALUE_A).equals(NbtShort.valueOf(VALUE_B));
    assert !NbtShort.valueOf(VALUE_A).toString().isEmpty();
    assert NbtShort.valueOf(VALUE_A).hashCode() == NbtShort.valueOf(VALUE_A).hashCode();
    ;
    assert NbtShort.valueOf(VALUE_A).getId() == 2;
    NbtShort instanceA = NbtShort.valueOf(VALUE_A);
    assert instanceA.clone() == instanceA;
  }

  private void testNbtString() {
    final String VALUE_A = "123";
    final String VALUE_B = "234";
    assert VALUE_A.equals(NbtString.valueOf(VALUE_A).getAsString());
    assert NbtString.valueOf(VALUE_A).equals(NbtString.valueOf(VALUE_A));
    assert !NbtString.valueOf(VALUE_A).equals(NbtString.valueOf(VALUE_B));
    assert !NbtString.valueOf(VALUE_A).toString().isEmpty();
    assert NbtString.valueOf(VALUE_A).hashCode() == NbtString.valueOf(VALUE_A).hashCode();
    assert NbtString.valueOf(VALUE_A).getId() == 8;
    NbtString instanceA = NbtString.valueOf(VALUE_A);
    assert instanceA.clone() == instanceA;
  }
}
