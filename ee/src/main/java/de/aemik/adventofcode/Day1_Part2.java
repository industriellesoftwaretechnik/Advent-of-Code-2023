package de.aemik.adventofcode;

import java.util.ArrayList;
import java.util.List;

public class Day1_Part2 {

    public static void main(String[] args) throws Exception {
        List<String> allLines = FileUtil.getLines("day1_part2.txt");

        List<String> allLines2 = new ArrayList<>();
        String[] onedigit = new String[] {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        for (String line : allLines) {
            for (int i = 0; i < 10; i++) {
                String digitAsWord = onedigit[i];
                line = line.replaceAll(digitAsWord, digitAsWord + String.valueOf(i) + digitAsWord);
            }
            allLines2.add(line);
        }

        var result = allLines2.stream()
                .map(s -> s.replaceAll("\\D+", ""))
                .map(s -> Integer.parseInt(s.substring(0, 1) + s.substring(s.length() - 1, s.length())))
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println(result);
    }


}