package de.aemik.adventofcode;

import java.util.List;

public class Day1_Part1 {

    public static void main(String[] args) throws Exception {
        List<String> allLines = FileUtil.getLines("day1_part1.txt");

        var result = allLines.stream()
                .map(s -> s.replaceAll("\\D+", ""))
                .map(s -> Integer.parseInt(s.substring(0, 1) + s.substring(s.length() - 1, s.length())))
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println(result);
    }

}