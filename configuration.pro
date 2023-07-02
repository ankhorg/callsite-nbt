-optimizations !code/simplification/cast
-optimizationpasses 10
-optimizations *
-dontobfuscate
-allowaccessmodification
-keepattributes RuntimeVisibleAnnotations, AnnotationDefault

-keepparameternames -keep class bot.inker.bukkit.nbt.*, bot.inker.bukkit.nbt.loader.*, bot.inker.bukkit.nbt.api.* {
    *;
}
-keep class bot.inker.bukkit.nbt.internal.ref.*, bot.inker.bukkit.nbt.internal.loader.ConstMappings, bot.inker.bukkit.nbt.internal.loader.CallSiteInstaller$Spy {
    *;
}
-keep enum ** {
    *;
}