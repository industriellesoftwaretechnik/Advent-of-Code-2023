package de.aemik.adventofcode.day3;

public record GearRatio(char[] top,
                        char[] left,
                        char[] right,
                        char[] bottom) {

    public void print() {
        System.out.println();
        System.out.println(top);
        System.out.print(left);
        System.out.print("*");
        System.out.println(right);
        System.out.println(bottom);
    }

}
