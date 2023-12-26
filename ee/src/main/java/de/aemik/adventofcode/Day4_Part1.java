package de.aemik.adventofcode;

import java.util.Arrays;
import java.util.List;

public class Day4_Part1 {

    public static void main(String[] args) throws Exception {
        new Day4_Part1().start();
    }

    public void start() throws Exception {
        List<String> allLines = FileUtil.getLines("day4_part1.txt");
        allLines.stream().map(Card::new).forEach(System.out::println);

        final var result = allLines.stream().map(Card::new).map(Card::getPoints).reduce(0.0, Double::sum);

        System.out.println(result);
    }

    public class Card {

        String number;
        List<String> winningCards;
        List<String> havingCards;

        public Card(String line) {
            final var splitGameAndCards = line.split(":");
            this.number = splitGameAndCards[0].replace("Card", "").trim();

            final var splitWinningAndHavingCards = splitGameAndCards[1].split("\\|");
            final var splitWinningCards = splitWinningAndHavingCards[0].trim().replace("  ", " ").split(" ");
            final var splitHavingCards = splitWinningAndHavingCards[1].trim().replace("  ", " ").split(" ");

            this.winningCards = Arrays.stream(splitWinningCards).map(String::trim).toList();
            this.havingCards = Arrays.stream(splitHavingCards).map(String::trim).toList();
        }

        long getWinCount() {
            return this.havingCards.stream().filter(x -> this.winningCards.contains(x)).count();
        }

        double getPoints() {
            if (getWinCount() == 0) return 0;
            return Math.pow(2, getWinCount()-1);
        }

        @Override
        public String toString() {
            return "Card{" +
                    "number='" + number + '\'' +
                    ", winningCards=" + winningCards +
                    ", havingCards=" + havingCards +
                    ", count=" + getWinCount() +
                    ", points=" + getPoints() +
                    '}';
        }
    }

}