package it.unibo.mvc;

import java.io.File;

/**
 * Application controller. Performs the I/O.
 */
public final class Controller {

    private File currentFile;

    /**
     * Builds the new Controller and sets the default file
     * to "output.txt" inside the user's home directory.
     */
    public Controller() {
        final String home = System.getProperty("user.home");
        final String separator = System.getProperty("file.separator");
        this.currentFile = new File(home + separator + "output.txt");
    }

    /**
     * Returns the currently selected file.
     * 
     * @return the current file
     */
    public File getCurrentFile() {
        return this.currentFile;
    }

}
