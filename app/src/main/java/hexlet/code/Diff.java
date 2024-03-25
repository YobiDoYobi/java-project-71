package hexlet.code;

public record Diff<T>(String key, T value, String flag) {
    Diff(String key, T value) {
        this(key, value, " ");
    }

    public String toString() {
        return String.format("  %s %s: %s", flag, key, value);
    }
}
