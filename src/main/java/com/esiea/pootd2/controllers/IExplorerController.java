package com.esiea.pootd2.controllers;

/**
 * Interface for the ExplorerController.
 */
public interface IExplorerController {
    /**
     * Executes a command.
     *
     * @param commandStr The command string to execute.
     * @return The result of the command execution.
     */
    public String executeCommand(String commandStr);

    /**
     * Returns the current directory.
     *
     * @return The current directory.
     */
    public String getCurrentDirectory();
}
