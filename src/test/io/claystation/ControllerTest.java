package io.claystation;

import io.claystation.model.Robot;
import io.claystation.model.Room;
import io.claystation.model.command.Command;
import io.claystation.model.command.CommandSequence;
import io.claystation.model.position.Direction;
import io.claystation.model.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ControllerTest {

    private Room room;
    private Robot robot;

    @BeforeEach
    void setUp() {
        room = new Room(5, 5);
        robot = new Robot(new Position(1, 2, Direction.NORTH), room);
    }

    @Test
    void executeSequenceTest() {
        final Controller controller = new Controller(robot, createSequence("RFRFFRFRF"));

        controller.execute();
        assertEquals("1 3 N", robot.getCurrentPosition().toString());
    }

    @Test
    void executeAnotherSequenceTest() {
        final Robot robot = new Robot(new Position(0, 0, Direction.EAST), room);
        final Controller controller = new Controller(robot, createSequence("RFLFFLRF"));

        controller.execute();
        assertEquals("3 1 E", robot.getCurrentPosition().toString());
    }

    @Test
    void executeImpossibleSequenceTest() {
        final Controller controller = new Controller(robot, createSequence("FFFFFFFFFFFF"));

        controller.execute();
        assertEquals("1 0 N", robot.getCurrentPosition().toString());
    }

    private CommandSequence createSequence(final String sequence) {
        final CommandSequence commandSequence = new CommandSequence();
        Arrays.stream(sequence.split(""))
                .map(Command::parseFromString)
                .forEach(commandSequence::addCommand);
        return commandSequence;
    }

}