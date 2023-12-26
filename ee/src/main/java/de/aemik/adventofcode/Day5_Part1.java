package de.aemik.adventofcode;

import de.aemik.adventofcode.day5.FileReader;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Day5_Part1 {

    public static void main(String[] args) throws Exception {
        new Day5_Part1().start();
    }

    private void start() throws Exception {
        FileReader fileReader = new FileReader("day5_part1.txt");
        final var seedListLists = fileReader.getSeedListLists();
        final var seedIds = fileReader.getSeedIds();

        // final var resultPart1 = startPart1(seedListLists, seedIds);
        // if (resultPart1 != 35) throw new RuntimeException("WRONG");
        // if (resultPart1 != 324724204) throw new RuntimeException("WRONG");

        // part 2
        long lowest = Long.MAX_VALUE;
        for (int i = 0; i < seedIds.size(); i = i+2) {
            long from = seedIds.get(i);
            long range  = seedIds.get(i+1);
            for (long j = from; j < from + range; j++) {
                long res = startPart1(seedListLists, List.of(j));
                if (res < lowest) lowest = res;
            }
        }

        // if (resultPart2 != 46) throw new RuntimeException("WRONG");
        System.out.println(lowest);
    }

    private long startPart1(List<List<SeedMap>> seedListLists, List<Long> seedIds) throws Exception {

        List<Long> lastLocations = new ArrayList<>();
        var firstMap = seedListLists.get(0);
        for (Long seedId : seedIds) {
            //System.out.println();
            //System.out.println("handle seedId: " + seedId);

            long lastLocation = seedId;
            for (List<SeedMap> seedListList : seedListLists) {
                lastLocation = getLocation(seedListList, lastLocation);
              //  System.out.println("for list: " + seedListList + " --> " + lastLocation);
            }

            lastLocations.add(lastLocation);
          //  System.out.println("Result: " + lastLocation);
        }

        long lowest = lastLocations.get(0);
        for (long number : lastLocations) {
            if (number < lowest) {
                lowest = number;
            }
        }

        // System.out.println("Final Result: " + lowest);

       return lowest;
    }


    private long getLocation(List<SeedMap> map, long key) {
        final var seedMap = map.stream().filter(x -> key >= x.source && key <= x.source + x.length).findFirst().orElse(null);
        if (seedMap == null) {
            return key;
        } else {
            return seedMap.dest + (key - seedMap.source);
        }
    }

    public record SeedMap(String name, long dest, long source, long length) {

    }

}