package io.claystation.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInputReader implements InputReader {

    private final BufferedReader bufferRead;

    public ConsoleInputReader() {
        bufferRead = new BufferedReader(new InputStreamReader(System.in));
    }

    public String read() {
        try {
            return bufferRead.readLine();
        } catch (final IOException e) {
            System.out.println("An IO exception occurred!");
            return "";
        }
    }

}
