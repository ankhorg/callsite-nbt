package bot.inker.bukkit.nbt;

import bot.inker.bukkit.nbt.loader.ref.RefNbtBase;
import bot.inker.bukkit.nbt.loader.ref.RefNbtList;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public abstract class NbtCollection<NMS extends RefNbtList<T>, T extends RefNbtBase, R extends Nbt<T>> extends Nbt<NMS> implements List<R> {
  private static final Function fromNmsFunction = it->Nbt.fromNms((RefNbtBase) it);
  private static final Function nbtRawFunction = it -> ((Nbt<?>) it).raw();

  private final MapList<T, R> mapList;

  NbtCollection(NMS delegate) {
    super(delegate);
    mapList = new MapList(delegate, fromNmsFunction, nbtRawFunction);
  }

  @Override
  public boolean add(R r) {
    return mapList.add(r);
  }

  @Override
  public R get(int index) {
    return mapList.get(index);
  }

  @Override
  public R set(int index, R element) {
    return mapList.set(index, element);
  }

  @Override
  public void add(int index, R element) {
    mapList.add(index, element);
  }

  @Override
  public R remove(int index) {
    return mapList.remove(index);
  }

  @Override
  public int size() {
    return mapList.size();
  }

  @Override
  public int indexOf(Object o) {
    return mapList.indexOf(o);
  }

  @Override
  public int lastIndexOf(Object o) {
    return mapList.lastIndexOf(o);
  }

  @Override
  public void clear() {
    mapList.clear();
  }

  @Override
  public boolean addAll(int index, Collection<? extends R> c) {
    return mapList.addAll(index, c);
  }

  @Override
  public Iterator<R> iterator() {
    return mapList.iterator();
  }

  @Override
  public ListIterator<R> listIterator() {
    return mapList.listIterator();
  }

  @Override
  public ListIterator<R> listIterator(int index) {
    return mapList.listIterator(index);
  }

  @Override
  public List<R> subList(int fromIndex, int toIndex) {
    return mapList.subList(fromIndex, toIndex);
  }

  @Override
  public boolean equals(Object o) {
    return mapList.equals(o);
  }

  @Override
  public int hashCode() {
    return mapList.hashCode();
  }

  @Override
  public boolean isEmpty() {
    return mapList.isEmpty();
  }

  @Override
  public boolean contains(Object o) {
    return mapList.contains(o);
  }

  @Override
  public Object[] toArray() {
    return mapList.toArray();
  }

  @Override
  public <T> T[] toArray(T[] a) {
    return mapList.toArray(a);
  }

  @Override
  public boolean remove(Object o) {
    return mapList.remove(o);
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return mapList.containsAll(c);
  }

  @Override
  public boolean addAll(Collection<? extends R> c) {
    return mapList.addAll(c);
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    return mapList.removeAll(c);
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    return mapList.retainAll(c);
  }

  @Override
  public String toString() {
    return mapList.toString();
  }

  @Override
  public boolean removeIf(Predicate<? super R> filter) {
    return mapList.removeIf(filter);
  }

  @Override
  public Spliterator<R> spliterator() {
    return mapList.spliterator();
  }

  @Override
  public Stream<R> stream() {
    return mapList.stream();
  }

  @Override
  public Stream<R> parallelStream() {
    return mapList.parallelStream();
  }

  @Override
  public void forEach(Consumer<? super R> action) {
    mapList.forEach(action);
  }

  @Override
  public void replaceAll(UnaryOperator<R> operator) {
    mapList.replaceAll(operator);
  }

  @Override
  public void sort(Comparator<? super R> c) {
    mapList.sort(c);
  }

  private static class MapList<T, R> extends AbstractList<R> implements List<R> {
    private final List<T> delegate;
    private final Function<T, R> t2rMapFunction;
    private final Function<R, T> r2tMapFunction;

    public MapList(List<T> delegate, Function<T, R> t2rMapFunction, Function<R, T> r2tMapFunction) {
      this.delegate = delegate;
      this.t2rMapFunction = t2rMapFunction;
      this.r2tMapFunction = r2tMapFunction;
    }

    @Override
    public boolean add(R r) {
      return delegate.add(r2tMapFunction.apply(r));
    }

    @Override
    public R get(int index) {
      return t2rMapFunction.apply(delegate.get(index));
    }

    @Override
    public R set(int index, R element) {
      return t2rMapFunction.apply(delegate.set(index, r2tMapFunction.apply(element)));
    }

    @Override
    public void add(int index, R element) {
      delegate.add(index, r2tMapFunction.apply(element));
    }

    @Override
    public R remove(int index) {
      return t2rMapFunction.apply(delegate.remove(index));
    }

    @Override
    public int size() {
      return delegate.size();
    }
  }
}
