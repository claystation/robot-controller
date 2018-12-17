package io.claystation;

import io.claystation.exception.ParseException;
import io.claystation.io.ConsoleInputReader;
import io.claystation.model.Robot;
import io.claystation.model.Room;
import io.claystation.model.command.CommandSequence;
import io.claystation.model.position.Position;
import io.claystation.validation.CommandSequenceParser;
import io.claystation.validation.PositionParser;
import io.claystation.validation.RoomParser;

import java.io.Writer;

public class Main {

    private static final ConsoleInputReader reader = new ConsoleInputReader();

    public static void main(final String[] args) {
        System.out.println("Welcome to the robot controller");

        try {
            final Room room = createRoom();
            final Robot robot = createRobot(room);
            final CommandSequence commandSequence = createCommandSequence();

            final Controller controller = new Controller(robot, commandSequence);
            controller.execute();

            System.out.println("Report: " + robot.getCurrentPosition());
        } catch (final ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    private static CommandSequence createCommandSequence() throws ParseException {
        System.out.println("Please define the sequence of commands with the following characters \"L F R\" without spaces: \"RFLFFLRF\"");
        return new CommandSequenceParser().parse(reader.read());
    }

    private static Room createRoom() throws ParseException {
        System.out.println("Please define the size of the room separated by a space: \"w h\"");
        return new RoomParser().parse(reader.read());
    }

    private static Robot createRobot(final Room room) throws ParseException {
        System.out.println("Please define the position and facing direction of the robot separated by spaces: \"X Y D\"");
        final Position position = new PositionParser().parse(reader.read());
        if (!room.validPosition(position)) {
            throw new ParseException("Given position is not valid in the room");
        }

        return new Robot(position, room);
    }
}
