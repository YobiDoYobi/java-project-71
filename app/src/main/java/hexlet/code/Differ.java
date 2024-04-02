package hexlet.code;

import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

public final class Differ {
    //в тз такого требования по такой сигнатуре не было, но автотесты ругались
    //дефолтное значение я определяю средствали picocli вообще
    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        Map<String, Object> file1 = Parser.unserialize(getFileString(filepath1),
                FilenameUtils.getExtension(filepath1));
        Map<String, Object> file2 = Parser.unserialize(getFileString(filepath2),
                FilenameUtils.getExtension(filepath2));
        ArrayList<Map<String, Object>> diffList = DiffCreator.getDiff(file1, file2);
        return Formatter.getDiffFormat(diffList, format);
    }

    private static String getFileString(String filepath) throws IOException {
        Path fullPath = Paths.get(filepath).toAbsolutePath().normalize();
        return Files.readString(fullPath);
    }
}

