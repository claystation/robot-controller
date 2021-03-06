package io.claystation.model;

import io.claystation.model.position.Direction;
import io.claystation.model.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        robot.turnLeft();
        assertEquals(Direction.WEST, robot.getCurrentPosition().getDirection());
        robot.turnLeft();
        assertEquals(Direction.SOUTH, robot.getCurrentPosition().getDirection());
        robot.turnLeft();
        assertEquals(Direction.EAST, robot.getCurrentPosition().getDirection());
        robot.turnLeft();
        assertEquals(Direction.NORTH, robot.getCurrentPosition().getDirection());
    }

    @Test
    void turnRightTest() {
        robot.turnRight();
        assertEquals(Direction.EAST, robot.getCurrentPosition().getDirection());
        robot.turnRight();
        assertEquals(Direction.SOUTH, robot.getCurrentPosition().getDirection());
        robot.turnRight();
        assertEquals(Direction.WEST, robot.getCurrentPosition().getDirection());
        robot.turnRight();
        assertEquals(Direction.NORTH, robot.getCurrentPosition().getDirection());
    }

    @Test
    void moveForwardTurningRightTest() {
        robot.moveForward();
        assertEquals(4, robot.getCurrentPosition().getY());
        robot.turnRight();
        robot.moveForward();
        assertEquals(6, robot.getCurrentPosition().getX());
        robot.turnRight();
        robot.moveForward();
        assertEquals(5, robot.getCurrentPosition().getY());
        robot.turnRight();
        robot.moveForward();
        assertEquals(5, robot.getCurrentPosition().getX());
    }

    @Test
    void moveForwardTurningLeftTest() {
        robot.moveForward();
        assertEquals(4, robot.getCurrentPosition().getY());
        robot.turnLeft();
        robot.moveForward();
        assertEquals(4, robot.getCurrentPosition().getX());
        robot.turnLeft();
        robot.moveForward();
        assertEquals(5, robot.getCurrentPosition().getY());
        robot.turnLeft();
        robot.moveForward();
        assertEquals(5, robot.getCurrentPosition().getX());
    }

    @Test
    void randomSequenceTest() {
        final Robot robot = new Robot(new Position(1, 2, Direction.NORTH), room);

        //RFRFFRFRF
        robot.turnRight();
        robot.moveForward();

        robot.turnRight();
        robot.moveForward();
        robot.moveForward();

        robot.turnRight();
        robot.moveForward();

        robot.turnRight();
        robot.moveForward();

        assertEquals(1, robot.getCurrentPosition().getX());
        assertEquals(3, robot.getCurrentPosition().getY());
        assertEquals(Direction.NORTH, robot.getCurrentPosition().getDirection());
    }

    @Test
    void anotherRandomSequenceTest() {
        final Robot robot = new Robot(new Position(0, 0, Direction.EAST), room);

        //RFLFFLRF
        robot.turnRight();
        robot.moveForward();

        robot.turnLeft();
        robot.moveForward();
        robot.moveForward();

        robot.turnLeft();
        robot.turnRight();
        robot.moveForward();

        assertEquals(3, robot.getCurrentPosition().getX());
        assertEquals(1, robot.getCurrentPosition().getY());
        assertEquals(Direction.EAST, robot.getCurrentPosition().getDirection());
    }
}