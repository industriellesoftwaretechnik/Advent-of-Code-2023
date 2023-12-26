package de.aemik.adventofcode;

import java.util.*;
import java.util.stream.Collectors;

public class Day9_Part1 {

    public static void main(String[] args) throws Exception {
        final var result1 = new Day9_Part1().start(List.of("0 3 6 9 12 15"));
        if (result1 != 18) throw new RuntimeException();

        final var result2 = new Day9_Part1().start(List.of("1 3 6 10 15 21"));
        if (result2 != 28) throw new RuntimeException();

        final var result3 = new Day9_Part1().start(List.of("10 13 16 21 30 45"));
        if (result3 != 68) throw new RuntimeException();

        final var result4 = new Day9_Part1().start(FileUtil.getLines("day9_part1.txt"));
        if (result4 != 1731106378) throw new RuntimeException();
    }

    private int start(List<String> lines) {
        int result = 0;
        for (String line : lines) {
            result = result + handleLine(line);
        }
        return result;
    }

    private int handleLine(String line) {
        final var numbers = Arrays.stream(line.split(" ")).map(Integer::parseInt).toList();
        return handleNumbers(numbers);
    }

    private int handleNumbers(List<Integer> numbers) {
        // System.out.println(numbers);

        List<Integer> nextNumbers = new ArrayList<>();
        for (int i = 0; i < numbers.size()-1; i++) {
            nextNumbers.add(numbers.get(i+1) - numbers.get(i));
        }
        if (nextNumbers.stream().allMatch(x -> x == 0)) {
            // last number on numers list
            return numbers.get(numbers.size()-1);
        } else {
            return handleNumbers(nextNumbers) + numbers.get(numbers.size()-1);
        }
    }

}