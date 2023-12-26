package de.aemik.adventofcode;


import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Day15_Part1 {

    public static void main(String[] args) throws Exception {
        //new Day15_Part1().start("day15_example.txt"); // 1320
        //new Day15_Part1().start("day15_part1.txt");

        System.out.println(new Day15_Part1().getHash("qp"));
    }

    public void start(String filename) throws Exception {
        final var lines = FileUtil.getLines(filename);
        int result = Arrays.stream(lines.get(0).split(",")).mapToInt(this::getHash).sum();
        System.out.println(result);
    }

    int getHash(String seq) {
        int curVal = 0;
        for (char c : seq.toCharArray()) {
            curVal += c;
            curVal *= 17;
            curVal %= 256;
        }
        return curVal;
    }


}