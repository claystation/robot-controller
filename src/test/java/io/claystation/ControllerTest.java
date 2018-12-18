package io.claystation;

import io.claystation.model.Robot;
import io.claystation.model.Room;
import io.claystation.model.command.Command;
import io.claystation.model.command.CommandSequence;
import io.claystation.model.position.Direction;
import io.claystation.model.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    private Room room;

    @BeforeEach
    void setUp() {
        room = new Room(10, 10);
    }

    @Test
    void randomSequenceTest() {
        final Robot robot = new Robot(new Position(1, 2, Direction.NORTH), room);

        //RFRFFRFRF
        final CommandSequence commandSequence = new CommandSequence();
        commandSequence.addCommand(Command.RIGHT);
        commandSequence.addCommand(Command.FORWARD);

        commandSequence.addCommand(Command.RIGHT);
        commandSequence.addCommand(Command.FORWARD);
        commandSequence.addCommand(Command.FORWARD);

        commandSequence.addCommand(Command.RIGHT);
        commandSequence.addCommand(Command.FORWARD);

        commandSequence.addCommand(Command.RIGHT);
        commandSequence.addCommand(Command.FORWARD);

        final Controller controller = new Controller(robot);
        commandSequence.getCommands().forEach(controller::execute);

        assertEquals(1, robot.getCurrentPosition().getX());
        assertEquals(3, robot.getCurrentPosition().getY());
        assertEquals(Direction.NORTH, robot.getCurrentPosition().getDirection());
    }

    @Test
    void anotherRandomSequenceTest() {
        final Robot robot = new Robot(new Position(0, 0, Direction.EAST), room);

        //RFLFFLRF
        final CommandSequence commandSequence = new CommandSequence();
        commandSequence.addCommand(Command.RIGHT);
        commandSequence.addCommand(Command.FORWARD);

        commandSequence.addCommand(Command.LEFT);
        commandSequence.addCommand(Command.FORWARD);
        commandSequence.addCommand(Command.FORWARD);

        commandSequence.addCommand(Command.LEFT);
        commandSequence.addCommand(Command.RIGHT);
        commandSequence.addCommand(Command.FORWARD);

        final Controller controller = new Controller(robot);
        commandSequence.getCommands().forEach(controller::execute);

        assertEquals(3, robot.getCurrentPosition().getX());
        assertEquals(1, robot.getCurrentPosition().getY());
        assertEquals(Direction.EAST, robot.getCurrentPosition().getDirection());
    }

}