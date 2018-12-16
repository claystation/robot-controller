package io.claystation.io;

public class Writer {

    public void writeString(final String message) {
        System.out.println(message);
    }

    public void writeError(final Exception exception) {
        System.out.println(exception.getMessage());
    }

}
