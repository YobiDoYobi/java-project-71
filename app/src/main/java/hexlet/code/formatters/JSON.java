package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.Parser;

import java.util.ArrayList;
import java.util.Map;

public class JSON {
    public static <T> String getJSON(ArrayList<Map<String, T>> diffList) throws JsonProcessingException {
        return Parser.serialize(diffList);
    }
}
