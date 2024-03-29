package app;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static hexlet.code.Differ.generate;
import static org.assertj.core.api.Assertions.assertThat;

public class DifferTest {
    private static Path fullPathStylish = Paths.get("src/test/resources/expectedStylish.txt")
            .toAbsolutePath().normalize();
    private static Path fullPathPlain = Paths.get("src/test/resources/expectedPlain.txt")
            .toAbsolutePath().normalize();
    private static Path fullPathJson = Paths.get("src/test/resources/expectedJson.json")
            .toAbsolutePath().normalize();

    private static String readFile(Path path) throws Exception {
        return Files.readString(path);
    }

    @Test
    void testJSON() throws Exception {
        String path1 = "src/test/resources/file1.json";
        String path2 = "src/test/resources/file2.json";
        assertThat(generate(path1, path2, "plain")).isEqualTo(readFile(fullPathPlain));
        assertThat(generate(path1, path2, "stylish")).isEqualTo(readFile(fullPathStylish));
        assertThat(generate(path1, path2, "json")).isEqualTo(readFile(fullPathJson));
    }

    @Test
    void testYAML() throws Exception {
        String path1 = "src/test/resources/file1.yml";
        String path2 = "src/test/resources/file2.yml";
        assertThat(generate(path1, path2, "plain")).isEqualTo(readFile(fullPathPlain));
        assertThat(generate(path1, path2, "stylish")).isEqualTo(readFile(fullPathStylish));
        assertThat(generate(path1, path2, "json")).isEqualTo(readFile(fullPathJson));
    }
}
