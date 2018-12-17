package io.claystation.validation;

import io.claystation.exception.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomParserTest {

    private RoomParser roomParser;

    @BeforeEach
    void setUp() {
        roomParser = new RoomParser();
    }

    @Test
    void validInputTest() {
        assertDoesNotThrow(() -> roomParser.parse("5 5"));
    }

    @Test
    void validateNonNumericInputTest() {
        final Throwable exception = assertThrows(ParseException.class, () -> roomParser.parse("claystation"));
        assertEquals("Room dimension does not have a valid format, 2 positive numbers separated with a space: \"10 10\"", exception.getMessage());
    }

    @Test
    void validateInvalidWidthTest() {
        final Throwable exception = assertThrows(ParseException.class, () -> roomParser.parse("a 1"));
        assertEquals("Room dimension does not have a valid format, 2 positive numbers separated with a space: \"10 10\"", exception.getMessage());
    }

    @Test
    void validateInvalidHeightTest() {
        final Throwable exception = assertThrows(ParseException.class, () -> roomParser.parse("1 a"));
        assertEquals("Room dimension does not have a valid format, 2 positive numbers separated with a space: \"10 10\"", exception.getMessage());
    }

    @Test
    void validateNegativeInputTest() {
        final Throwable exceptionWidth = assertThrows(ParseException.class, () -> roomParser.parse("-1 1"));
        assertEquals("Room dimension does not have a valid format, 2 positive numbers separated with a space: \"10 10\"", exceptionWidth.getMessage());

        final Throwable exceptionHeight = assertThrows(ParseException.class, () -> roomParser.parse("1 -1"));
        assertEquals("Room dimension does not have a valid format, 2 positive numbers separated with a space: \"10 10\"", exceptionHeight.getMessage());

        final Throwable exceptionBoth = assertThrows(ParseException.class, () -> roomParser.parse("-1 -1"));
        assertEquals("Room dimension does not have a valid format, 2 positive numbers separated with a space: \"10 10\"", exceptionBoth.getMessage());
    }

    @Test
    void validateNumbersNotTooLargeTest() {
        final Throwable exceptionWidth = assertThrows(ParseException.class, () -> roomParser.parse("2147483648 1"));
        assertEquals("Room dimension is not a valid number", exceptionWidth.getMessage());

        final Throwable exceptionHeight = assertThrows(ParseException.class, () -> roomParser.parse("1 2147483648"));
        assertEquals("Room dimension is not a valid number", exceptionHeight.getMessage());

        final Throwable exceptionBoth = assertThrows(ParseException.class, () -> roomParser.parse("2147483648 2147483648"));
        assertEquals("Room dimension is not a valid number", exceptionBoth.getMessage());
    }

    @Test
    void validateRoomMustBeAtLeastOneSquareBig() {
        final Throwable exceptionWidth = assertThrows(IllegalArgumentException.class, () -> roomParser.parse("0 100"));
        assertEquals("Room dimensions must be greater than 0", exceptionWidth.getMessage());

        final Throwable exceptionHeight = assertThrows(IllegalArgumentException.class, () -> roomParser.parse("100 0"));
        assertEquals("Room dimensions must be greater than 0", exceptionHeight.getMessage());

        final Throwable exceptionBoth = assertThrows(IllegalArgumentException.class, () -> roomParser.parse("0 0"));
        assertEquals("Room dimensions must be greater than 0", exceptionBoth.getMessage());
    }

}