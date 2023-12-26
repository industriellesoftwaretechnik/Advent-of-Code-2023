package de.aemik.adventofcode;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileUtil {

    public static List<String> getLines(String textfile) throws Exception {
        return Files.readAllLines(Path.of(Day1_Part1.class.getClassLoader().getResource(textfile).toURI()));
    }
}
