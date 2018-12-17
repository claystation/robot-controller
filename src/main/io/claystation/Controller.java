package io.claystation;

import io.claystation.model.Robot;
import io.claystation.model.Room;
import io.claystation.model.command.Command;
import io.claystation.model.command.CommandSequence;

class Controller {

    private final Robot robot;
    private final CommandSequence commandSequence;

    Controller(final Robot robot, final CommandSequence commandSequence) {
        this.robot = robot;
        this.commandSequence = commandSequence;
    }

    void execute() {
        commandSequence.getCommands()
                .forEach(command -> {
                    if (Command.LEFT.equals(command)) {
                        robot.turnLeft();
                    } else if (Command.RIGHT.equals(command)) {
                        robot.turnRight();
                    } else {
                        robot.moveForward();
                    }
                });
    }
}
