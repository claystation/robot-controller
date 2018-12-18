package io.claystation.parser;

import io.claystation.exception.ParseException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionParserTest {

    @Test
    void validInputTest() {
        assertDoesNotThrow(() -> PositionParser.parse("1 2 N"));
        assertDoesNotThrow(() -> PositionParser.parse("0 0 E"));
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
        final Throwable exceptionX = assertThrows(ParseException.class, () -> PositionParser.parse("2147483648 5 N"));
        assertEquals("Coordinate is not a valid number", exceptionX.getMessage());
        final Throwable exceptionY = assertThrows(ParseException.class, () -> PositionParser.parse("5 2147483648 N"));
        assertEquals("Coordinate is not a valid number", exceptionY.getMessage());
    }

    private void assertThrowsAndVerifyErrorMessage(final String input) {
        final Throwable exception = assertThrows(ParseException.class, () -> PositionParser.parse(input));
        assertEquals("Position does not have a valid format, 2 positive numbers and one of the following characters \"N E S W\" separated with a space: \"5 5 N\"", exception.getMessage());
    }

}