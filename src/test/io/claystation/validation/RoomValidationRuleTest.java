package io.claystation.validation;

import io.claystation.exception.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomValidationRuleTest {

    private RoomValidationRule roomValidationRule;

    @BeforeEach
    void setUp() {
        roomValidationRule = new RoomValidationRule();
    }

    @Test
    void validInputTest() {
        assertDoesNotThrow(() -> roomValidationRule.validate("5 5"));
    }

    @Test
    void validateNonNumericInputTest() {
        final Throwable exception = assertThrows(ValidationException.class, () -> roomValidationRule.validate("claystation"));
        assertEquals("Room dimension does not have a valid format, 2 positive numbers separated with a space: \"10 10\"", exception.getMessage());
    }

    @Test
    void validateInvalidWidthTest() {
        final Throwable exception = assertThrows(ValidationException.class, () -> roomValidationRule.validate("a 1"));
        assertEquals("Room dimension does not have a valid format, 2 positive numbers separated with a space: \"10 10\"", exception.getMessage());
    }

    @Test
    void validateInvalidHeightTest() {
        final Throwable exception = assertThrows(ValidationException.class, () -> roomValidationRule.validate("1 a"));
        assertEquals("Room dimension does not have a valid format, 2 positive numbers separated with a space: \"10 10\"", exception.getMessage());
    }

    @Test
    void validateNegativeInputTest() {
        final Throwable exceptionWidth = assertThrows(ValidationException.class, () -> roomValidationRule.validate("-1 1"));
        assertEquals("Room dimension does not have a valid format, 2 positive numbers separated with a space: \"10 10\"", exceptionWidth.getMessage());

        final Throwable exceptionHeight = assertThrows(ValidationException.class, () -> roomValidationRule.validate("1 -1"));
        assertEquals("Room dimension does not have a valid format, 2 positive numbers separated with a space: \"10 10\"", exceptionHeight.getMessage());

        final Throwable exceptionBoth = assertThrows(ValidationException.class, () -> roomValidationRule.validate("-1 -1"));
        assertEquals("Room dimension does not have a valid format, 2 positive numbers separated with a space: \"10 10\"", exceptionBoth.getMessage());
    }

    @Test
    void validateNumbersNotTooLargeTest() {
        final Throwable exceptionWidth = assertThrows(ValidationException.class, () -> roomValidationRule.validate("2147483648 1"));
        assertEquals("Room width is not a valid number", exceptionWidth.getMessage());

        final Throwable exceptionHeight = assertThrows(ValidationException.class, () -> roomValidationRule.validate("1 2147483648"));
        assertEquals("Room height is not a valid number", exceptionHeight.getMessage());

        final Throwable exceptionBoth = assertThrows(ValidationException.class, () -> roomValidationRule.validate("2147483648 2147483648"));
        assertEquals("Room width is not a valid number", exceptionBoth.getMessage());
    }

    @Test
    void validateRoomMustBeAtLeastOneSquareBig() {
        final Throwable exceptionWidth = assertThrows(ValidationException.class, () -> roomValidationRule.validate("0 100"));
        assertEquals("Room width must be equal or bigger than 1", exceptionWidth.getMessage());

        final Throwable exceptionHeight = assertThrows(ValidationException.class, () -> roomValidationRule.validate("100 0"));
        assertEquals("Room height must be equal or bigger than 1", exceptionHeight.getMessage());

        final Throwable exceptionBoth = assertThrows(ValidationException.class, () -> roomValidationRule.validate("0 0"));
        assertEquals("Room width must be equal or bigger than 1", exceptionBoth.getMessage());
    }

}