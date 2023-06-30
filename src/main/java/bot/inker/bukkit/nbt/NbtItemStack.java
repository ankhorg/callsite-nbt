package bot.inker.bukkit.nbt;

import bot.inker.bukkit.nbt.api.NbtComponentLike;
import bot.inker.bukkit.nbt.internal.loader.CallSiteInstaller;
import bot.inker.bukkit.nbt.internal.loader.DelegateAbstractMap;
import bot.inker.bukkit.nbt.internal.ref.RefBukkitItemStack;
import bot.inker.bukkit.nbt.internal.ref.RefCraftMetaItem;
import bot.inker.bukkit.nbt.internal.ref.RefNbtBase;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

public class NbtItemStack implements NbtComponentLike {
  private static final Set<String> HANDLED_TAGS;
  private static final Map<String, Class<? extends HandleByEntry>> HANDLED_MAP;
  private static final Map<String, HandleByEntry> HANDLED_ENTRIES;
  private static final Function<Map.Entry<String, ?>, String> ENTRY_GET_KEY = Entry::getKey;
  private static final Function<RefNbtBase, Nbt<?>> NBT_FROM_NMS = Nbt::fromNms;

  private final Map<String, Nbt<?>> delegateMap;
  private final ItemStack itemStack;
  private final ItemMeta itemMeta;
  private final RefCraftMetaItem refItemMeta;
  private Set<String> keySet;
  private Set<Map.Entry<String, Nbt<?>>> entrySet;

