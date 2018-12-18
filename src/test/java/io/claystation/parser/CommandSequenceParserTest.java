package io.claystation.parser;

import io.claystation.exception.ParseException;
import io.claystation.model.command.Command;
import io.claystation.model.command.CommandSequence;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandSequenceParserTest {

    @Test
    void validSequenceTest() {
        CommandSequence sequence = assertDoesNotThrow(() -> CommandSequenceParser.parse("LFFRFFLRFLFRF"));
        assertEquals(13, sequence.getCommands().size());
        assertEquals(Command.LEFT, sequence.getCommands().get(0));
        assertEquals(Command.FORWARD, sequence.getCommands().get(sequence.getCommands().size() - 1));

        CommandSequence anotherSequence = assertDoesNotThrow(() -> CommandSequenceParser.parse("FFRFLFFRRFFR"));
        assertEquals(12, anotherSequence.getCommands().size());
        assertEquals(Command.FORWARD, anotherSequence.getCommands().get(0));
        assertEquals(Command.RIGHT, anotherSequence.getCommands().get(anotherSequence.getCommands().size() - 1));
    }

    @Test
    void invalidSequenceTest() {
        assertThrowsAndVerifyErrorMessage("INVALIDSEQUENCE");
        assertThrowsAndVerifyErrorMessage("LFR LFR LFR");
        assertThrowsAndVerifyErrorMessage("LFRINVALID");
        assertThrowsAndVerifyErrorMessage("INVALIDLFR");
        assertThrowsAndVerifyErrorMessage("noncapital");
        assertThrowsAndVerifyErrorMessage("LFR3LFR");
    }

    private void assertThrowsAndVerifyErrorMessage(final String input) {
        final Throwable exception = assertThrows(ParseException.class, () -> CommandSequenceParser.parse(input));
        assertEquals("Command sequence is not in a valid format, one or more of the following 3 characters \"L F R\" without spaces: \"LFRFLFRLFFR\"", exception.getMessage());
    }

}