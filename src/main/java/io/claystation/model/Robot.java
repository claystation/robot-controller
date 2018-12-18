package io.claystation.model;

import io.claystation.model.position.Position;

public class Robot {

    private Position position;
    private final Room room;

    public Robot(final Position position, final Room room) {
        this.position = position;
        this.room = room;
    }

    public Position getCurrentPosition() {
        return position;
    }

    public void turnLeft() {
        position = position.getLeft();
    }

    public void turnRight() {
        position = position.getRight();
    }

    public void moveForward() {
        if (room.validPosition(position.getForward())) {
            position = position.getForward();
        }
    }

}