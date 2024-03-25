package hexlet.code;

import java.util.ArrayList;

import static hexlet.code.formatters.Plain.getPlain;
import static hexlet.code.formatters.Stylish.getStylish;

public class Formatter {
    static String getDiffFormat(ArrayList<Diff> diffList, String format) {
        return switch (format) {
            case "stylish" -> getStylish(diffList);
            case "plain" -> getPlain(diffList);
            default -> "Wrong parameter of format";
        };
    }
}
