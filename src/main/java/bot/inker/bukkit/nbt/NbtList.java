package bot.inker.bukkit.nbt;

import bot.inker.bukkit.nbt.loader.annotation.CbVersion;
import bot.inker.bukkit.nbt.loader.ref.RefNbtBase;
import bot.inker.bukkit.nbt.loader.ref.RefNbtTagEnd;
import bot.inker.bukkit.nbt.loader.ref.RefNbtTagList;

import java.util.*;

public final class NbtList extends Nbt<RefNbtTagList> implements List<Nbt<?>> {
  private static boolean THROW_INDEX_SUPPORT = CbVersion.v1_16_R3.isSupport();
  private static boolean RETURN_SET_SUPPORT = CbVersion.v1_16_R3.isSupport();
  private static boolean INDEX_ADD_SUPPORT = CbVersion.v1_16_R3.isSupport();

  private final SimpleAbstractList list = new SimpleAbstractList();

  NbtList(RefNbtTagList delegate) {
    super(delegate);
  }

  public NbtList() {
    super(new RefNbtTagList());
  }

  private Nbt<?> nms2nbt(RefNbtBase nms) {
    return Nbt.fromNms(nms);
  }

  private RefNbtBase nbt2nms(Nbt<?> nbt) {
    return nbt == null ? null : nbt.delegate;
  }

  @Override
  public Nbt<RefNbtTagList> clone() {
    return new NbtList(cloneNms());
  }

  @Override
  public boolean add(Nbt<?> nbt) {
    return list.add(nbt);
  }

  @Override
  public Nbt<?> get(int index) {
    return list.get(index);
  }

  @Override
  public Nbt<?> set(int index, Nbt<?> element) {
    return list.set(index, element);
  }

  @Override
  public void add(int index, Nbt<?> element) {
    list.add(index, element);
  }

  @Override
  public Nbt<?> remove(int index) {
    return list.remove(index);
  }

  @Override
  public int indexOf(Object o) {
    return list.indexOf(o);
  }

  @Override
  public int lastIndexOf(Object o) {
    return list.lastIndexOf(o);
  }

  @Override
  public void clear() {
    list.clear();
  }

  @Override
  public boolean addAll(int index, Collection<? extends Nbt<?>> c) {
    return list.addAll(index, c);
  }

  @Override
  public Iterator<Nbt<?>> iterator() {
    return list.iterator();
  }

  @Override
  public ListIterator<Nbt<?>> listIterator() {
    return list.listIterator();
  }

  @Override
  public ListIterator<Nbt<?>> listIterator(int index) {
    return list.listIterator(index);
  }

  @Override
  public List<Nbt<?>> subList(int fromIndex, int toIndex) {
    return list.subList(fromIndex, toIndex);
  }

  @Override
  public int size() {
    return list.size();
  }

  @Override
  public boolean isEmpty() {
    return list.isEmpty();
  }

  @Override
  public boolean contains(Object o) {
    return list.contains(o);
  }

  @Override
  public Object[] toArray() {
    return list.toArray();
  }

  @Override
  public <T> T[] toArray(T[] a) {
    return list.toArray(a);
  }

  @Override
  public boolean remove(Object o) {
    return list.remove(o);
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return list.containsAll(c);
  }

  @Override
  public boolean addAll(Collection<? extends Nbt<?>> c) {
    return list.addAll(c);
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    return list.removeAll(c);
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    return list.retainAll(c);
  }

  private final class SimpleAbstractList extends AbstractList<Nbt<?>> {
    @Override
    public Nbt<?> get(int index) {
      RefNbtBase result = delegate.get(index);
      if (THROW_INDEX_SUPPORT && result instanceof RefNbtTagEnd && (index < 0 || index >= delegate.size())) {
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + delegate.size());
      }
      return nms2nbt(result);
    }

    @Override
    public int size() {
      return delegate.size();
    }

    @Override
    public Nbt<?> set(int index, Nbt<?> element) {
      if (RETURN_SET_SUPPORT) {
        return nms2nbt(delegate.set1(index, nbt2nms(element)));
      } else if (index >= 0 && index < delegate.size()) {
        Nbt<?> previous = nms2nbt(delegate.get(index));
        delegate.set0(index, nbt2nms(element));
        return previous;
      } else {
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + delegate.size());
      }
    }

    @Override
    public void add(int index, Nbt<?> element) {
      if (INDEX_ADD_SUPPORT) {
        delegate.add1(index, nbt2nms(element));
      } else {
        int addedIndex = delegate.size();
        if (index > addedIndex) {
          throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + delegate.size());
        }
        RefNbtBase nms = nbt2nms(element);
        delegate.add0(nms);
        if (index == addedIndex) {
          return;
        }
        if (RETURN_SET_SUPPORT) {
          for (int i = index; i < addedIndex; i++) {
            nms = delegate.set1(i, nms);
          }
          delegate.set1(addedIndex, nms);
        } else {
          for (int i = index; i < addedIndex; i++) {
            RefNbtBase oldValue = delegate.get(i);
            delegate.set0(i, nms);
            nms = oldValue;
          }
          delegate.set0(addedIndex, nms);
        }
      }
    }

    @Override
    public Nbt<?> remove(int index) {
      return nms2nbt(delegate.remove(index));
    }
  }
}
