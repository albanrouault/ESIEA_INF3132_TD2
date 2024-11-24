package com.esiea.pootd2.commands;

/**
 * Command for creating a new file.
 */
public class TouchCommand extends Command {
    private final String fileName;

    /**
     * Constructs a TouchCommand with the given file name.
     *
     * @param fileName The name of the file to create.
     */
    public TouchCommand(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Gets the name of the file to create.
     *
     * @return The name of the file to create.
     */
    public String getFileName() {
        return fileName;
    }
}
