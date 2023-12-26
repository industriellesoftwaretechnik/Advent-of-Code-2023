package de.aemik.adventofcode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day14_Part1 {

    public static void main(String[] args) throws Exception {
        //new Day14_Part1().start("day14_example.txt");
        new Day14_Part1().start("day14_part1.txt");
    }

    public void start(String filename) throws Exception {
        final var lines = FileUtil.getLines(filename);
        final var matrix = getMatrix(lines);

        print(matrix);
        System.out.println();
        calc(matrix);
    }

    public void calc(char[][] matrix) {
        // char[][] newMatrix = new char[yLength][xLength];

        int result = 0;
        for (int y = 0; y < matrix.length; y++) {
            char[] line = new char[matrix.length];
            for (int x = 0; x < matrix[y].length; x++) {
                line[x] = matrix[x][y];
            }
            result = result + checkLine(line);
        }

        System.out.println("Result: " + result);
    }

    public int checkLine(char[] line) {
        System.out.print(line);
        System.out.print(" --> ");

        int pointCounter = 0;
        for (int i = 0; i < line.length; i++) {
            if (line[i] == 'O') {
                if (pointCounter > 0) {
                    int newPos = i-pointCounter;
                    swap(line, newPos, i);
                    pointCounter = 0;
                    i = newPos;
                }
            } else if (line[i] == '#') {
                pointCounter = 0;
            } else if (line[i] == '.') {
                pointCounter++;
            } else {
                throw new RuntimeException("invalid sign");
            }
        }

        System.out.print(line);
        System.out.print(" --> ");

        int result = 0;
        for (int i = line.length; i > 0; i--) {
            if (line[line.length - i] == 'O') {
                result = result + i;
            }
        }

        System.out.println(result);

        return result;
    }

    public static final void swap (char[] a, int i, int j) {
        char t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private char[][] getMatrix(List<String> lines) {
        int yLength = lines.size();
        int xLength = lines.get(0).length();

        char[][] matrix = new char[yLength][xLength];
        for (int y = 0; y < yLength; y++) {
            var line = lines.get(y);
            matrix[y] = line.toCharArray();
        }
        return matrix;
    }

    private void print(char[][] matrix) {
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                System.out.print(matrix[y][x]);
            }
            System.out.println();
        }
        System.out.println();
    }
}