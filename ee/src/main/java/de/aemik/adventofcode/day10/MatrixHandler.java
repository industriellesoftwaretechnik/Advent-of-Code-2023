package de.aemik.adventofcode.day10;

import java.util.List;

public class MatrixHandler {

    final char[][] matrix;

    public MatrixHandler(char[][] matrix) {
        this.matrix = matrix;
    }

    public MatrixHandler(List<String> lines) {
        int yLength = lines.size();
        int xLength = lines.get(0).length();

        char[][] matrix = new char[yLength][xLength];

        for (int y = 0; y < yLength; y++) {
            var line = lines.get(y);
            matrix[y] = line.toCharArray();
        }

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
