package io.claystation.creator;

import io.claystation.io.InputReader;
import io.claystation.io.Writer;

abstract class Creator {

    InputReader inputReader;
    Writer writer;

    Creator(final InputReader inputReader, final Writer writer) {
        this.inputReader = inputReader;
        this.writer = writer;
    }
}
