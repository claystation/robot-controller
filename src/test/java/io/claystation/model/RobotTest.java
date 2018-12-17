package io.claystation.model;

import io.claystation.model.command.Command;
import io.claystation.model.position.Direction;
import io.claystation.model.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RobotTest {

    private Robot robot;
    private Room room;

    @BeforeEach
    void setUp() {
        final Position initialPosition = new Position(5, 5, Direction.NORTH);
        room = new Room(10, 10);
        robot = new Robot(initialPosition, room);
    }

    @Test
    void turnLeftTest() {
        robot.execute(Command.LEFT);
        assertEquals(Direction.WEST, robot.getCurrentPosition().getDirection());
        robot.execute(Command.LEFT);
        assertEquals(Direction.SOUTH, robot.getCurrentPosition().getDirection());
        robot.execute(Command.LEFT);
        assertEquals(Direction.EAST, robot.getCurrentPosition().getDirection());
        robot.execute(Command.LEFT);
        assertEquals(Direction.NORTH, robot.getCurrentPosition().getDirection());
    }

    @Test
    void turnRightTest() {
        robot.execute(Command.RIGHT);
        assertEquals(Direction.EAST, robot.getCurrentPosition().getDirection());
        robot.execute(Command.RIGHT);
        assertEquals(Direction.SOUTH, robot.getCurrentPosition().getDirection());
        robot.execute(Command.RIGHT);
        assertEquals(Direction.WEST, robot.getCurrentPosition().getDirection());
        robot.execute(Command.RIGHT);
        assertEquals(Direction.NORTH, robot.getCurrentPosition().getDirection());
    }

    @Test
    void moveForwardTurningRightTest() {
        robot.execute(Command.FORWARD);
        assertEquals(4, robot.getCurrentPosition().getY());
        robot.execute(Command.RIGHT);
        robot.execute(Command.FORWARD);
        assertEquals(6, robot.getCurrentPosition().getX());
        robot.execute(Command.RIGHT);
        robot.execute(Command.FORWARD);
        assertEquals(5, robot.getCurrentPosition().getY());
        robot.execute(Command.RIGHT);
        robot.execute(Command.FORWARD);
        assertEquals(5, robot.getCurrentPosition().getX());
    }

    @Test
    void moveForwardTurningLeftTest() {
        robot.execute(Command.FORWARD);
        assertEquals(4, robot.getCurrentPosition().getY());
        robot.execute(Command.LEFT);
        robot.execute(Command.FORWARD);
        assertEquals(4, robot.getCurrentPosition().getX());
        robot.execute(Command.LEFT);
        robot.execute(Command.FORWARD);
        assertEquals(5, robot.getCurrentPosition().getY());
        robot.execute(Command.LEFT);
        robot.execute(Command.FORWARD);
        assertEquals(5, robot.getCurrentPosition().getX());
    }

    @Test
    void randomSequenceTest() {
        final Robot robot = new Robot(new Position(1, 2, Direction.NORTH), room);

        //RFRFFRFRF
        robot.execute(Command.RIGHT);
        robot.execute(Command.FORWARD);

        robot.execute(Command.RIGHT);
        robot.execute(Command.FORWARD);
        robot.execute(Command.FORWARD);

        robot.execute(Command.RIGHT);
        robot.execute(Command.FORWARD);

        robot.execute(Command.RIGHT);
        robot.execute(Command.FORWARD);

        assertEquals(1, robot.getCurrentPosition().getX());
        assertEquals(3, robot.getCurrentPosition().getY());
        assertEquals(Direction.NORTH, robot.getCurrentPosition().getDirection());
    }

    @Test
    void anotherRandomSequenceTest() {
        final Robot robot = new Robot(new Position(0, 0, Direction.EAST), room);

        //RFLFFLRF
        robot.execute(Command.RIGHT);
        robot.execute(Command.FORWARD);

        robot.execute(Command.LEFT);
        robot.execute(Command.FORWARD);
        robot.execute(Command.FORWARD);

        robot.execute(Command.LEFT);
        robot.execute(Command.RIGHT);
        robot.execute(Command.FORWARD);

        assertEquals(3, robot.getCurrentPosition().getX());
        assertEquals(1, robot.getCurrentPosition().getY());
        assertEquals(Direction.EAST, robot.getCurrentPosition().getDirection());
    }
}