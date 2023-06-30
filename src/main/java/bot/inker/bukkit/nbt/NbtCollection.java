package bot.inker.bukkit.nbt;

import bot.inker.bukkit.nbt.api.NbtCollectionLike;
import bot.inker.bukkit.nbt.internal.loader.DelegateAbstractList;
import bot.inker.bukkit.nbt.internal.ref.RefNbtBase;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public abstract class NbtCollection<NMS extends RefNbtBase, E> extends Nbt<NMS> implements NbtCollectionLike<E> {
  private final DelegateAbstractList<E> delegateList;

  NbtCollection(NMS delegate) {
    super(delegate);
    this.delegateList = new DelegateAbstractList<>(this);
  }

  @Override
  public abstract E get(int index);

  @Override
  public abstract int size();

  @Override
  public abstract E set(int index, E element);

  @Override
  public abstract void add(int index, E element);

  @Override
  public abstract E remove(int index);

  @Override
  public final boolean add(E nbt) {
    return delegateList.add(nbt);
  }

  @Override
  public final int indexOf(Object o) {
    return delegateList.indexOf(o);
  }

  @Override
  public final int lastIndexOf(Object o) {
    return delegateList.lastIndexOf(o);
  }

  @Override
  public final void clear() {
    delegateList.clear();
  }

  @Override
  public final boolean addAll(int index, Collection<? extends E> c) {
    return delegateList.addAll(index, c);
  }

  @Override
  public final Iterator<E> iterator() {
    return delegateList.iterator();
  }

  @Override
  public final ListIterator<E> listIterator() {
    return delegateList.listIterator();
  }

  @Override
  public final ListIterator<E> listIterator(int index) {
    return delegateList.listIterator(index);
  }

  @Override
  public final List<E> subList(int fromIndex, int toIndex) {
    return delegateList.subList(fromIndex, toIndex);
  }

  @Override
  public final boolean isEmpty() {
    return delegateList.isEmpty();
  }

  @Override
  public final boolean contains(Object o) {
    return delegateList.contains(o);
  }

  @Override
  public final Object[] toArray() {
    return delegateList.toArray();
  }

  @Override
  public final <T> T[] toArray(T[] a) {
    return delegateList.toArray(a);
  }

  @Override
  public final boolean remove(Object o) {
    return delegateList.remove(o);
  }

  @Override
  public final boolean containsAll(Collection<?> c) {
    return delegateList.containsAll(c);
  }

  @Override
  public final boolean addAll(Collection<? extends E> c) {
    return delegateList.addAll(c);
  }

  @Override
  public final boolean removeAll(Collection<?> c) {
    return delegateList.removeAll(c);
  }

  @Override
  public final boolean retainAll(Collection<?> c) {
    return delegateList.retainAll(c);
  }

  @Override
  public abstract NbtCollection<NMS, E> clone();
}
