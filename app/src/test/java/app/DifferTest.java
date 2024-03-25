package app;

import org.junit.jupiter.api.Test;
import java.io.IOException;

import static hexlet.code.Differ.generate;
import static org.assertj.core.api.Assertions.assertThat;

public class DifferTest {
    private String path1 = "src/test/resources/file1.json";
    private String path2 = "src/test/resources/file2.json";
    @Test
    void  test1() throws IOException {
        String expected = "- follow: false\n"
                + "  host: hexlet.io\n"
                + "- proxy: 123.234.53.22\n"
                + "- timeout: 50\n"
                + "+ timeout: 20\n"
                + "+ verbose: null\n";
        assertThat(generate(path1, path2)).isEqualTo(expected);
    }
}
