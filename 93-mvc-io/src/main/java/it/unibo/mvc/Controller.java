package it.unibo.mvc;

import java.util.List;

/**
 * A controller defining operations for printing strings and retrieving history.
 */
public interface Controller {

    /**
     * Sets the next string to print.
     * 
     * @param s the next string to print
     */
    void setNextString(String s);

    /**
     * @return the next string to print
     */
    String getNextString();

    /**
     * @return the history of all printed strings
     */
    List<String> getHistory();

    /**
     * Prints the current string. If unset, throws an {@link IllegalStateException}.
     */
    void print();
}
