package com.esiea.pootd2.commands.parsers;

import com.esiea.pootd2.commands.Command;

/**
 * Interface for the CommandParser.
 */
public interface ICommandParser {
    /**
     * Parses a command string into a Command object.
     *
     * @param commandStr The command string to parse.
     * @return The parsed Command object.
     */
    abstract Command parse(String commandStr);
}
