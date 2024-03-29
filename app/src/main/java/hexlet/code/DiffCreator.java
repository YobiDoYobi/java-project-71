package hexlet.code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static hexlet.code.ActionType.ADDED;
import static hexlet.code.ActionType.CHANGED;
import static hexlet.code.ActionType.DELETED;
import static hexlet.code.ActionType.UNCHANGED;

public class DiffCreator {
    public static <T> ArrayList getDiff(Map<String, T> file1, Map<String, T> file2) {
        HashSet<String> keySet = new HashSet<>(file1.keySet());
        keySet.addAll(file2.keySet());
        ArrayList<String> keyList = new ArrayList<>(keySet);
        Collections.sort(keyList);
        ArrayList<Map<String, T>> diffList = new ArrayList<>();
        keyList.forEach(k -> {
            boolean containsKey1 = file1.containsKey(k);
            boolean containsKey2 = file2.containsKey(k);
            HashMap<String, T> diff = new HashMap<>();
            diff.put("key", (T) k);
            if (containsKey1 && containsKey2) {
                if (String.valueOf(file1.get(k)).equals(String.valueOf(file2.get(k)))) {
                    diff.put("type", (T) UNCHANGED);
                    diff.put("value1", file1.get(k));
                    diff.put("value2", file2.get(k));
                } else {
                    diff.put("type", (T) CHANGED);
                    diff.put("value1", file1.get(k));
                    diff.put("value2", file2.get(k));
                }
            } else if (!containsKey1 && containsKey2) {
                diff.put("type", (T) ADDED);
                diff.put("value2", file2.get(k));
            } else if (containsKey1 && !containsKey2) {
                diff.put("type", (T) DELETED);
                diff.put("value1", file1.get(k));
            }
            diffList.add(diff);
        });
        return diffList;
    }
}
