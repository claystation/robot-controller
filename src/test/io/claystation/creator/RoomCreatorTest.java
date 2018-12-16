package io.claystation.creator;

import io.claystation.io.Writer;
import io.claystation.model.Room;
import io.claystation.model.command.Command;
import io.claystation.model.command.CommandSequence;
import io.claystation.testutil.MockInputReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class RoomCreatorTest {

    private RoomCreator roomCreator;
    private MockInputReader mockInputReader;

    @BeforeEach
    void setUp() {
        mockInputReader = new MockInputReader();
        roomCreator = new RoomCreator(mockInputReader, new Writer());
    }

    @Test
    void validInputTest() {
        mockInputReader.addInput("5 5");
        final Room room = roomCreator.createRoom();
        assertNotNull(room);

        assertEquals(5, room.getWidth());
        assertEquals(5, room.getHeight());
    }

    @Test
    void invalidAndValidInputTest() {
        mockInputReader.setInput(Arrays.asList("INVALID", "ALSO INVALID", "5 5"));
        final Room room = roomCreator.createRoom();
        assertNotNull(room);

        assertEquals(5, room.getWidth());
        assertEquals(5, room.getHeight());
    }

    @Test
    void invalidInputTest() {
        mockInputReader.addInput("INVALID");
        assertThrows(IndexOutOfBoundsException.class, () -> roomCreator.createRoom());
    }

}