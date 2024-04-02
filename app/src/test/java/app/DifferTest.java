package app;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static hexlet.code.Differ.generate;
import static org.assertj.core.api.Assertions.assertThat;

public class DifferTest {
    private static String expectedStylish;
    private static String expectedPlain;
    private static String expectedJson;

    private static String readFile(String fileName) throws Exception {
        Path path = Paths.get("src", "test", "resources", fileName)
                .toAbsolutePath().normalize();
        return Files.readString(path);
    }

    @BeforeAll
    static void preparing() throws Exception {
        expectedStylish = readFile("expectedStylish.txt");
        expectedPlain = readFile("expectedPlain.txt");
        expectedJson = readFile("expectedJson.json");
    }

    @Test
    void testJSON() throws Exception {
        String file1 = "src/test/resources/file1.json";
        String file2 = "src/test/resources/file2.json";
        assertThat(generate(file1, file2, "plain")).isEqualTo(expectedPlain);
        assertThat(generate(file1, file2, "stylish")).isEqualTo(expectedStylish);
        assertThat(generate(file1, file2, "json")).isEqualTo(expectedJson);
    }

    @Test
    void testYAML() throws Exception {
        String file1 = "src/test/resources/file1.yml";
        String file2 = "src/test/resources/file2.yml";
        assertThat(generate(file1, file2, "plain")).isEqualTo(expectedPlain);
        assertThat(generate(file1, file2, "stylish")).isEqualTo(expectedStylish);
        assertThat(generate(file1, file2, "json")).isEqualTo(expectedJson);
    }
}
