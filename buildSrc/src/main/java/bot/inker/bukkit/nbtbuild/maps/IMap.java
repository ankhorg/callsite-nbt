package bot.inker.bukkit.nbtbuild.maps;

import bot.inker.bukkit.nbtbuild.nodes.FieldNode;
import bot.inker.bukkit.nbtbuild.nodes.MethodNode;
import org.objectweb.asm.Type;

public interface IMap {
  String map(String internalName);

  default String mapDesc(String descriptor) {
    return mapType(Type.getType(descriptor)).getDescriptor();
  }

  default Type mapType(Type type) {
    switch (type.getSort()) {
      case Type.ARRAY:
        StringBuilder remappedDescriptor = new StringBuilder();
        for (int i = 0; i < type.getDimensions(); ++i) {
          remappedDescriptor.append('[');
        }
        remappedDescriptor.append(mapType(type.getElementType()).getDescriptor());
        return Type.getType(remappedDescriptor.toString());
      case Type.OBJECT:
        String remappedInternalName = map(type.getInternalName());
        return remappedInternalName != null ? Type.getObjectType(remappedInternalName) : type;
      case Type.METHOD:
        return Type.getMethodType(mapMethodDesc(type.getDescriptor()));
      default:
        return type;
    }
  }

  default String mapMethodDesc(String methodDescriptor) {
    if ("()V".equals(methodDescriptor)) {
      return methodDescriptor;
    }

    StringBuilder stringBuilder = new StringBuilder("(");
    for (Type argumentType : Type.getArgumentTypes(methodDescriptor)) {
      stringBuilder.append(mapType(argumentType).getDescriptor());
    }
    Type returnType = Type.getReturnType(methodDescriptor);
    if (returnType == Type.VOID_TYPE) {
      stringBuilder.append(")V");
    } else {
      stringBuilder.append(')').append(mapType(returnType).getDescriptor());
    }
    return stringBuilder.toString();
  }

  default MethodNode mapMethod(MethodNode methodNode) {
    return new MethodNode(
        map(methodNode.owner()),
        mapMethodName(methodNode.owner(), methodNode.name(), methodNode.desc()),
        mapMethodDesc(methodNode.desc())
    );
  }

  String mapMethodName(String owner, String name, String descriptor);

  default FieldNode mapField(FieldNode fieldNode) {
    return new FieldNode(
        map(fieldNode.owner()),
        mapFieldName(fieldNode.owner(), fieldNode.name(), fieldNode.desc()),
        mapDesc(fieldNode.desc())
    );
  }

  String mapFieldName(String owner, String name, String descriptor);
}
