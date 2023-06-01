# CallSite NBT - 速度优先的 NBT 库

## 使用方法
```kotlin
repositories {
  maven("https://r.irepo.space/maven")
}

dependencies {
  // api("bot.inker.bukkit:callsite-nbt:1.0-3")
  // api("bot.inker.bukkit:callsite-nbt-fat:1.0-3")
  api("bot.inker.bukkit:callsite-nbt-obf:1.0-3")
}
```

- callsite-nbt 需要手动打包 asm 与 asm-commons
- callsite-nbt-fat 为打包 asm 的版本
- callsite-nbt-obf 为经过 proguard 混淆与优化的版本

请在插件中使用时，请在插件的 `<clinit>` 块中安装 `CallSiteNbt`。

```java
public class YourBukkitPlugin extends JavaPlugin {
  static {
    CallSiteNbt.install(YourBukkitPlugin.class);
  }
}
```

在安装完成后即可使用 CallSiteNbt。为了确保 Nbt 相关的类在安装前不被加载，请尽量不要在插件主类中使用。

## License

The MIT License (MIT)

Copyright (c) 2023 InkerBot

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

## License 豁免

我们可以提供 WTFPL 形式的授权。如您需要，请邮件联系我们 im@inker.bot