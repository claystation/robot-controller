package io.claystation.validation;

import io.claystation.exception.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionValidationRuleTest {

    private PositionValidationRule positionValidationRule;

    @BeforeEach
    void setUp() {
        positionValidationRule = new PositionValidationRule();
    }

    @Test
    void validInputTest() {
        assertDoesNotThrow(() -> positionValidationRule.validate("1 2 N"));
        assertDoesNotThrow(() -> positionValidationRule.validate("0 0 E"));
    }

    @Test
    void invalidFormatTest() {
        assertThrowsAndVerifyErrorMessage("claystation");
        assertThrowsAndVerifyErrorMessage("abc abc abc");
        assertThrowsAndVerifyErrorMessage("5 5 north");
        assertThrowsAndVerifyErrorMessage("5 5 5");
        assertThrowsAndVerifyErrorMessage("5 5 a");
        assertThrowsAndVerifyErrorMessage("5 a a");
        assertThrowsAndVerifyErrorMessage("5 a 5");
        assertThrowsAndVerifyErrorMessage("a 5 a");
        assertThrowsAndVerifyErrorMessage("a a a");
    }

    @Test
    void nonCapitalDirectionCharactersTest() {
       assertThrowsAndVerifyErrorMessage("5 5 n");
       assertThrowsAndVerifyErrorMessage("5 5 e");
       assertThrowsAndVerifyErrorMessage("5 5 s");
       assertThrowsAndVerifyErrorMessage("5 5 w");
    }

    @Test
    void nonValidCoordinatesTest() {
        assertThrowsAndVerifyErrorMessage("-5 5 a");
        assertThrowsAndVerifyErrorMessage("5 -5 a");
    }

    @Test
    void invalidIntegerTest() {
        final Throwable exceptionX = assertThrows(ValidationException.class, () -> positionValidationRule.validate("2147483648 5 N"));
        assertEquals("Position X is not a valid number", exceptionX.getMessage());
        final Throwable exceptionY = assertThrows(ValidationException.class, () -> positionValidationRule.validate("5 2147483648 N"));
        assertEquals("Position Y is not a valid number", exceptionY.getMessage());
    }

    private void assertThrowsAndVerifyErrorMessage(final String input) {
        final Throwable exception = assertThrows(ValidationException.class, () -> positionValidationRule.validate(input));
        assertEquals("Position does not have a valid format, 2 positive numbers and one of the following characters \"N E S W\" separated with a space: \"5 5 N\"", exception.getMessage());
    }

}