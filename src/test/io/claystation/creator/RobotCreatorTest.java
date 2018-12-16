package io.claystation.creator;

import io.claystation.io.Writer;
import io.claystation.model.Robot;
import io.claystation.model.Room;
import io.claystation.model.position.Direction;
import io.claystation.testutil.MockInputReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class RobotCreatorTest {

    private RobotCreator robotCreator;
    private MockInputReader mockInputReader;

    @BeforeEach
    void setUp() {
        mockInputReader = new MockInputReader();
        robotCreator = new RobotCreator(mockInputReader, new Writer(), new Room(5, 5));
    }

    @Test
    void validInputTest() {
        mockInputReader.addInput("1 2 N");
        final Robot robot = robotCreator.createRobot();
        assertNotNull(robot);

        assertEquals(1, robot.getCurrentPosition().getX());
        assertEquals(2, robot.getCurrentPosition().getY());
        assertEquals(Direction.NORTH, robot.getCurrentPosition().getDirection());
    }

    @Test
    void invalidAndValidInputTest() {
        mockInputReader.setInput(Arrays.asList("INVALID", "Mode invalid", "6 6 N", "4 4 S"));
        final Robot robot = robotCreator.createRobot();
        assertNotNull(robot);

        assertEquals(4, robot.getCurrentPosition().getX());
        assertEquals(4, robot.getCurrentPosition().getY());
        assertEquals(Direction.SOUTH, robot.getCurrentPosition().getDirection());
    }

    @Test
    void invalidInputTest() {
        mockInputReader.addInput("ASD INVALID");
        assertThrows(IndexOutOfBoundsException.class, () -> robotCreator.createRobot());
    }

    @Test
    void invalidPositionTest() {
        mockInputReader.addInput("6 6 N");
        assertThrows(IndexOutOfBoundsException.class, () -> robotCreator.createRobot());
    }

}