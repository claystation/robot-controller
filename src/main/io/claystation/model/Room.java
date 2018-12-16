package io.claystation.model;

import io.claystation.model.position.Position;

public class Room {

    private final int width;
    private final int height;

    public Room(final int width, final int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean validPosition(final Position position) {
        final int x = position.getX();
        final int y = position.getY();
        return x >= 0 && y >= 0 && x < width && y < height;
    }
}
