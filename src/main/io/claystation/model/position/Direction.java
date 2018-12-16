package io.claystation.model.position;

import java.util.Arrays;

public enum Direction {

    NORTH("N", "WEST", "EAST"),
    EAST("E", "NORTH", "SOUTH"),
    SOUTH("S", "EAST", "WEST"),
    WEST("W", "SOUTH", "NORTH");

    private final String direction;
    private final String left;
    private final String right;

    Direction(final String direction, final String left, final String right) {
        this.direction = direction;
        this.left = left;
        this.right = right;
    }

    public static Direction parseFromString(final String direction) {
        return Arrays.stream(values()).filter(v -> v.direction.equals(direction)).findFirst().orElseThrow(() -> new IllegalArgumentException("Invalid character given as direction"));
    }

    public String getDirection() {
        return direction;
    }

    public Direction getLeft() {
        return Direction.valueOf(left);
    }

    public Direction getRight() {
        return Direction.valueOf(right);
    }
}
