package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.Differ;
import hexlet.code.Utils;

import java.util.ArrayList;

public class JSON {
    public static String getJSON(ArrayList<Differ> diffList) throws JsonProcessingException {
        return Utils.serialize(diffList);
    }
}
