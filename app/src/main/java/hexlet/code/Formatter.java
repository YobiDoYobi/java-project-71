package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;

import static hexlet.code.formatters.JSON.getJSON;
import static hexlet.code.formatters.Plain.getPlain;
import static hexlet.code.formatters.Stylish.getStylish;

public class Formatter {
    static String getDiffFormat(ArrayList<Differ> diffList, String format) throws JsonProcessingException {
        return switch (format) {
            case "stylish" -> getStylish(diffList);
            case "plain" -> getPlain(diffList);
            case "json" -> getJSON(diffList);
            default -> "Wrong parameter of format";
        };
    }
}
