package app;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static hexlet.code.Differ.generate;
import static org.assertj.core.api.Assertions.assertThat;

public class DifferTest {
    private final String expectedStr = """
            {
                chars1: [a, b, c]
              - chars2: [d, e, f]
              + chars2: false
              - checked: false
              + checked: true
              - default: null
              + default: [value1, value2]
              - id: 45
              + id: null
              - key1: value1
              + key2: value2
                numbers1: [1, 2, 3, 4]
              - numbers2: [2, 3, 4, 5]
              + numbers2: [22, 33, 44, 55]
              - numbers3: [3, 4, 5]
              + numbers4: [4, 5, 6]
              + obj1: {nestedKey=value, isNested=true}
              - setting1: Some value
              + setting1: Another value
              - setting2: 200
              + setting2: 300
              - setting3: true
              + setting3: none
            }""";
    private final String expectedPlain = """
            Property 'chars2' was updated. From [complex value] to false
            Property 'checked' was updated. From false to true
            Property 'default' was updated. From null to [complex value]
            Property 'id' was updated. From 45 to null
            Property 'key1' was removed
            Property 'key2' was added with value: 'value2'
            Property 'numbers2' was updated. From [complex value] to [complex value]
            Property 'numbers3' was removed
            Property 'numbers4' was added with value: [complex value]
            Property 'obj1' was added with value: [complex value]
            Property 'setting1' was updated. From 'Some value' to 'Another value'
            Property 'setting2' was updated. From 200 to 300
            Property 'setting3' was updated. From true to 'none'""";

    private final String expectedJson = "[{\"key\":\"chars1\",\"value\":[\"a\",\"b\",\"c\"],\"type\":\"=\"},{\"key\":"
            + "\"chars2\",\"value\":[\"d\",\"e\",\"f\"],\"type\":\"-\"},{\"key\":\"chars2\",\"value\":false,\"type\":"
            + "\"+\"},{\"key\":\"checked\",\"value\":false,\"type\":\"-\"},{\"key\":\"checked\",\"value\":true,\"type"
            + "\":\"+\"},{\"key\":\"default\",\"value\":null,\"type\":\"-\"},{\"key\":\"default\",\"value\":[\"value1"
            + "\",\"value2\"],\"type\":\"+\"},{\"key\":\"id\",\"value\":45,\"type\":\"-\"},{\"key\":\"id\",\"value"
            + "\":null,\"type\":\"+\"},{\"key\":\"key1\",\"value\":\"value1\",\"type\":\"-\"},{\"key\":\"key2\",\"value"
            + "\":\"value2\",\"type\":\"+\"},{\"key\":\"numbers1\",\"value\":[1,2,3,4],\"type\":\"=\"},{\"key\":"
            + "\"numbers2\",\"value\":[2,3,4,5],\"type\":\"-\"},{\"key\":\"numbers2\",\"value\":[22,33,44,55],\"type"
            + "\":\"+\"},{\"key\":\"numbers3\",\"value\":[3,4,5],\"type\":\"-\"},{\"key\":\"numbers4\",\"value"
            + "\":[4,5,6],\"type\":\"+\"},{\"key\":\"obj1\",\"value\":{\"nestedKey\":\"value\",\"isNested\":true},"
            + "\"type\":\"+\"},{\"key\":\"setting1\",\"value\":\"Some value\",\"type\":\"-\"},{\"key\":\"setting1\","
            + "\"value\":\"Another value\",\"type\":\"+\"},{\"key\":\"setting2\",\"value\":200,\"type\":\"-\"},{\"key"
            + "\":\"setting2\",\"value\":300,\"type\":\"+\"},{\"key\":\"setting3\",\"value\":true,\"type\":\"-\"},{"
            + "\"key\":\"setting3\",\"value\":\"none\",\"type\":\"+\"}]";

    @Test
    void testJSON() throws IOException {
        String path1 = "src/test/resources/file1.json";
        String path2 = "src/test/resources/file2.json";
        assertThat(generate(path1, path2, "plain")).isEqualTo(expectedPlain);
        assertThat(generate(path1, path2, "stylish")).isEqualTo(expectedStr);
        assertThat(generate(path1, path2, "json")).isEqualTo(expectedJson);
    }

    @Test
    void testYAML() throws IOException {
        String path1 = "src/test/resources/file1.yml";
        String path2 = "src/test/resources/file2.yml";
        assertThat(generate(path1, path2, "plain")).isEqualTo(expectedPlain);
        assertThat(generate(path1, path2, "stylish")).isEqualTo(expectedStr);
        assertThat(generate(path1, path2, "json")).isEqualTo(expectedJson);
    }
}
