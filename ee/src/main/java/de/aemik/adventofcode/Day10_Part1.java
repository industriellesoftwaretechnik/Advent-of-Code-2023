package de.aemik.adventofcode;


import de.aemik.adventofcode.day10.MatrixHandler;
import de.aemik.adventofcode.day10.Orientation;

import java.util.List;

public class Day10_Part1 {

    public static void main(String[] args) throws Exception {
        new Day10_Part1().start("day10_part1.txt");
    }

    public void start(String filename) throws Exception {
        final var lines = FileUtil.getLines(filename);
        final var matrixHandler = new MatrixHandler(lines);

        final var matrix = matrixHandler.getMatrix();
        final var startPipe = findStartPipe(matrix);
        System.out.println("Start: " + startPipe);

        System.out.println(goNext(matrix, startPipe, Orientation.NORTH, 0) / 2);
    }

    private int goNext(char[][] matrix, Pipe pipe, Orientation comeFrom, int counter) {
        counter++;
        //System.out.println("current Pipe: " + pipe + " come from: " + comeFrom);

        if (pipe.c() != 'S') {
            matrix[pipe.y][pipe.x] = '#';
        }


        int y = pipe.y();
        int x = pipe.x();

        switch (comeFrom) {
            case NORTH -> y++;
            case SOUTH -> y--;
            case EAST -> x--;
            case WEST -> x++;
        }

        char nextChar = matrix[y][x];
        Pipe nextPipe = new Pipe(y, x, nextChar);
        Orientation comeFromNext = null;

        if (nextChar == '-') {
            comeFromNext = comeFrom == Orientation.WEST ? Orientation.WEST : Orientation.EAST;
        } else if (nextChar == '|') {
            comeFromNext = comeFrom == Orientation.NORTH ? Orientation.NORTH : Orientation.SOUTH;
        } else if (nextChar == '7') {
            comeFromNext = comeFrom == Orientation.WEST ? Orientation.NORTH : Orientation.EAST;
        } else if (nextChar == 'J') {
            comeFromNext = comeFrom == Orientation.WEST ? Orientation.SOUTH : Orientation.EAST;
        } else if (nextChar == 'L') {
            comeFromNext = comeFrom == Orientation.NORTH ? Orientation.WEST : Orientation.SOUTH;
        } else if (nextChar == 'F') {
            comeFromNext = comeFrom == Orientation.SOUTH ? Orientation.WEST : Orientation.NORTH;
        } else if (nextChar == 'S') {
            new MatrixHandler(matrix).print();
            return counter;
        } else {
            throw new RuntimeException("invalid char: " + nextChar);
        }

        System.out.println("next Pipe: " + nextPipe + " with come from: " + comeFromNext);

        return goNext(matrix, nextPipe, comeFromNext, counter);
    }

    private Pipe findStartPipe(char[][] matrix) {
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                if (matrix[y][x] == 'S') {
                    return new Pipe(y, x, matrix[y][x]);
                }
            }
        }
        throw new RuntimeException("Startpoint not found!");
    }

    record Pipe(int y, int x, char c) {}


}