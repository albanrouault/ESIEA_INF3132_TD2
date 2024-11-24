package com.esiea.pootd2.commands;

public class MakeDirectoryCommand extends Command {
    private final String directoryPath;

    public MakeDirectoryCommand(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    public String getDirectoryPath() {
        return directoryPath;
    }
}
