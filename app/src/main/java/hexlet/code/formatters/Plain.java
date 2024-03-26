package hexlet.code.formatters;

import hexlet.code.Differ;
import org.apache.commons.lang3.ClassUtils;

import java.util.ArrayList;

public class Plain {
    public static String getPlain(ArrayList<Differ> diffList) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < diffList.size(); i++) {
            Differ currDiff = diffList.get(i);
            if (!currDiff.getType().equals("=")) {
                //если 2 ключа подряд, то объединяем в 1 запись
                if (i != diffList.size() - 1 && diffList.get(i + 1).getKey().equals(currDiff.getKey())) {
                    result.append("Property '").append(currDiff.getKey()).append("' was updated. From ")
                            .append(complexOrValue(currDiff)).append(" to ")
                            .append(complexOrValue(diffList.get(i + 1))).append("\n");
                    i += 1;
                    //если минус, то удаление
                } else if (currDiff.getType().equals("-")) {
                    result.append("Property '").append(currDiff.getKey()).append("' was removed").append("\n");
                    //если плюс, до добавление
                } else if (currDiff.getType().equals("+")) {
                    result.append("Property '").append(currDiff.getKey()).append("' was added with value: ")
                            .append(complexOrValue(currDiff)).append("\n");
                }
            }
        }
        return result.toString().trim();
    }

    //определяем, примитив ли это или комплексное значение
    private static String complexOrValue(Differ currDiff) {
        if (currDiff.getValue() == null) {
            return "null";
        } else if (currDiff.getValue().getClass() == String.class) {
            return "'" + currDiff.getValue().toString() + "'";
        }
        return ClassUtils.isPrimitiveOrWrapper(currDiff.getValue().getClass())
                ? currDiff.getValue().toString() : "[complex value]";
    }
}
