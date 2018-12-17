package io.claystation.parser;

import io.claystation.exception.ParseException;
import io.claystation.model.position.Direction;
import io.claystation.model.position.Position;

import java.util.regex.Pattern;

public class PositionParser {

    private static final Pattern FORMAT = Pattern.compile("\\d+\\s\\d+\\s[NESW]");

    private PositionParser() {}

    public static Position parse(final String input) throws ParseException {
        if (!FORMAT.matcher(input).matches()) {
            throw new ParseException("Position does not have a valid format, 2 positive numbers and one of the following characters \"N E S W\" separated with a space: \"5 5 N\"");
        }

        try {
            final String[] position = input.split(" ");
            final int x = Integer.parseInt(position[0]);
            final int y = Integer.parseInt(position[1]);
            final Direction direction = Direction.parseFromString(position[2]);
            return new Position(x, y, direction);
        } catch (final NumberFormatException e) {
            throw new ParseException("Coordinate is not a valid number");
        }
    }
}
