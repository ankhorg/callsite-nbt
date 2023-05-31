package bot.inker.bukkit.nbtbuild.maps;

import bot.inker.bukkit.nbtbuild.nodes.ClassNode;
import bot.inker.bukkit.nbtbuild.nodes.FieldNode;
import bot.inker.bukkit.nbtbuild.nodes.MethodNode;
import org.apache.commons.lang3.StringUtils;
import org.objectweb.asm.Type;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProguardMap extends AbstractMap {
  public ProguardMap(Map<String, ClassNode> classNodeMap) {
    super(classNodeMap);
  }

  public static ProguardMap create(BufferedReader reader) throws IOException {
    String line;
    Map<String, ClassNode> classNodeMap = new LinkedHashMap<>();
    ClassNode currentClass = null;
    while ((line = reader.readLine()) != null) {
      if (line.startsWith("#")) {
        continue;
      }
      if (!line.startsWith("    ")) {
        String[] lineEntry = StringUtils.split(line, ' ');
        String rawType = proguardToType(lineEntry[0]).getInternalName();
        String remapType = proguardToType(lineEntry[2].substring(0, lineEntry[2].length() - 1)).getInternalName();
        currentClass = classNodeMap.computeIfAbsent(rawType, it -> new ClassNode(rawType, remapType));
        continue;
      }
      String[] lineEntry = StringUtils.split(line.substring(4), ' ');
      if (lineEntry[1].endsWith(")")) {
        Type returnType = proguardToType(substringAfterLastOrAll(lineEntry[0], ':'));
        String methodName = StringUtils.substringBefore(lineEntry[1], '(');
        String[] argProguardTypes = StringUtils.split(lineEntry[1].substring(methodName.length() + 1, lineEntry[1].length() - 1), ',');
        Type[] argTypes = new Type[argProguardTypes.length];
        for (int i = 0; i < argProguardTypes.length; i++) {
          argTypes[i] = proguardToType(argProguardTypes[i]);
        }
        currentClass.methodMap().put(new MethodNode(
            currentClass.raw(),
            methodName,
            Type.getMethodType(returnType, argTypes).getDescriptor()
        ), lineEntry[3]);
      } else {
        currentClass.fieldMap().put(new FieldNode(
            currentClass.raw(),
            lineEntry[1],
            proguardToType(lineEntry[0]).getDescriptor()
        ), lineEntry[3]);
      }
    }
    return new ProguardMap(classNodeMap);
  }

  private static Type proguardToType(String proguardName) {
    if (proguardName.endsWith("[]")) {
      int arraySize = (proguardName.length() - proguardName.indexOf('[')) / 2;
      Type componentType = proguardToType(proguardName.substring(0, proguardName.length() - 2 * arraySize));
      return Type.getType(StringUtils.repeat('[', arraySize) + componentType.getDescriptor());
    }
    switch (proguardName) {
      case "void":
        return Type.VOID_TYPE;
      case "boolean":
        return Type.BOOLEAN_TYPE;
      case "char":
        return Type.CHAR_TYPE;
      case "byte":
        return Type.BYTE_TYPE;
      case "short":
        return Type.SHORT_TYPE;
      case "int":
        return Type.INT_TYPE;
      case "float":
        return Type.FLOAT_TYPE;
      case "long":
        return Type.LONG_TYPE;
      case "double":
        return Type.DOUBLE_TYPE;
      default:
        return Type.getObjectType(proguardName.replace('.', '/'));
    }
  }

  private static String typeToProguard(Type type) {
    switch (type.getSort()) {
      case Type.VOID:
        return "void";
      case Type.BOOLEAN:
        return "boolean";
      case Type.CHAR:
        return "char";
      case Type.BYTE:
        return "byte";
      case Type.SHORT:
        return "short";
      case Type.INT:
        return "int";
      case Type.FLOAT:
        return "float";
      case Type.LONG:
        return "long";
      case Type.DOUBLE:
        return "double";
      case Type.ARRAY:
        return typeToProguard(type.getElementType()) + StringUtils.repeat("[]", type.getDimensions());
      case Type.OBJECT:
        return type.getInternalName().replace('/', '.');
      default:
        throw new UnsupportedOperationException("Unsupported sort " + type.getSort());
    }
  }

  private static String substringAfterLastOrAll(String str, int separator) {
    if (str.isEmpty()) {
      return str;
    } else {
      int pos = str.lastIndexOf(separator);
      return pos != -1 && pos != str.length() - 1 ? str.substring(pos + 1) : str;
    }
  }

  public void write(Writer out) throws IOException {
    for (Map.Entry<String, ClassNode> classEntry : classNodeMap().entrySet()) {
      out.write(typeToProguard(Type.getObjectType(classEntry.getValue().raw())));
      out.write(" -> ");
      out.write(typeToProguard(Type.getObjectType(classEntry.getValue().remapped())));
      out.write(":\n");

      for (Map.Entry<FieldNode, String> fieldEntry : classEntry.getValue().fieldMap().entrySet()) {
        out.write("    ");
        out.write(typeToProguard(Type.getType(fieldEntry.getKey().desc())));
        out.write(" ");
        out.write(fieldEntry.getKey().name());
        out.write(" -> ");
        out.write(fieldEntry.getValue());
        out.write("\n");
      }

      for (Map.Entry<MethodNode, String> methodEntry : classEntry.getValue().methodMap().entrySet()) {
        out.write("    1:1:");
        Type desc = Type.getMethodType(methodEntry.getKey().desc());
        out.write(typeToProguard(desc.getReturnType()));
        out.write(" ");
        out.write(methodEntry.getKey().name());
        out.write("(");
        Type[] argumentTypes = desc.getArgumentTypes();
        for (int i = 0; i < argumentTypes.length; i++) {
          out.write(typeToProguard(argumentTypes[i]));
          if (i != argumentTypes.length - 1) {
            out.write(",");
          }
        }
        out.write(") -> ");
        out.write(methodEntry.getValue());
        out.write("\n");
      }
    }

    out.flush();
  }
}
