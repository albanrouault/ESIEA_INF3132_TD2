package com.esiea.pootd2.commands;

/**
 * Command for changing the current directory.
 */
public class ChangeDirectoryCommand extends Command {
    private final String path;

    /**
     * Constructs a ChangeDirectoryCommand with the given path.
     *
     * @param path The path to change to.
     */
    public ChangeDirectoryCommand(String path) {
        this.path = path;
    }

    /**
     * Gets the path to change to.
     *
     * @return The path to change to.
     */
    public String getPath() {
        return path;
    }
}
