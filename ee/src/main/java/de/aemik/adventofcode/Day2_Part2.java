package de.aemik.adventofcode;

import java.util.List;

public class Day2_Part2 {

    public static void main(String[] args) throws Exception {
        List<String> allLines = FileUtil.getLines("day2_part2.txt");

        final var day2Part1 = new Day2_Part1();
        final var games = day2Part1.linesToGames(allLines);

        final var day2Part2 = new Day2_Part2();
        final var result = day2Part2.getResult(games);

        // games.stream().forEach(System.out::println);
        System.out.println(result);

    }

    private int getResult(List<Day2_Part1.Game> games) {
        int result = 0;

        for (Day2_Part1.Game game : games) {

            int blue = 0;
            int red = 0;
            int green = 0;

            for (Day2_Part1.Bag bag : game.bags()) {
                for (Day2_Part1.Cubes cubes : bag.cubesList()) {
                    if (cubes.color().equals("red")) {
                        if (red < cubes.count()) red = cubes.count();
                    } else if (cubes.color().equals("green")) {
                        if (green < cubes.count()) green = cubes.count();
                    } else if (cubes.color().equals("blue")) {
                        if (blue < cubes.count()) blue = cubes.count();
                    }
                }
            }

            int gameResult = blue * red * green;
            result = result + gameResult;
        }

        return result;
    }


}