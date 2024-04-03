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
        Path path = getPath(fileName);
        return Files.readString(path);
    }

    private static Path getPath(String fileName) throws Exception {
        return Paths.get("src", "test", "resources", fileName)
                .toAbsolutePath().normalize();
    }

    @BeforeAll
    static void preparing() throws Exception {
        expectedStylish = readFile("expectedStylish.txt");
        expectedPlain = readFile("expectedPlain.txt");
        expectedJson = readFile("expectedJson.json");
    }

    @Test
    void testJSON() throws Exception {
        String file1 = getPath("file1.json").toString();
        String file2 = getPath("file2.json").toString();
        assertThat(generate(file1, file2, "plain")).isEqualTo(expectedPlain);
        assertThat(generate(file1, file2, "stylish")).isEqualTo(expectedStylish);
        assertThat(generate(file1, file2, "json")).isEqualTo(expectedJson);
    }

    @Test
    void testYAML() throws Exception {
        String file1 = getPath("file1.yml").toString();
        String file2 = getPath("file2.yml").toString();
        assertThat(generate(file1, file2, "plain")).isEqualTo(expectedPlain);
        assertThat(generate(file1, file2, "stylish")).isEqualTo(expectedStylish);
        assertThat(generate(file1, file2, "json")).isEqualTo(expectedJson);
    }
}
