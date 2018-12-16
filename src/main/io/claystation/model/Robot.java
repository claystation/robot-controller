package io.claystation.model;

import io.claystation.model.position.Position;

public class Robot {

    private Position position;

    public Robot(final Position position) {
        this.position = position;
    }

    public Position getCurrentPosition() {
        return position;
    }

    public Position getForwardPosition() {
        return position.getForward();
    }

    public void moveForward() {
        this.position = position.getForward();
    }

    public void turnLeft() {
        this.position = position.getLeft();
    }

    public void turnRight() {
        this.position = position.getRight();
    }

    public String reportPosition() {
        return position.toString();
    }
}
