package com.esiea.pootd2.commands.parsers;

import com.esiea.pootd2.commands.*;

import java.util.List;
import java.util.Arrays;

public class UnixLikeCommandParser implements ICommandParser {
    @Override
    public Command parse(String commandLine) {
        try {
            List<String> arguments = splitArguments(commandLine);
            return mapCommand(arguments);
        } catch (Exception e) {
            return new ErrorCommand("Failed to parse command: " + e.getMessage());
        }
    }

    private List<String> splitArguments(String commandLine) {
        // Splits the commandLine by spaces, assuming basic space-separated commands
        return Arrays.asList(commandLine.trim().split("\\s+"));
    }

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
            default:
                return new ErrorCommand("Unknown command: " + commandName);
        }
    }
}
