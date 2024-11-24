package com.esiea.pootd2.commands;

/**
 * Command for creating a new directory.
 */
public class MakeDirectoryCommand extends Command {
    private final String directoryName;

    /**
     * Constructs a MakeDirectoryCommand with the given directory name.
     *
     * @param directoryName The name of the directory to create.
     */
    public MakeDirectoryCommand(String directoryName) {
        this.directoryName = directoryName;
    }

    /**
     * Gets the name of the directory to create.
     *
     * @return The name of the directory to create.
     */
    public String getDirectoryName() {
        return directoryName;
    }
}
