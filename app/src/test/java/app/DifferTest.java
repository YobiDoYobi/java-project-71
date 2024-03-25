package app;

import org.junit.jupiter.api.Test;
import java.io.IOException;

import static hexlet.code.Differ.generate;
import static org.assertj.core.api.Assertions.assertThat;

public class DifferTest {

    @Test
    void  testJSON() throws IOException {
        String path1 = "src/test/resources/file1.json";
        String path2 = "src/test/resources/file2.json";
        String expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: null
                }""";
        assertThat(generate(path1, path2)).isEqualTo(expected);
    }

    @Test
    void  testYAML() throws IOException {
        String path1 = "src/test/resources/file1.yml";
        String path2 = "src/test/resources/file2.yml";
        String expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: null
                }""";
        assertThat(generate(path1, path2)).isEqualTo(expected);
    }
}
