package io.claystation.validation;

import io.claystation.exception.ValidationException;
import io.claystation.model.command.Command;
import io.claystation.model.command.CommandSequence;

import java.util.Arrays;
import java.util.regex.Pattern;

public class CommandSequenceValidationRule implements ValidationRule {

    private final Pattern format = Pattern.compile("^[LFR]+$");

    @Override
    public CommandSequence validate(final String input) throws ValidationException {
        if (!format.matcher(input).matches()) {
            throw new ValidationException("Command sequence is not in a valid format, one or more of the following 3 characters \"L F R\" without spaces: \"LFRFLFRLFFR\"");
        }
        final CommandSequence commandSequence = new CommandSequence();

        Arrays.stream(input.split(""))
                .map(Command::parseFromString)
                .forEach(commandSequence::addCommand);

        return commandSequence;
    }
}
