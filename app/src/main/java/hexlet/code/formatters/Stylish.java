package hexlet.code.formatters;

import hexlet.code.ActionType;

import java.util.ArrayList;
import java.util.Map;

public class Stylish {
    public static String getStylish(ArrayList<Map<String, Object>> diffList) {
        StringBuilder result = new StringBuilder();
        result.append("{\n");
        diffList.forEach(diff -> {
            Enum type = (Enum) diff.get("type");
            String key = (String) diff.get("key");
            if (type == ActionType.ADDED) {
                result.append(String.format("  + %s: %s\n", key, diff.get("value")));
            } else if (type == ActionType.DELETED) {
                result.append(String.format("  - %s: %s\n", key, diff.get("value")));
            } else if (type == ActionType.UNCHANGED) {
                result.append(String.format("    %s: %s\n", key, diff.get("value")));
            } else if (type == ActionType.CHANGED) {
                result.append(String.format("  - %s: %s\n", key, diff.get("value1")));
                result.append(String.format("  + %s: %s\n", key, diff.get("value2")));
            }
        });
        result.append("}");
        return result.toString();
    }
}
