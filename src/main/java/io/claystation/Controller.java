package io.claystation;

import io.claystation.model.Robot;
import io.claystation.model.command.Command;

class Controller {

    private final Robot robot;

    Controller(final Robot robot) {
        this.robot = robot;
    }

    void execute(final Command command) {
        if (Command.LEFT.equals(command)) {
            robot.turnLeft();
        } else if (Command.RIGHT.equals(command)) {
            robot.turnRight();
        } else {
            robot.moveForward();
        }
    }

}
