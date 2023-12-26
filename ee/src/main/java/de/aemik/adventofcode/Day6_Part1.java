package de.aemik.adventofcode;

import java.util.List;

public class Day6_Part1 {

    public static void main(String[] args) throws Exception {
        List<Race> races = List.of(
                new Race(7,9),
                new Race(15,40),
                new Race(30, 200));
        final var result = new Day6_Part1().calc(races);
        if (result != 288) throw new RuntimeException();
        System.out.println(result);

        List<Race> races2 = List.of(
                new Race(47,282),
                new Race(70,1079),
                new Race(75, 1147),
                new Race(66, 1062));
        final var result2 = new Day6_Part1().calc(races2);
        if (result2 != 281600) throw new RuntimeException();
        System.out.println(result2);

        List<Race> races3 = List.of(new Race(71530,940200));
        final var result3 = new Day6_Part1().calc(races3);
        if (result3 != 71503) throw new RuntimeException();
        System.out.println(result3);

        List<Race> races4 = List.of(new Race(47707566,282107911471062L));
        final var result4 = new Day6_Part1().calc(races4);
        // if (result != 71503) throw new RuntimeException();
        System.out.println(result4);
    }

    public long calc(List<Race> races) {
        long result = 1;
        for (Race race : races) {
            result = result * getWaysToBeatTheRecord(race);
        }
        return result;
    }

    public long getWaysToBeatTheRecord(Race race) {
        long wins = 0;
        for (long t = 1; t < race.timeInSec(); t++) {
            long speed = t;
            long time = race.timeInSec() - speed;
            if (time * speed > race.distanceRecord) wins++;
        }
        return wins;
    }

    record Race(int timeInSec, long distanceRecord) {}

}