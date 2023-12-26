package de.aemik.adventofcode;

import java.util.ArrayList;
import java.util.List;

public class Day2_Part1 {

    public static void main(String[] args) throws Exception {
        List<String> allLines = FileUtil.getLines("day2_part1.txt");

        final var day2Part1 = new Day2_Part1();
        final var games = day2Part1.linesToGames(allLines);
        final var result = day2Part1.getResult(games);

        // games.stream().forEach(System.out::println);
        System.out.println(result);

    }

    List<Game> linesToGames(List<String> allLines) {
        List<Game> games = new ArrayList<>();
        for (String line : allLines) {

            // Game
            String[] gameAndBags = line.split(":");
            String gameId = gameAndBags[0].replaceAll("Game ", "");
            String[] allBagsString = gameAndBags[1].trim().split(";");

            List<Bag> bags = new ArrayList();
            for (String bagString : allBagsString) {
                String[] oneBagString = bagString.trim().split(",");

                List<Cubes> cubesList = new ArrayList<>();
                for (String oneCube : oneBagString) {
                    String[] numberAndColorOfCubeString = oneCube.trim().split(" ");
                    Cubes cubes = new Cubes(Integer.parseInt(numberAndColorOfCubeString[0]), numberAndColorOfCubeString[1]);
                    cubesList.add(cubes);
                }

                bags.add(new Bag(cubesList));
            }

            Game game = new Game(Integer.parseInt(gameId), bags);
            games.add(game);
        }

        return games;
    }

    private int getResult(List<Game> games) {
        int result = 0;

        for (Game game : games) {
            boolean isCorrect = true;
            for (Bag bag : game.bags()) {
                if (!isCorrect) break;
                for (Cubes cubes : bag.cubesList()) {
                    if (!isCorrect) break;
                    if (cubes.color().equals("red") && cubes.count > 12) {
                        System.out.println("RED TO HIGH: " + game);
                        isCorrect = false;
                    } else if (cubes.color().equals("green") && cubes.count > 13) {
                        System.out.println("GREEN TO HIGH: " + game);
                        isCorrect = false;
                    } else if (cubes.color().equals("blue") && cubes.count > 14) {
                        System.out.println("BLUE TO HIGH: " + game);
                        isCorrect = false;
                    }
                }

            }
            if (isCorrect) {
                System.out.println("CORRECT: " + game);
                result = result + game.id();
            }
        }

        return result;
    }


    public record Game(int id, List<Bag> bags) {};

    public record Bag(List<Cubes> cubesList) {};

    public record Cubes(int count, String color) {};

}