package io.claystation.model.command;

import java.util.Arrays;

public enum Command {

    LEFT("L"),
    FORWARD("F"),
    RIGHT("R");

    private final String command;

    Command(final String command) {
        this.command = command;
    }

    public static Command parseFromString(final String commandString) {
        return Arrays.stream(values())
                .filter(v -> v.command.equals(commandString))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid character given as command"));
    }
}
