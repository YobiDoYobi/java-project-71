package hexlet.code.formatters;

import hexlet.code.ActionType;
import org.apache.commons.lang3.ClassUtils;

import java.util.ArrayList;
import java.util.Map;

public class Plain {
    public static <T> String getPlain(ArrayList<Map<String, T>> diffList) {
        StringBuilder result = new StringBuilder();
        diffList.forEach(diff -> {
            Enum type = (Enum) diff.get("type");
            String key = (String) diff.get("key");

            if (type == ActionType.ADDED) {
                result.append(String.format("Property '%s' was added with value: %s\n",
                        key, complexOrValue(diff.get("value2"))));
            } else if (type == ActionType.DELETED) {
                result.append(String.format("Property '%s' was removed\n", key));
            } else if (type == ActionType.CHANGED) {
                result.append(String.format("Property '%s' was updated. From %s to %s\n",
                        key, complexOrValue(diff.get("value1")), complexOrValue(diff.get("value2"))));
            }
        });
        return result.toString().trim();
    }

    //определяем, примитив ли это или комплексное значение
    //определяем, примитив ли это или комплексное значение
    private static String complexOrValue(Object diff) {
        if (diff == null) {
            return "null";
        } else if (diff.getClass() == String.class) {
            return "'" + diff + "'";
        }
        return ClassUtils.isPrimitiveOrWrapper(diff.getClass())
                ? diff.toString() : "[complex value]";
    }
}
