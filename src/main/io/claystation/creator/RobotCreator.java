package io.claystation.creator;

import io.claystation.exception.ValidationException;
import io.claystation.io.InputReader;
import io.claystation.io.Writer;
import io.claystation.model.Robot;
import io.claystation.model.Room;
import io.claystation.model.position.Position;
import io.claystation.validation.PositionValidationRule;

public class RobotCreator extends Creator {

    private final Room room;

    public RobotCreator(final InputReader inputReader, final Writer writer, final Room room) {
        super(inputReader, writer);
        this.room = room;
    }

    public Robot createRobot() {
        writer.writeString("Please define the position and facing direction of the robot separated by spaces: \"x y n\"");
        final Position position;
        try {
            position = new PositionValidationRule().validate(inputReader.read());
        } catch (final ValidationException e) {
            writer.writeError(e);
            return this.createRobot();
        }
        if (!room.validPosition(position)) {
            writer.writeString("Given position is not valid in the room.");
            return this.createRobot();
        }

        return new Robot(position);
    }

}
