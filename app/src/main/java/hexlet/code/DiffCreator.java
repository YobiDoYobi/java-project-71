package hexlet.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import static hexlet.code.ActionType.ADDED;
import static hexlet.code.ActionType.CHANGED;
import static hexlet.code.ActionType.DELETED;
import static hexlet.code.ActionType.UNCHANGED;

public class DiffCreator {
    public static ArrayList<Map<String, Object>> getDiff(Map<String, Object> file1, Map<String, Object> file2) {
        TreeSet<String> keySet = new TreeSet<>(file1.keySet());
        keySet.addAll(file2.keySet());
        ArrayList<Map<String, Object>> diffList = new ArrayList<>();
        keySet.forEach(k -> {
            boolean containsKey1 = file1.containsKey(k);
            boolean containsKey2 = file2.containsKey(k);
            HashMap<String, Object> diff = new HashMap<>();
            diff.put("key", k);
            if (!containsKey1) {
                diff.put("type", ADDED);
                diff.put("value", file2.get(k));
            } else if (!containsKey2) {
                diff.put("type", DELETED);
                diff.put("value", file1.get(k));
            } else if (String.valueOf(file1.get(k)).equals(String.valueOf(file2.get(k)))) {
                diff.put("type", UNCHANGED);
                diff.put("value", file1.get(k));
            } else {
                diff.put("type", CHANGED);
                diff.put("value1", file1.get(k));
                diff.put("value2", file2.get(k));
            }
            diffList.add(diff);
        });
        return diffList;
    }
}
