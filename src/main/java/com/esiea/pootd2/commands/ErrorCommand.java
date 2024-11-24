package com.esiea.pootd2.commands;

/**
 * Command for handling errors.
 */
public class ErrorCommand extends Command {
    private final String message;

    /**
     * Constructs an ErrorCommand with the given message.
     *
     * @param message The error message.
     */
    public ErrorCommand(String message) {
        this.message = message;
    }

    /**
     * Gets the error message.
     *
     * @return The error message.
     */
    public String getMessage() {
        return message;
    }
}
