package de.aemik.adventofcode.day3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SchemaEngine {

    private final char[][] matrix;
    private final int yMax;
    private final int xMax;

    public SchemaEngine(char[][] matrix) {
        this.matrix = matrix;
        this.yMax = matrix.length;
        this.xMax = matrix[0].length;
    }

    public int calculatePart1() {
        final List<PartNumber> partNumbers = new ArrayList<>();

        for (int y = 0; y < yMax; y++) {
            StringBuffer digitBuffer = new StringBuffer();
            for (int x = 0; x < xMax; x++) {
                var c = matrix[y][x];
                if (Character.isDigit(c)) {
                    digitBuffer.append(c);
                } else if (!digitBuffer.isEmpty() || (!digitBuffer.isEmpty() && (x == xMax-1))) {
                    partNumbers.add(createPartNumber(digitBuffer.toString(), y, x - digitBuffer.length()));
                    digitBuffer = new StringBuffer();
                }
            }
        }

        partNumbers.forEach(PartNumber::print);

        return partNumbers.stream().filter(x -> x.isSurroundedBySymbol()).map(x -> x.number()).reduce(0, Integer::sum);
    }

    private PartNumber createPartNumber(String digit, int y, int x) {
        return new PartNumber(
                Integer.parseInt(digit.toString()),
                Arrays.copyOfRange(matrix[y-1], x-1, x + digit.length()+1),
                matrix[y][x-1],
                matrix[y][x + digit.length()],
                Arrays.copyOfRange(matrix[y+1], x-1, x + digit.length()+1)
        );
    }

    public int calculatePart2() {
        final List<GearRatio> gearRatios = new ArrayList<>();

        for (int y = 0; y < yMax; y++) {
            for (int x = 0; x < xMax; x++) {
                var c = matrix[y][x];
                if (c == '*') {
                    gearRatios.add(createGearRatio(y,x));
                }
            }
        }

        gearRatios.forEach(GearRatio::print);

        return 0;
        // return partNumbers.stream().filter(x -> x.isSurroundedBySymbol()).map(x -> x.number()).reduce(0, Integer::sum);
    }

    private GearRatio createGearRatio(int y, int x) {
        return new GearRatio(
                Arrays.copyOfRange(matrix[y-1], x-3, x+3),
                Arrays.copyOfRange(matrix[y], x-3, x),
                Arrays.copyOfRange(matrix[y], x, x+3),
                Arrays.copyOfRange(matrix[y+1], x-3, x+3)
        );
    }
}
