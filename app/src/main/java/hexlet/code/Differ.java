package hexlet.code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws IOException {
        Map<String, String> file1 = Utils.unserialize(filepath1);
        Map<String, String> file2 = Utils.unserialize(filepath2);
        HashSet<String> keySet = new HashSet<>(file1.keySet());
        keySet.addAll(file2.keySet());
        ArrayList<String> keyList = new ArrayList<>(keySet);
        Collections.sort(keyList);
        ArrayList<Pair> pairList = new ArrayList<>();
        keyList.forEach(k -> {
            if (file1.containsKey(k) && file2.containsKey(k)) {
                if (file1.get(k).equals(file2.get(k))) {
                    pairList.add(new Pair(k, file1.get(k)));
                } else {
                    pairList.add(new Pair(k, file1.get(k), "-"));
                    pairList.add(new Pair(k, file2.get(k), "+"));
                }
            } else if (!file1.containsKey(k) && file2.containsKey(k)) {
                pairList.add(new Pair(k, file1.get(k), "+"));
            } else if (file1.containsKey(k) && !file2.containsKey(k)) {
                pairList.add(new Pair(k, file1.get(k), "-"));
            }
        });
        StringBuilder result = new StringBuilder();
        result.append("{\n");
        pairList.forEach(pair -> {
            result.append("  ").append(pair.toString()).append("\n");
        });
        result.append("}");
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
