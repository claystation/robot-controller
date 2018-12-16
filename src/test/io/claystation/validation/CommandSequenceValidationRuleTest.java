package io.claystation.validation;

import io.claystation.exception.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandSequenceValidationRuleTest {

    private CommandSequenceValidationRule commandSequenceValidationRule;

    @BeforeEach
    void setUp() {
        commandSequenceValidationRule = new CommandSequenceValidationRule();
    }

    @Test
    void validSequenceTest() {
        assertDoesNotThrow(() -> commandSequenceValidationRule.validate("LFFRFFLRFLFRF"));
        assertDoesNotThrow(() -> commandSequenceValidationRule.validate("FFRFLFFRRFFLF"));
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
        final Throwable exception = assertThrows(ValidationException.class, () -> commandSequenceValidationRule.validate(input));
        assertEquals("Command sequence is not in a valid format, one or more of the following 3 characters \"L F R\" without spaces: \"LFRFLFRLFFR\"", exception.getMessage());
    }

}