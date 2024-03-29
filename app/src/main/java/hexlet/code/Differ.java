package hexlet.code;

import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

public final class Differ<T> {
    //в тз такого требования по такой сигнатуре не было, но автотесты ругались
    //дефолтное значение я определяю средствали picocli вообще
    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
    }

    public static <T> String generate(String filepath1, String filepath2, String format) throws IOException {
        Path fullPath1 = Paths.get(filepath1).toAbsolutePath().normalize();
        String fileString1 = Files.readString(fullPath1);
        Path fullPath2 = Paths.get(filepath2).toAbsolutePath().normalize();
        String fileString2 = Files.readString(fullPath2);

        Map<String, T> file1 = Parser.unserialize(fileString1,
                FilenameUtils.getExtension(fullPath1.getFileName().toString()));
        Map<String, T> file2 = Parser.unserialize(fileString2,
                FilenameUtils.getExtension(fullPath1.getFileName().toString()));
        ArrayList<Map<String, T>> diffList = DiffCreator.getDiff(file1, file2);
        return Formatter.getDiffFormat(diffList, format);
    }
}

