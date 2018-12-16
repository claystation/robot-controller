package io.claystation.creator;

import io.claystation.exception.ValidationException;
import io.claystation.io.InputReader;
import io.claystation.io.Writer;
import io.claystation.model.Room;
import io.claystation.validation.RoomValidationRule;

public class RoomCreator extends Creator{

    public RoomCreator(final InputReader inputReader, final Writer writer) {
        super(inputReader, writer);
    }

    public Room createRoom() {
        writer.writeString("Please define the size of the room separated by a space: \"w h\"");
        try {
            return new RoomValidationRule().validate(inputReader.read());
        } catch (final ValidationException e) {
            writer.writeError(e);
            return this.createRoom();
        }
    }


}
