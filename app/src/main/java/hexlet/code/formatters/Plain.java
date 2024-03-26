package hexlet.code.formatters;

import hexlet.code.Differ;
import org.apache.commons.lang3.ClassUtils;

import java.util.ArrayList;

public class Plain {
    public static String getPlain(ArrayList<Differ> diffList) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < diffList.size(); i++) {
            Differ currDiff = diffList.get(i);
            if (!currDiff.type.equals("=")) {
                //если 2 ключа подряд, то объединяем в 1 запись
                if (i != diffList.size() - 1 && diffList.get(i + 1).key.equals(currDiff.key)) {
                    result.append("Property '").append(currDiff.key).append("' was updated. From ")
                            .append(complexOrValue(currDiff)).append(" to ")
                            .append(complexOrValue(diffList.get(i + 1))).append("\n");
                    i += 1;
                    //если минус, то удаление
                } else if (currDiff.type.equals("-")) {
                    result.append("Property '").append(currDiff.key).append("' was removed").append("\n");
                    //если плюс, до добавление
                } else if (currDiff.type.equals("+")) {
                    result.append("Property '").append(currDiff.key).append("' was added with value: ")
                            .append(complexOrValue(currDiff)).append("\n");
                }
            }
        }
        return result.toString();
    }

    //определяем, примитив ли это или комплексное значение
    private static String complexOrValue(Differ currDiff) {
        if (currDiff.value == null) {
            return "null";
        } else if (currDiff.value.getClass() == String.class) {
            return "'" + currDiff.value.toString() + "'";
        }
        return ClassUtils.isPrimitiveOrWrapper(currDiff.value.getClass())
                ? currDiff.value.toString() : "[complex value]";
    }
}
