package io.claystation.creator;

import io.claystation.exception.ValidationException;
import io.claystation.io.InputReader;
import io.claystation.io.Writer;
import io.claystation.model.command.CommandSequence;
import io.claystation.validation.CommandSequenceValidationRule;

public class CommandSequenceCreator extends Creator {

    public CommandSequenceCreator(final InputReader inputReader, final Writer writer) {
        super(inputReader, writer);
    }

    public CommandSequence createSequence() {
        writer.writeString("Please define the sequence of commands with the following characters \"L F R\" without spaces: \"RFLFFLRF\"");
        try {
            return new CommandSequenceValidationRule().validate(inputReader.read());
        } catch (final ValidationException e) {
            writer.writeError(e);
            return this.createSequence();
        }
    }


}
