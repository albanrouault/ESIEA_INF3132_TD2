package com.esiea.pootd2.commands;

/**
 * Command for creating a new directory.
 */
public class MakeDirectoryCommand extends Command {
    private final String directoryPath;

    /**
     * Constructs a MakeDirectoryCommand with the given directory name.
     *
     * @param directoryName The name of the directory to create.
     */
    public MakeDirectoryCommand(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    /**
     * Gets the name of the directory to create.
     *
     * @return The name of the directory to create.
     */
    public String getDirectoryPath() {
        return directoryPath;
    }
}
