package com.esiea.pootd2.controllers;

import com.esiea.pootd2.commands.*;
import com.esiea.pootd2.commands.parsers.UnixLikeCommandParser;
import com.esiea.pootd2.models.FolderInode;

public class ExplorerController implements IExplorerController {

    private FolderInode currentDirectory;

    public ExplorerController() {
        this.currentDirectory = new FolderInode("/");
    }

    @Override
    public String executeCommand(String commandStr) {
        UnixLikeCommandParser parser = new UnixLikeCommandParser();
        Command command = parser.parse(commandStr);

        if (command instanceof ChangeDirectoryCommand) {
            return doCommand((ChangeDirectoryCommand) command);
        } else if (command instanceof ErrorCommand) {
            return doCommand((ErrorCommand) command);
        } else if (command instanceof ListCommand) {
            return doCommand((ListCommand) command);
        } else if (command instanceof MakeDirectoryCommand) {
            return doCommand((MakeDirectoryCommand) command);
        } else if (command instanceof TouchCommand) {
            return doCommand((TouchCommand) command);
        } else {
            ErrorCommand errorCommand = new ErrorCommand("Command not implemented");
            return doCommand(errorCommand);
        }
    }

    private String doCommand(ChangeDirectoryCommand command) {
        return "";
    }

    private String doCommand(ErrorCommand command) {
        return "";
    }

    private String doCommand(ListCommand command) {
        return "";
    }

    private String doCommand(MakeDirectoryCommand command) {
        return "";
    }

    private String doCommand(TouchCommand command) {
        return "";
    }
}
