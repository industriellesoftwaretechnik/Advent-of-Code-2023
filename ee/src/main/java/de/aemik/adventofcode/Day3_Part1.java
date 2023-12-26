package de.aemik.adventofcode;

import de.aemik.adventofcode.day3.MatrixCreator;
import de.aemik.adventofcode.day3.SchemaEngine;

public class Day3_Part1 {

    public static void main(String[] args) throws Exception {
        final var lines = FileUtil.getLines("day3_part1.txt");

        final var matrixCreator = new MatrixCreator(lines);
        matrixCreator.print();

        final var schemaEngine = new SchemaEngine(matrixCreator.getMatrix());
        System.out.println(schemaEngine.calculatePart1());
    }

}