package io.claystation.testutil;

import io.claystation.io.InputReader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MockInputReader implements InputReader {

    private final List<String> inputList = new ArrayList<>();
    private final AtomicInteger index = new AtomicInteger(0);

    @Override
    public String read() {
        return inputList.get(index.getAndIncrement());
    }

    public void setInput(final List<String> inputList) {
        this.inputList.addAll(inputList);
    }

    public void addInput(final String input) {
        inputList.add(input);
    }

}
