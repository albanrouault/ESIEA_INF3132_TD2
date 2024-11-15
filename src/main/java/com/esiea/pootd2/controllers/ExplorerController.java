package com.esiea.pootd2.controllers;

import com.esiea.pootd2.commands.*;
import com.esiea.pootd2.parsers.UnixLikeCommandParser;

public class ExplorerController implements IExplorerController {
    @Override
    public String executeCommand(String commandStr) {
        UnixLikeCommandParser parser = new UnixLikeCommandParser();
        Command command = parser.parse(commandStr);

        // Je suis pas sur qu'il faille faire ça, ça me parrait bien moche, je pense pas avoir compris la question (Etape 6) + il faut finir l'étape
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
            return "";
        }
    }

    private String doCommand(ChangeDirectoryCommand commandStr) {
        return "";
    }

    private String doCommand(ErrorCommand commandStr) {
        return "";
    }

    private String doCommand(ListCommand commandStr) {
        return "";
    }

    private String doCommand(MakeDirectoryCommand commandStr) {
        return "";
    }

    private String doCommand(TouchCommand commandStr) {
        return "";
    }
}
