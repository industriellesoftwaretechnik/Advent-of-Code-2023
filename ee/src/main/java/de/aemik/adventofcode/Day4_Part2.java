package de.aemik.adventofcode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day4_Part2 {

    public static void main(String[] args) throws Exception {
        new Day4_Part2().start();
    }

    List<Card> cards;

    int cardCount = 0;

    public void start() throws Exception {
        List<String> allLines = FileUtil.getLines("day4_part1.txt");
        // allLines.stream().map(Card::new).forEach(System.out::println);

        this.cards = allLines.stream().map(Card::new).collect(Collectors.toList());
        this.cards.stream().forEach(this::handleCard);

        System.out.println("cardCount:" + cardCount);
    }

    private void handleCard(Card card) {
        cardCount++;
        final var wins = card.getWins();
        // System.out.println("Card Nummer=" + card.number + " with Wins=" + wins);
        for (int i = card.number + 1; i <= card.number + wins; i++) {
            // System.out.println(cards.get(i-1));
            handleCard(cards.get(i-1));
        }
    }

    public class Card {

        int number;
        List<String> winningCards;
        List<String> havingCards;

        public Card(String line) {
            final var splitGameAndCards = line.split(":");
            this.number = Integer.parseInt(splitGameAndCards[0].replace("Card", "").trim());

            final var splitWinningAndHavingCards = splitGameAndCards[1].split("\\|");
            final var splitWinningCards = splitWinningAndHavingCards[0].trim().replace("  ", " ").split(" ");
            final var splitHavingCards = splitWinningAndHavingCards[1].trim().replace("  ", " ").split(" ");

            this.winningCards = Arrays.stream(splitWinningCards).map(String::trim).toList();
            this.havingCards = Arrays.stream(splitHavingCards).map(String::trim).toList();
        }

        long getWins() {
            return this.havingCards.stream().filter(x -> this.winningCards.contains(x)).count();
        }

        @Override
        public String toString() {
            return "Card{" +
                    "number='" + number + '\'' +
                    ", winningCards=" + winningCards +
                    ", havingCards=" + havingCards +
                    ", count=" + getWins() +
                    '}';
        }
    }

}