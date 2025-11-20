package it.unibo.mvc;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple implementation of the Controller interface.
 */
public final class SimpleController implements Controller {

    private String currentString = "";
    private final List<String> history;

    /**
     * Builds a Controller with an empty history
     */
    public SimpleController() {
        this.history = new ArrayList<>();
    }

    @Override
    public void setNextString(String s) {
        if (s == null) {
            throw new IllegalStateException("String cannot be null");
        }
        this.currentString = s;
    }
}
