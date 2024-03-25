package hexlet.code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws IOException {
        Map<String, String> json1 = Utils.unserialize(Utils.readFile(filepath1));
        Map<String, String> json2 = Utils.unserialize(Utils.readFile(filepath2));
        LinkedHashSet<String> keySet = new LinkedHashSet<>(json1.keySet());
        keySet.addAll(json2.keySet());
        ArrayList<String> keyList = new ArrayList<>(keySet);
        Collections.sort(keyList);
        ArrayList<Pair> pairList = new ArrayList<>();
        keyList.forEach(k -> {
            if (json1.containsKey(k) && json2.containsKey(k)) {
                if (json1.get(k).equals(json2.get(k))) {
                    pairList.add(new Pair(k, json1.get(k)));
                } else {
                    pairList.add(new Pair(k, json1.get(k), "-"));
                    pairList.add(new Pair(k, json2.get(k), "+"));
                }
            } else if (!json1.containsKey(k) && json2.containsKey(k)) {
                pairList.add(new Pair(k, json1.get(k), "+"));
            } else if (json1.containsKey(k) && !json2.containsKey(k)) {
                pairList.add(new Pair(k, json1.get(k), "-"));
            }
        });
        StringBuilder result = new StringBuilder();
        pairList.forEach(pair -> {
            result.append(pair.toString()).append("\n");
        });
        return result.toString();
    }
}

record Pair(String key, String value, String flag) {
    Pair(String key, String value) {
        this(key, value, " ");
    }

    public String toString() {
        return String.format("%s %s: %s", flag, key, value);
    }
}
