package io.claystation.validation;

import io.claystation.exception.ValidationException;
import io.claystation.model.Room;

import java.util.regex.Pattern;

public class RoomValidationRule implements ValidationRule {

    private final Pattern format = Pattern.compile("\\d+\\s\\d+");

    @Override
    public Room validate(final String input) throws ValidationException {
        if (!format.matcher(input).matches()) {
            throw new ValidationException("Room dimension does not have a valid format, 2 positive numbers separated with a space: \"10 10\"");
        }

        final String[] dimensions = input.split(" ");
        final int x = validateDimension(dimensions[0], "width");
        final int y = validateDimension(dimensions[1], "height");

        return new Room(x, y);
    }

    private int validateDimension(final String dimension, final String name) throws ValidationException {
        try {
            final int size = Integer.parseInt(dimension);
            if (size < 1) {
                throw new ValidationException("Room " + name + " must be equal or bigger than 1");
            }
            return size;
        } catch (final NumberFormatException e) {
            throw new ValidationException("Room " + name + " is not a valid number");
        }
    }
}
