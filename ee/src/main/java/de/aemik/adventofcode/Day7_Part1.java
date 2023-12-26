package de.aemik.adventofcode;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day7_Part1 {

    public static void main(String[] args) throws Exception {
        new Day7_Part1().start();
    }

    private void start() throws Exception {
         List<String> allLines = FileUtil.getLines("day7_part1.txt");

        //List<String> allLines = List.of("32T3K 765", "T55J5 684", "KK677 28", "KTJJT 220", "QQQJA 483");

        final var sorted = allLines.stream().map(Hand::new).sorted().toList();

        int result = 0;
        for (int i = 0; i < sorted.size(); i++) {
            final var hand = sorted.get(i);

            int rank = i+1;
            result = result + (hand.getBid() * rank);
            System.out.println(hand + " --> " + result + "+" + rank + "*" + hand.getBid() +"="+ result);
        }

        System.out.println(result);
    }

    public class Hand implements Comparable<Hand>{

        private char[] cards;
        private int bid;
        private Handtypes type;

        public int getBid() {
            return bid;
        }

        public Hand(String line) {
            final var split = line.split(" ");
            cards = split[0].toCharArray();
            bid = Integer.parseInt(split[1]);

            this.type = calculateType();
        }

        private Map getCardCount() {
            Map<Character, Integer> map = new HashMap<>();
            for (char card : cards) {
                map.put(card, map.getOrDefault(card, 0).intValue() + 1);
            }
            return map;
        }

        private Handtypes calculateType() {
            final var cardCountMap = getCardCount();
            final var values = cardCountMap.values();

            if (values.size() == 1 && values.contains(5)) return Handtypes.FIVE_OF_KIND;
            if (values.size() == 2 && values.contains(4)) return Handtypes.FOUR_OF_KIND;
            if (values.size() == 2 && values.contains(3) && values.contains(2)) return Handtypes.FULL_HOUSE;
            if (values.size() == 3 && values.contains(3)) return Handtypes.THREE_OF_KIND;
            if (values.size() == 3 && values.contains(2) && values.contains(2)) return Handtypes.TWO_PAIR;
            if (values.size() == 4 && values.contains(2)) return Handtypes.ONE_PAIR;

            return Handtypes.HIGH_CARD;
        }

        public Handtypes getType() {
            return type;
        }

        public char[] getCards() {
            return cards;
        }

        @Override
        public String toString() {
            return "Hand{" +
                    "cards='" + new String(cards) + '\'' +
                    ", bid=" + bid +
                    ", map=" + getCardCount() +
                    ", type=" + getType() +
                    '}';
        }

        @Override
        public int compareTo(Hand o) {
            if (this.getType().rank == o.getType().rank) {
                for (int i = 0; i < 5; i++) {
                    int rank1 = cardRankMap.get(this.getCards()[i]);
                    int rank2 = cardRankMap.get(o.getCards()[i]);
                    if (rank1 != rank2) {
                        return rank2 - rank1;
                    }
                }
            }
            return o.getType().rank - this.getType().rank;
        }
    }

    private static final Map<Character, Integer> cardRankMap;
    static {
        Map<Character, Integer> aMap = new HashMap<>();
        aMap.put('A', 1);
        aMap.put('K', 2);
        aMap.put('Q', 3);
        aMap.put('J', 4);
        aMap.put('T', 5);
        aMap.put('9', 6);
        aMap.put('8', 7);
        aMap.put('7', 8);
        aMap.put('6', 9);
        aMap.put('5', 10);
        aMap.put('4', 11);
        aMap.put('3', 12);
        aMap.put('2', 13);
        cardRankMap = Collections.unmodifiableMap(aMap);
    }


    public enum Handtypes {
        FIVE_OF_KIND(1),
        FOUR_OF_KIND(2),
        FULL_HOUSE(3),
        THREE_OF_KIND(4),
        TWO_PAIR(5),
        ONE_PAIR(6),
        HIGH_CARD(7);

        public int rank;
        Handtypes(int rank) {
            this.rank = rank;
        }
    }


}