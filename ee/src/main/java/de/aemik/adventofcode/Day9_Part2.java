package de.aemik.adventofcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day9_Part2 {

    public static void main(String[] args) throws Exception {
        final var result1 = new Day9_Part2().start(List.of("0 3 6 9 12 15"));
        if (result1 != -3) throw new RuntimeException();

        final var result2 = new Day9_Part2().start(List.of("1 3 6 10 15 21"));
        if (result2 != 0) throw new RuntimeException();

        final var result3 = new Day9_Part2().start(List.of("10 13 16 21 30 45"));
        if (result3 != 5) throw new RuntimeException();

        final var result4 = new Day9_Part2().start(FileUtil.getLines("day9_part1.txt"));
        System.out.println(result4);
        //if (result4 != 1731106378) throw new RuntimeException();
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
            return numbers.get(0);
        } else {
            final var firstNextNumber = handleNumbers(nextNumbers);
            final var firstNumber = numbers.get(0) - firstNextNumber;

            return firstNumber;
        }
    }

}