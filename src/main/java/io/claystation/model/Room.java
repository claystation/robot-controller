package io.claystation.model;

import io.claystation.model.position.Position;

public class Room {

    private final int width;
    private final int height;

    public Room(final int width, final int height) {
        if (width < 1 || height < 1) {
            throw new IllegalArgumentException("Room dimensions must be greater than 0");
        }

        this.width = width;
        this.height = height;
    }

    public boolean validPosition(final Position position) {
        final int x = position.getX();
        final int y = position.getY();
        return x >= 0 && y >= 0 && x < width && y < height;
    }
}
