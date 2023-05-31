-optimizationpasses 10
-optimizations *
-dontobfuscate
-allowaccessmodification
-keepattributes RuntimeVisibleAnnotations, AnnotationDefault

-keepparameternames -keep class bot.inker.bukkit.nbt.*, bot.inker.bukkit.nbt.loader.* {
    *;
}
-keep class bot.inker.bukkit.nbt.internal.ref.*, bot.inker.bukkit.nbt.internal.loader.ConstMappings {
    *;
}
-keep enum ** {
    *;
}