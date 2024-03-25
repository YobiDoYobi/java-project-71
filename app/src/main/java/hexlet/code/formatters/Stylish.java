package hexlet.code.formatters;

import hexlet.code.Diff;

import java.util.ArrayList;

public class Stylish {
    public static String getStylish(ArrayList<Diff> diffList) {
        StringBuilder result = new StringBuilder();
        result.append("{\n");
        diffList.forEach(diff -> result.append(diff.toString()).append("\n"));
        result.append("}");
        return result.toString();
    }
}
