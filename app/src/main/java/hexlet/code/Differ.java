package hexlet.code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;

public class Differ<T> {
    public final String key;
    public final T value;
    public final String type;

    public Differ(String key, T value, String type) {
        this.key = key;
        this.value = value;
        this.type = type;
    }

    public String toString() {
        return String.format("  %s %s: %s", type.equals("=") ? " " : type, key, value);
    }
    //в тз такого требования по такой сигнатуре не было, но автотесты ругались
    //дефолтное значение я определяю средствали picocli вообще
    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
    }
    public static <T> String generate(String filepath1, String filepath2, String format) throws IOException {
        Map<String, T> file1 = Utils.unserialize(filepath1);
        Map<String, T> file2 = Utils.unserialize(filepath2);
        HashSet<String> keySet = new HashSet<>(file1.keySet());
        keySet.addAll(file2.keySet());
        ArrayList<String> keyList = new ArrayList<>(keySet);
        Collections.sort(keyList);
        ArrayList<Differ> diffList = new ArrayList<>();
        keyList.forEach(k -> {
            boolean containsKey1 = file1.containsKey(k);
            boolean containsKey2 = file2.containsKey(k);

            if (containsKey1 && containsKey2) {
                if (String.valueOf(file1.get(k)).equals(String.valueOf(file2.get(k)))) {
                    diffList.add(new Differ(k, file1.get(k), "="));
                } else {
                    diffList.add(new Differ(k, file1.get(k), "-"));
                    diffList.add(new Differ(k, file2.get(k), "+"));
                }
            } else if (!containsKey1 && containsKey2) {
                diffList.add(new Differ(k, file2.get(k), "+"));
            } else if (containsKey1 && !containsKey2) {
                diffList.add(new Differ(k, file1.get(k), "-"));
            }
        });
        return Formatter.getDiffFormat(diffList, format);
    }
}

