package io.claystation.model.position;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DirectionTest {

    @Test
    void parseDirectionFromStringTest() {
        assertEquals(Direction.NORTH, Direction.parseFromString("N"));
        assertEquals(Direction.EAST, Direction.parseFromString("E"));
        assertEquals(Direction.SOUTH, Direction.parseFromString("S"));
        assertEquals(Direction.WEST, Direction.parseFromString("W"));
    }

    @Test
    void exceptionThrownWithInvalidDirectionTest() {
        final Throwable exception = assertThrows(IllegalArgumentException.class, () -> Direction.parseFromString("NOT A DIRECTION"));
        assertEquals("Invalid character given as direction", exception.getMessage());
    }
}