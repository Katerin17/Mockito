package main.ru.job4j.tracker;

import java.util.Objects;
import java.util.function.Consumer;

public class StubOutput implements Consumer<String> {

    private final StringBuilder buffer = new StringBuilder();

    @Override
    public void accept(String s) {
        buffer.append(Objects.requireNonNullElse(s, "null"));
    }

    @Override
    public String toString() {
        return buffer.toString();
    }
}

