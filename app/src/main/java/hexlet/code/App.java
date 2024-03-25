package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff beta",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {
    @Option(names = {"-f", "--format"}, paramLabel = "format", description = "output format [default: stylish]")
    String format = "stylish";
    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    static String filepath1;
    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    static String filepath2;

    public static void main(String[] args) throws IOException {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        String result = Differ.generate(filepath1, filepath2);
        System.out.println(result);
        return 0;
    }
}
