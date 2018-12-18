package io.claystation.parser;

import io.claystation.exception.ParseException;
import io.claystation.model.Room;

import java.util.regex.Pattern;

public class RoomParser {

    private static final Pattern FORMAT = Pattern.compile("\\d+\\s\\d+");

    private RoomParser() {
    }

    public static Room parse(final String input) throws ParseException {
        if (!FORMAT.matcher(input).matches()) {
            throw new ParseException("Room dimension does not have a valid format, 2 positive numbers separated with a space: \"10 10\"");
        }

        final String[] dimensions = input.split(" ");

        try {
            final int x = Integer.parseInt(dimensions[0]);
            final int y = Integer.parseInt(dimensions[1]);
            return new Room(x, y);
        } catch (final NumberFormatException e) {
            throw new ParseException("Room dimension is not a valid number");
        }
    }
}
