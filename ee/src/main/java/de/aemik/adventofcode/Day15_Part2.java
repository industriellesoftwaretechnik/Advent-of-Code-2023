package de.aemik.adventofcode;


import java.util.*;
import java.util.stream.Collectors;

public class Day15_Part2 {

    public static void main(String[] args) throws Exception {
        //new Day15_Part2().start("day15_example.txt"); // 1320 // 145
        new Day15_Part2().start("day15_part1.txt");

        //System.out.println(new Day15_Part2().getHash("qp"));
    }

    public void start(String filename) throws Exception {
        final var lines = FileUtil.getLines(filename);
        final var sequences = Arrays.stream(lines.get(0).split(",")).collect(Collectors.toList());

        final var collect = sequences.stream().map(x -> {
            String[] split = x.contains("-") ? x.split("-") : x.split("=");
            return new Lens(split[0], getHash(split[0]), x.contains("=") ? Integer.parseInt(split[1]) : 0, x.contains("="));
        }).collect(Collectors.toList());

        collect.stream().forEach(System.out::println);

        sort(collect);
    }

    public void sort(List<Lens> lenses) {
        Map<Integer, List<Lens>> boxes = new HashMap<>();
        for (int i = 0; i < 256; i++) {
            boxes.put(i, new ArrayList<>());
        }

        for (Lens lens : lenses) {
            final var boxStack = boxes.get(lens.box());
            if (!lens.add()) {
                final var first = boxStack.stream().filter(x -> Objects.equals(x.label(), lens.label())).findFirst().orElse(null);
                if (first != null) boxStack.remove(first);
            } else {
                final var first = boxStack.stream().filter(x -> Objects.equals(x.label(), lens.label())).findFirst().orElse(null);
                if (first != null) {
                    final var i = boxStack.indexOf(first);
                    boxStack.set(i, lens);
                } else {
                    boxStack.add(lens);
                }

            }
            System.out.println(boxes);
        }

        calc(boxes);
    }

    private void calc(Map<Integer, List<Lens>> boxes) {
        int result = 0;
        for (Integer box : boxes.keySet()) {
            for (int i = 0; i < boxes.get(box).size(); i++) {
                result = result + ((box+1) * (i+1) * boxes.get(box).get(i).focalLength());
                System.out.println("Lens: " + boxes.get(box).get(i) + " --> " + result);
            }
        }
        System.out.println(result);
    }

    private int getHash(String seq) {
        int curVal = 0;
        for (char c : seq.toCharArray()) {
            curVal += c;
            curVal *= 17;
            curVal %= 256;
        }
        return curVal;
    }

    record Lens(String label, int box, int focalLength, boolean add) {}

}