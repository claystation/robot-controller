package io.claystation.parser;

import io.claystation.exception.ParseException;
import io.claystation.model.command.Command;
import io.claystation.model.command.CommandSequence;

import java.util.Arrays;
import java.util.regex.Pattern;

public class CommandSequenceParser {

    private static final Pattern FORMAT = Pattern.compile("^[LFR]+$");

    private CommandSequenceParser() {
    }

    public static CommandSequence parse(final String input) throws ParseException {
        if (!FORMAT.matcher(input).matches()) {
            throw new ParseException("Command sequence is not in a valid format, one or more of the following 3 characters \"L F R\" without spaces: \"LFRFLFRLFFR\"");
        }

        final CommandSequence commandSequence = new CommandSequence();

        Arrays.stream(input.split(""))
                .map(Command::parseFromString)
                .forEach(commandSequence::addCommand);

        return commandSequence;
    }
}
