package de.aemik.adventofcode.day10;

public enum Orientation {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    static {
        NORTH.opposite = SOUTH;
        SOUTH.opposite = NORTH;
        EAST.opposite = WEST;
        WEST.opposite = EAST;
    }

    public Orientation flip() {
        return opposite;
    }

    private Orientation opposite;
}


