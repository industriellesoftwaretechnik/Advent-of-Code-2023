package de.aemik.adventofcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day8_Part1 {

    public char[] getLeftRightCharArray() throws Exception {
        List<String> leftRightLines = FileUtil.getLines("day8_part1_A.txt");
        final String leftRightLine = leftRightLines.get(0);
        return  leftRightLine.toCharArray();
    }

    public Map<String, Tuple> getKoordinateMap() throws Exception {
        List<String> koordinateLines = FileUtil.getLines("day8_part1_B.txt");
        koordinateLines = koordinateLines
                .stream()
                .map(x -> x.replaceAll(" ", ""))
                .map(x -> x.replaceAll("\\(", ""))
                .map(x -> x.replaceAll("\\)", ""))
                .collect(Collectors.toList());

        Map<String, Tuple> koordinateMap = new HashMap<>();
        for (String koordinateLine : koordinateLines) {
            final String[] split = koordinateLine.split("=");
            final String[] splitLR = split[1].split(",");
            koordinateMap.put(split[0], new Tuple(splitLR[0], splitLR[1]));
        }

        return koordinateMap;
    }

    public static void main(String[] args) throws Exception {
        final var day8Part1 = new Day8_Part1();

        final var leftRightArray = day8Part1.getLeftRightCharArray();
        final Map<String, Tuple> koordinateMap = day8Part1.getKoordinateMap();

        int counter = 0;
        String lastKoordiante = "AAA";

        finish:
        for (int i = 0; i < 100; i++) {
            for (char lr : leftRightArray) {
                counter++;

                final var tuple = koordinateMap.get(lastKoordiante);

                if (lr == 'L') {
                    lastKoordiante = tuple.l();
                } else if (lr == 'R') {
                    lastKoordiante = tuple.r();
                } else {
                    throw new RuntimeException("lr invalid");
                }

                if (lastKoordiante.equals("ZZZ")) {
                    System.out.println(counter);
                    break finish;
                }
            }
        }

    }

    public record Tuple(String l, String r) {};

}