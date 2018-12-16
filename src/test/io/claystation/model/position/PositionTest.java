package io.claystation.model.position;

import io.claystation.model.position.Direction;
import io.claystation.model.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    private Position initialPosition;

    @BeforeEach
    void setUp() {
        initialPosition = new Position(5, 5, Direction.NORTH);
    }

    @Test
    void moveForwardTest() {
        final Position newPosition = initialPosition.getForward();

        assertEquals(5, newPosition.getX());
        assertEquals(4, newPosition.getY());
        assertEquals(Direction.NORTH, newPosition.getDirection());
    }

    @Test
    void turnLeftTest() {
        final Position newPositionWest = initialPosition.getLeft();
        assertEquals(Direction.WEST, newPositionWest.getDirection());
        final Position newPositionSouth = newPositionWest.getLeft();
        assertEquals(Direction.SOUTH, newPositionSouth.getDirection());
        final Position newPositionEast = newPositionSouth.getLeft();
        assertEquals(Direction.EAST, newPositionEast.getDirection());
    }

    @Test
    void turnRightTest() {
        final Position newPositionEast = initialPosition.getRight();
        assertEquals(Direction.EAST, newPositionEast.getDirection());
        final Position newPositionSouth = newPositionEast.getRight();
        assertEquals(Direction.SOUTH, newPositionSouth.getDirection());
        final Position newPositionWest = newPositionSouth.getRight();
        assertEquals(Direction.WEST, newPositionWest.getDirection());
    }

    @Test
    void toStringTest() {
        final Position positionNorth = new Position(5, 5, Direction.NORTH);
        assertEquals("5 5 N", positionNorth.toString());
        final Position positionEast = new Position(2, 6, Direction.EAST);
        assertEquals("2 6 E", positionEast.toString());
        final Position positionSouth = new Position(7, 1, Direction.SOUTH);
        assertEquals("7 1 S", positionSouth.toString());
        final Position positionWest = new Position(2, 4, Direction.WEST);
        assertEquals("2 4 W", positionWest.toString());
    }

}