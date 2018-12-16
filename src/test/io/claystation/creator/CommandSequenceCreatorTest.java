package io.claystation.creator;

import io.claystation.io.Writer;
import io.claystation.model.command.Command;
import io.claystation.model.command.CommandSequence;
import io.claystation.testutil.MockInputReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandSequenceCreatorTest {

    private CommandSequenceCreator commandSequenceCreator;
    private MockInputReader mockInputReader;

    @BeforeEach
    void setUp() {
        mockInputReader = new MockInputReader();
        commandSequenceCreator = new CommandSequenceCreator(mockInputReader, new Writer());
    }

    @Test
    void validInputTest() {
        mockInputReader.addInput("LFFRFRFRFF");
        final CommandSequence commandSequence = commandSequenceCreator.createSequence();
        assertNotNull(commandSequence);

        final ArrayList<Command> commands = commandSequence.getCommands();
        assertEquals(Command.LEFT, commands.get(0));
        assertEquals(Command.FORWARD, commands.get(commands.size() - 1));
    }

    @Test
    void invalidAndValidInputTest() {
        mockInputReader.setInput(Arrays.asList("INVALID", "ALSO INVALID", "LFFRFRFRFF"));
        final CommandSequence commandSequence = commandSequenceCreator.createSequence();
        assertNotNull(commandSequence);

        final ArrayList<Command> commands = commandSequence.getCommands();
        assertEquals(Command.LEFT, commands.get(0));
        assertEquals(Command.FORWARD, commands.get(commands.size() - 1));
    }

    @Test
    void invalidInputTest() {
        mockInputReader.addInput("INVALID");
        assertThrows(IndexOutOfBoundsException.class, () -> commandSequenceCreator.createSequence());
    }
}