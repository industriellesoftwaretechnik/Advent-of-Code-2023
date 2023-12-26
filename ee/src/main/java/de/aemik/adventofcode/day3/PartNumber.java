package de.aemik.adventofcode.day3;

import java.util.Arrays;

public record PartNumber(int number,
                         char[] top,
                         char left,
                         char right,
                         char[] bottom) {

    public boolean isSurroundedBySymbol() {
        for (char c : top) {
            if (c != '.') return true;
        }
        if (left != '.') return true;
        if (right != '.') return true;
        for (char c : bottom) {
            if (c != '.') return true;
        }
        return false;
    }

    public void print() {
        System.out.println();
        System.out.println(top);
        System.out.print(left);
        System.out.print(number);
        System.out.print(right);
        System.out.println(" --> " + isSurroundedBySymbol());
        System.out.println(bottom);
        System.out.println();
    }

}
