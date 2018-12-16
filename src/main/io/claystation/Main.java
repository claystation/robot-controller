package io.claystation;

import io.claystation.creator.CommandSequenceCreator;
import io.claystation.creator.RobotCreator;
import io.claystation.creator.RoomCreator;
import io.claystation.io.ConsoleInputReader;
import io.claystation.io.Writer;
import io.claystation.model.Robot;
import io.claystation.model.Room;
import io.claystation.model.command.CommandSequence;

public class Main {

    private static final ConsoleInputReader reader = new ConsoleInputReader();
    private static final Writer writer = new Writer();

    public static void main(final String[] args) {
        writer.writeString("Welcome to the robot controller");
        final Room room = new RoomCreator(reader, writer).createRoom();
        final Robot robot = new RobotCreator(reader, writer, room).createRobot();
        final CommandSequence commandSequence = new CommandSequenceCreator(reader, writer).createSequence();

        final Controller controller = new Controller(room, robot, commandSequence);
        controller.execute();

        writer.writeString(controller.report());
    }
}
