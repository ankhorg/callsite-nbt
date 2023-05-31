package bot.inker.bukkit.nbtbuild.util;

import org.objectweb.asm.Type;

import java.util.Map;

public class RemapUtils {
  private RemapUtils() {
    throw new UnsupportedOperationException();
  }

  public static Type mapType(Map<String, String> classMap, Type type) {
    switch (type.getSort()) {
      case Type.ARRAY:
        StringBuilder remappedDescriptor = new StringBuilder();
        for (int i = 0; i < type.getDimensions(); ++i) {
          remappedDescriptor.append('[');
        }
        remappedDescriptor.append(mapType(classMap, type.getElementType()).getDescriptor());
        return Type.getType(remappedDescriptor.toString());
      case Type.OBJECT:
        String internalName = type.getInternalName();
        return Type.getObjectType(classMap.getOrDefault(internalName, internalName));
      case Type.METHOD:
        Type methodType = Type.getMethodType(type.getDescriptor());
        Type[] newArgumentTypes = new Type[methodType.getArgumentTypes().length];
        for (int i = 0; i < newArgumentTypes.length; i++) {
          newArgumentTypes[i] = mapType(classMap, methodType.getArgumentTypes()[i]);
        }
        return Type.getMethodType(mapType(classMap, methodType.getReturnType()), newArgumentTypes);
      default:
        return type;
    }
  }
}
