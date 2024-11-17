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

        if (command == null) {
            return "";
        }

        return switch (command) {
            case ChangeDirectoryCommand cdCommand -> doCommand(cdCommand);
            case ListCommand listCommand -> doCommand(listCommand);
            case MakeDirectoryCommand mkdirCommand -> doCommand(mkdirCommand);
            case TouchCommand touchCommand -> doCommand(touchCommand);
            case ErrorCommand errorCommand -> doCommand(errorCommand);
            default -> {
                ErrorCommand errorCmd = new ErrorCommand("Command not implemented");
                yield doCommand(errorCmd);
            }
        };
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
        if (currentDirectory.addInode(newFolder)) {
            return "";
        } else {
            return this.doCommand(new ErrorCommand("Directory already exists"));
        }
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
        if (currentDirectory.addInode(newFile)) {
            return "";
        } else {
            return this.doCommand(new ErrorCommand("File already exists"));
        }
    }
}
