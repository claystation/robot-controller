package io.claystation.model;

import io.claystation.model.command.Command;
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

    public void execute(final Command command) {
        if (Command.LEFT.equals(command)) {
            turnLeft();
        } else if (Command.RIGHT.equals(command)) {
            turnRight();
        } else {
            moveForward();
        }
    }

    private void turnLeft() {
        position = position.getLeft();
    }

    private void turnRight() {
        position = position.getRight();
    }

    private void moveForward() {
        if (room.validPosition(position.getForward())) {
            position = position.getForward();
        }
    }

}