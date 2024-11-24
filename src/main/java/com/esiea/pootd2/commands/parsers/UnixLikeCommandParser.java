package com.esiea.pootd2.commands.parsers;

import com.esiea.pootd2.commands.*;

import java.util.List;
import java.util.Arrays;

/**
 * Parser for Unix-like commands.
 */
public class UnixLikeCommandParser implements ICommandParser {

    /**
     * Parses a command string into a Command object.
     *
     * @param commandLine The command string to parse.
     * @return The parsed Command object.
     */
    @Override
    public Command parse(String commandLine) {
        try {
            List<String> arguments = splitArguments(commandLine);
            return mapCommand(arguments);
        } catch (Exception e) {
            return new ErrorCommand("Failed to parse command: " + e.getMessage());
        }
    }

    /**
     * Splits the commandLine by spaces, assuming basic space-separated commands.
     *
     * @param commandLine The command string to split.
     * @return The list of arguments.
     */
    private List<String> splitArguments(String commandLine) {
        return Arrays.asList(commandLine.trim().split("\\s+"));
    }

    /**
     * Maps the arguments to the corresponding Command object.
     *
     * @param arguments The list of arguments.
     * @return The parsed Command object.
     */
    private Command mapCommand(List<String> arguments) {
        if (arguments.isEmpty()) {
            return new ErrorCommand("No command provided.");
        }

        String commandName = arguments.get(0);
        switch (commandName) {
            case "ls":
                return new ListCommand();
            case "mkdir":
                if (arguments.size() < 2) {
                    return new ErrorCommand("mkdir: Missing directory name.");
                }
                return new MakeDirectoryCommand(arguments.get(1));
            case "cd":
                if (arguments.size() < 2) {
                    return new ErrorCommand("cd: Missing directory name.");
                }
                return new ChangeDirectoryCommand(arguments.get(1));
            case "touch":
                if (arguments.size() < 2) {
                    return new ErrorCommand("touch: Missing file name.");
                }
                return new TouchCommand(arguments.get(1));
            case "":
                return null;
            default:
                return new ErrorCommand("Unknown command: " + commandName);
        }
    }
}
