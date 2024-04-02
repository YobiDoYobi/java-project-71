package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.util.HashMap;

public class Parser {
    public static HashMap<String, Object> unserialize(String fileString, String type) throws IOException {
        HashMap<String, Object> fileMap = new HashMap<>();
        if (type.equals("json")) {
            fileMap = unserializeJSON(fileString);
        } else if (type.equals("yml") | type.equals("yaml")) {
            fileMap = unserializeYAML(fileString);
        }
        return fileMap;
    }

    public static HashMap<String, Object> unserializeJSON(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, new TypeReference<HashMap<String, Object>>() {
        });
    }

    public static HashMap<String, Object> unserializeYAML(String yaml) throws JsonProcessingException {
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(yaml, new TypeReference<HashMap<String, Object>>() {
        });
    }
}
