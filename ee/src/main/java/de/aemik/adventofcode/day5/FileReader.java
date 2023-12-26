package de.aemik.adventofcode.day5;

import de.aemik.adventofcode.Day5_Part1;
import de.aemik.adventofcode.FileUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileReader {

    private List<Long> seedIds = null;
    private List<List<Day5_Part1.SeedMap>> seedListLists = new ArrayList<>();

    public FileReader(String filename) throws Exception {
        List<String> allLines = FileUtil.getLines(filename);
        readLines(allLines);
    }

    private void readLines(List<String> allLines) {

        List<Day5_Part1.SeedMap> lastSeedMap = null;
        String lastMapName = null;

        for (String allLine : allLines) {
            if (allLine.startsWith("seeds: ")) {
                seedIds = Arrays.stream(allLine
                        .replaceAll("seeds: ", "")
                        .split(" ")).map(Long::parseLong).collect(Collectors.toList());

            } else if (allLine.endsWith(" map:")) {
                lastSeedMap = new ArrayList<>();
                lastMapName = allLine;
            } else if (allLine.isEmpty() && lastMapName != null) {
                System.out.println(lastMapName);
                System.out.println(lastSeedMap);

                seedListLists.add(lastSeedMap);
                lastMapName = null;
            } else if (lastMapName != null) {
                final var split = allLine.trim().split(" ");
                lastSeedMap.add(new Day5_Part1.SeedMap(
                        lastMapName,
                        Long.parseLong(split[0]),
                        Long.parseLong(split[1]),
                        Long.parseLong(split[2])));
            }
        }
        System.out.println(lastMapName);
        System.out.println(lastSeedMap);
    }

    public List<Long> getSeedIds() {
        return seedIds;
    }

    public List<List<Day5_Part1.SeedMap>> getSeedListLists() {
        return seedListLists;
    }
}
