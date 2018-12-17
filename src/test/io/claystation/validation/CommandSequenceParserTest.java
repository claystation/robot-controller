package io.claystation.validation;

import io.claystation.exception.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandSequenceParserTest {

    private CommandSequenceParser commandSequenceParser;

    @BeforeEach
    void setUp() {
        commandSequenceParser = new CommandSequenceParser();
    }

    @Test
    void validSequenceTest() {
        assertDoesNotThrow(() -> commandSequenceParser.parse("LFFRFFLRFLFRF"));
        assertDoesNotThrow(() -> commandSequenceParser.parse("FFRFLFFRRFFLF"));
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
        final Throwable exception = assertThrows(ParseException.class, () -> commandSequenceParser.parse(input));
        assertEquals("Command sequence is not in a valid format, one or more of the following 3 characters \"L F R\" without spaces: \"LFRFLFRLFFR\"", exception.getMessage());
    }

}