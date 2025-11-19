package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

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

    /**
     * Returns the path of the current file.
     * 
     * @return the string path of the current file
     */
    public String getPath() {
        return this.currentFile.getPath();
    }

    /**
     * Sets a new file as the current file.
     * 
     * @param file the file to be set as current
     */
    public void setCurrentFile(final File file) {
        this.currentFile = file;
    }

    /**
     * Saves the specified content into the current file using UTF-8 encoding.
     * 
     * @param content the text to be saved
     * @throws IOException if something goes wrong during writing
     */
    public void save(final String content) throws IOException {
        try (PrintStream ps = new PrintStream(this.currentFile, StandardCharsets.UTF_8)) {
            ps.print(content);
        }
    } 
}
