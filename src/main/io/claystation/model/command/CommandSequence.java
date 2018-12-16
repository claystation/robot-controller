package io.claystation.model.command;

import java.util.ArrayList;

public class CommandSequence {

    private final ArrayList<Command> commands = new ArrayList<>();

    public ArrayList<Command> getCommands() {
        return commands;
    }

    public void addCommand(final Command command) {
        commands.add(command);
    }
}
