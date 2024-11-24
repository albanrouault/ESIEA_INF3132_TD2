package com.esiea.pootd2.commands;

/**
 * Command for creating a new directory.
 */
public class MakeDirectoryCommand extends Command {
    private final String directoryPath;

    /**
     * Constructs a MakeDirectoryCommand with the given directory path.
     *
     * @param directoryPath The path of the directory to create.
     */
    public MakeDirectoryCommand(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    /**
     * Gets the path of the directory to create.
     *
     * @return The path of the directory to create.
     */
    public String getDirectoryPath() {
        return directoryPath;
    }
}
