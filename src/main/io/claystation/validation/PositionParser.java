package io.claystation.validation;

import io.claystation.exception.ParseException;
import io.claystation.model.position.Direction;
import io.claystation.model.position.Position;

import java.util.regex.Pattern;

public class PositionParser implements Parser {

    private final Pattern format = Pattern.compile("\\d+\\s\\d+\\s[NESW]");

    @Override
    public Position parse(final String input) throws ParseException {
        if (!format.matcher(input).matches()) {
            throw new ParseException("Position does not have a valid format, 2 positive numbers and one of the following characters \"N E S W\" separated with a space: \"5 5 N\"");
        }
        final String[] position = input.split(" ");
        final int x = validateCoordinate(position[0], "X");
        final int y = validateCoordinate(position[1], "Y");
        final Direction direction = Direction.parseFromString(position[2]);

        return new Position(x, y, direction);
    }

    private int validateCoordinate(final String position, final String name) throws ParseException {
        try {
            return Integer.parseInt(position);
        } catch (final NumberFormatException e) {
            throw new ParseException("Position " + name + " is not a valid number");
        }
    }
}
