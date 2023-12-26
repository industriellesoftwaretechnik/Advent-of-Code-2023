package de.aemik.adventofcode.day3;

import java.util.List;
import java.util.stream.IntStream;

public class MatrixCreator {

    final char[][] matrix;

    public MatrixCreator(List<String> lines) {
        int xLength = lines.get(0).length();
        char[][] matrix = new char[lines.size() + 2][xLength + 2];

        matrix[0] = ".".repeat(xLength+2).toCharArray();
        for (int y = 0; y < lines.size(); y++) {
            var line = lines.get(y);
            line = '.' + line + '.';
            matrix[y+1] = line.toCharArray();
        }
        matrix[lines.size()+1] = ".".repeat(xLength+2).toCharArray();

        this.matrix = matrix;
    }

    public char[][] getMatrix() {
        return matrix;
    }

    public void print() {
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                System.out.print(matrix[y][x]);
            }
            System.out.println();
        }
    }
}
