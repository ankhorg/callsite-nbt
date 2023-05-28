package bot.inker.bukkit.nbt.loader.annotation;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(HandleBy.List.class)
public @interface HandleBy {
  MinecraftVersion version();

  String reference();

  boolean isInterface() default false;

  boolean remap() default false;


  @Target({ElementType.FIELD, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.TYPE})
  @Retention(RetentionPolicy.RUNTIME)
  @interface List {
    HandleBy[] value();
  }
}
