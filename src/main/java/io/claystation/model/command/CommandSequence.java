package io.claystation.model.command;

import java.util.ArrayList;
import java.util.List;

public class CommandSequence {

    private final List<Command> commands = new ArrayList<>();

    public List<Command> getCommands() {
        return commands;
    }

    public void addCommand(final Command command) {
        commands.add(command);
    }
}
