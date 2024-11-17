package com.esiea.pootd2.controllers;

import com.esiea.pootd2.commands.*;
import com.esiea.pootd2.commands.parsers.UnixLikeCommandParser;
import com.esiea.pootd2.models.*;

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

    /**
     * Change the current directory to the given path.
     * @param command The command to execute.
     * @return The result of the command.
     */
    private String doCommand(ChangeDirectoryCommand command) {
        // Split the path into parts
        String[] pathParts = command.getPath().split("/");
        // Loop through the path parts to navigate to the desired folder
        FolderInode currentFolder = currentDirectory;
        for (String pathPart : pathParts) {
            currentFolder = currentFolder.getFolderInode(pathPart);
            if (currentFolder == null) {
                return this.doCommand(new ErrorCommand("No such file or directory"));
            }
        }
        currentDirectory = currentFolder;
        return "";
    }

    /**
     * Handle the error command.
     * @param command The command to execute.
     * @return The result of the command.
     */
    private String doCommand(ErrorCommand command) {
        return command.getMessage() + "\n";
    }

    /**
     * TODO : Voir si on peut rendre cette liste en RO avec une deep copy et si on doit pouvoir l'exec à distance, exemple ls /home/user
     * Handle the list command.
     * @param command The command to execute.
     * @return The result of the command.
     */
    private String doCommand(ListCommand command) {
        // Loop through the children of the current folder and display their name and size
        // Format : name(tabulation)size\n
        StringBuilder result = new StringBuilder();
        for (Inode child : currentDirectory.getChildrens()) {
            result.append(child.getName()).append("\t").append(child.getSize()).append("\n");
        }
        return result.toString();
    }

    /**
     * TODO : Voir si on doit pouvoir créer un dossier à "distance" -> actuellement on stock le nom, dans ce cas il faudrait stocker le path
     * Handle the make directory command.
     * @param command The command to execute.
     * @return The result of the command.
     */
    private String doCommand(MakeDirectoryCommand command) {
        // Create a new folder with the given name
        FolderInode newFolder = new FolderInode(command.getDirectoryName());
        currentDirectory.addInode(newFolder);
        return "";
    }

    /**
     * TODO : Voir si on doit pouvoir créer un fichier à "distance" -> actuellement on stock le nom, dans ce cas il faudrait stocker le path
     * Handle the touch command.
     * @param command The command to execute.
     * @return The result of the command.
     */
    private String doCommand(TouchCommand command) {
        // Create a new file with the given name
        FileInode newFile = new FileInode(command.getFileName());
        currentDirectory.addInode(newFile);
        return "";
    }
}
