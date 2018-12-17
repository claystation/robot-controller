package io.claystation.model;

import io.claystation.model.position.Direction;
import io.claystation.model.position.Position;
import io.claystation.model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    private Room room;

    @BeforeEach
    void setUp() {
        room = new Room(5, 5);
    }

    @Test
    void validPositionTest() {
        assertTrue(room.validPosition(createPosition(1, 2)));
        assertTrue(room.validPosition(createPosition(3, 2)));
        assertTrue(room.validPosition(createPosition(2, 3)));
        assertTrue(room.validPosition(createPosition(0, 0)));
        assertTrue(room.validPosition(createPosition(0, 1)));
        assertTrue(room.validPosition(createPosition(1, 0)));
    }

    @Test
    void invalidPositionTest() {
        assertFalse(room.validPosition(createPosition(-1, -2)));
        assertFalse(room.validPosition(createPosition(-1, 2)));
        assertFalse(room.validPosition(createPosition(4, -2)));
        assertFalse(room.validPosition(createPosition(5, 5)));
        assertFalse(room.validPosition(createPosition(1, 5)));
        assertFalse(room.validPosition(createPosition(5, 1)));
    }

    private Position createPosition(final int x, final int y) {
        return new Position(x, y, Direction.NORTH);
    }

}