  static {
    HANDLED_TAGS = RefCraftMetaItem.HANDLED_TAGS();

    HANDLED_MAP = ImmutableMap.<String, Class<? extends HandleByEntry>>builder()
        .put("Name", DisplayEntry.class)
        .build();

    HANDLED_ENTRIES = new LinkedHashMap<>();
    for (String tag : HANDLED_TAGS) {
      Class<? extends HandleByEntry> handleClass = HANDLED_MAP.get(tag);
      if(handleClass != null) {
        try {
          HANDLED_ENTRIES.put(tag, handleClass.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
          CallSiteInstaller.Spy.throwException(e);
        }
      }else{
        HANDLED_ENTRIES.put(tag, new UnsupportedEntry(tag));
        CallSiteInstaller.Spy.info("Failed to register meta handle for " + tag);
      }
    }
  }

  public NbtItemStack(ItemStack itemStack) {
    this.itemStack = itemStack;
    this.delegateMap = new DelegateAbstractMap<>(this);
    RefBukkitItemStack refItemStack = (RefBukkitItemStack) (Object) itemStack;
    ItemMeta meta = refItemStack.handle();
    if (meta == null) {
      meta = itemStack.getItemMeta();
      refItemStack.handle(meta);
    }
    itemMeta = meta;
    refItemMeta = (RefCraftMetaItem) (Object) meta;
  }

  @Override
  public Nbt<?> get(String key) {
    HandleByEntry handleByEntry = HANDLED_ENTRIES.get(key);
    if (handleByEntry == null) {
      return Nbt.fromNms(refItemMeta.unhandledTags().get(key));
    }else{
      return handleByEntry.getValue(itemMeta);
    }
  }

  @Override
  public boolean containsKey(String key) {
    HandleByEntry handleByEntry = HANDLED_ENTRIES.get(key);
    if (handleByEntry == null) {
      return refItemMeta.unhandledTags().containsKey(key);
    }else{
      return handleByEntry.withValue(itemMeta);
    }
  }

  @Override
  public Nbt<?> put(String key, Nbt<?> value) {
    HandleByEntry handleByEntry = HANDLED_ENTRIES.get(key);
    if (handleByEntry == null) {
      return Nbt.fromNms(refItemMeta.unhandledTags().put(key, value.delegate));
    }else{
      Nbt<?> oldValue = handleByEntry.getValue(itemMeta);
      handleByEntry.setValue(itemMeta, value);
      return oldValue;
    }
  }

  @Override
  public Nbt<?> remove(String key){
    HandleByEntry handleByEntry = HANDLED_ENTRIES.get(key);
    if (handleByEntry == null) {
      return Nbt.fromNms(refItemMeta.unhandledTags().remove(key));
    }else{
      Nbt<?> oldValue = handleByEntry.getValue(itemMeta);
      handleByEntry.remove(itemMeta);
      return oldValue;
    }
  }

  @Override
  public NbtItemStack clone() {
    return new NbtItemStack(itemStack.clone());
  }

  @Override
  public int size() {
    int size = refItemMeta.unhandledTags().size();
    for (Entry<String, HandleByEntry> entry : HANDLED_ENTRIES.entrySet()) {
      if (entry.getValue().withValue(itemMeta)) {
        size ++;
      }
    }
    return size;
  }

  @Override
  public byte getId() {
    return NbtType.TAG_COMPOUND;
  }

  @Override
  public String getAsString() {
    return itemStack.toString();
  }

  @Override
  public void putAll(@NotNull Map<? extends String, ? extends Nbt<?>> m) {
    delegateMap.putAll(m);
  }

  @Override
  public void clear() {
    delegateMap.clear();
  }

  @Override
  public boolean isEmpty() {
    return delegateMap.isEmpty();
  }

  @Override
  public boolean containsValue(Object value) {
    return delegateMap.containsValue(value);
  }


  @Override
  public Set<String> keySet() {
    if(keySet == null){
      keySet = new AbstractSet<String>() {
        @Override
        public boolean contains(Object o) {
          return NbtItemStack.this.containsKey(o);
        }

        @Override
        public Iterator<String> iterator() {
          Iterator<String> joinedIterator = Stream.concat(HANDLED_ENTRIES.entrySet()
                  .stream()
                  .filter(it->it.getValue().withValue(itemMeta))
                  .map(ENTRY_GET_KEY),
              refItemMeta.unhandledTags().keySet().stream()
          ).iterator();

          return new Iterator<String>() {
            private String current;

            @Override
            public boolean hasNext() {
              return joinedIterator.hasNext();
            }

            @Override
            public String next() {
              current = joinedIterator.next();
              return current;
            }

            @Override
            public void remove() {
              if(current == null){
                throw new IllegalStateException();
              }
              NbtItemStack.this.remove(current);
            }
          };
        }

        @Override
        public int size() {
          return NbtItemStack.this.size();
        }
      };
    }
    return keySet;
  }

  @NotNull
  @Override
  public Collection<Nbt<?>> values() {
    return null;
  }

  @Override
  public Set<Entry<String, Nbt<?>>> entrySet() {
    if(entrySet == null){
      entrySet = new AbstractSet<Entry<String, Nbt<?>>>() {
        @Override
        public Iterator<Entry<String, Nbt<?>>> iterator() {
          Iterator<Entry<String, Nbt<?>>> joinedIterator = Stream.concat(HANDLED_ENTRIES.entrySet()
                  .stream()
                  .filter(it -> it.getValue().withValue(itemMeta))
                  .map(it -> (Entry<String, Nbt<?>>) (Object) Maps.immutableEntry(it.getKey(), it.getValue().getValue(itemMeta))),
              refItemMeta.unhandledTags().entrySet()
                  .stream()
                  .map(it -> (Entry<String, Nbt<?>>) (Object) Maps.immutableEntry(it.getKey(), Nbt.fromNms(it.getValue())))
          ).iterator();
          return new Iterator<Entry<String, Nbt<?>>>() {
            private Entry<String, Nbt<?>> current;
            @Override
            public boolean hasNext() {
              return joinedIterator.hasNext();
            }

            @Override
            public Entry<String, Nbt<?>> next() {
              current = joinedIterator.next();
              return current;
            }

            @Override
            public void remove() {
              NbtItemStack.this.remove(current.getKey());
            }
          };
        }

        @Override
        public int size() {
          return NbtItemStack.this.size();
        }
      };
    }
    return entrySet;
  }

  public interface HandleByEntry {
    String getKey();
    boolean withValue(ItemMeta meta);
    Nbt<?> getValue(ItemMeta meta);
    void setValue(ItemMeta meta, Nbt<?> value);
    void remove(ItemMeta meta);
  }

  public static class UnsupportedEntry implements HandleByEntry {
    private final String key;

    public UnsupportedEntry(String key) {
      this.key = key;
    }

    @Override
    public String getKey() {
      return key;
    }

    @Override
    public boolean withValue(ItemMeta meta) {
      return false;
    }

    @Override
    public Nbt<?> getValue(ItemMeta meta) {
      return null;
    }

    @Override
    public void setValue(ItemMeta meta, Nbt<?> value) {

    }

    @Override
    public void remove(ItemMeta meta) {

    }
  }

  public static class DisplayEntry implements HandleByEntry {
    @Override
    public String getKey() {
      return "Name";
    }

    @Override
    public boolean withValue(ItemMeta meta) {
      return meta.hasDisplayName();
    }

    @Override
    public Nbt<?> getValue(ItemMeta meta) {
      return meta.hasDisplayName() ? NbtString.valueOf(meta.getDisplayName()) : null;
    }

    @Override
    public void setValue(ItemMeta meta, Nbt<?> value) {
      meta.setDisplayName(value == null ? null : value.getAsString());
    }

    @Override
    public void remove(ItemMeta meta) {
      meta.setDisplayName(null);
    }
  }
}
