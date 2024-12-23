package com.esiea.pootd2.controllers;

import com.esiea.pootd2.commands.*;
import com.esiea.pootd2.commands.parsers.UnixLikeCommandParser;
import com.esiea.pootd2.models.FileInode;
import com.esiea.pootd2.models.FolderInode;

/**
 * Controller for managing file system operations.
 */
public class ExplorerController implements IExplorerController {
    private FolderInode currentDirectory;

    /**
     * Initializes the ExplorerController with the root directory.
     */
    public ExplorerController() {
        this.currentDirectory = new FolderInode("/");
    }

    /**
     * Executes a given command string.
     *
     * @param commandStr The command string to execute.
     * @return The result of the command execution.
     */
    @Override
    public String executeCommand(String commandStr) {
        UnixLikeCommandParser parser = new UnixLikeCommandParser();
        Command command = parser.parse(commandStr);

        if (command == null) {
            return "";
        }

        if (command instanceof ChangeDirectoryCommand cdCommand)
            return doCommand(cdCommand);
        if (command instanceof ListCommand listCommand)
            return doCommand(listCommand);
        if (command instanceof MakeDirectoryCommand mkdirCommand)
            return doCommand(mkdirCommand);
        if (command instanceof TouchCommand touchCommand)
            return doCommand(touchCommand);
        if (command instanceof ErrorCommand errorCommand)
            return doCommand(errorCommand);

        ErrorCommand errorCmd = new ErrorCommand("Command not implemented");
        return doCommand(errorCmd);
    }

    /**
     * Gets the current directory.
     *
     * @return The current directory.
     */
    @Override
    public String getCurrentDirectory() {
        // Get the full path of the current directory (from current to root)
        FolderInode currentFolder = currentDirectory;
        StringBuilder result = new StringBuilder(currentFolder.getName());
        // Loop through the parent folders to get the full path
        while (currentFolder.getParent() != null) {
            currentFolder = currentFolder.getParent();
            result.insert(0, currentFolder.getName() + "/");
        }
        // Remove the first '/' character because the root folder is named '/'
        result.deleteCharAt(0);
        return result.toString();
    }

    /**
     * Changes the current directory to the specified path.
     *
     * @param command The change directory command to execute.
     * @return The result of the command execution.
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
     * Handles the error command.
     *
     * @param command The error command to execute.
     * @return The result of the command execution.
     */
    private String doCommand(ErrorCommand command) {
        return command.getMessage() + "\n";
    }

    /**
     * Handles the list command.
     *
     * @param command The list command to execute.
     * @return The result of the command execution.
     */
    private String doCommand(ListCommand command) {
        // Sort the children of the current folder by name and display their name and size
        // Format: name(tabulation)size\n
        StringBuilder result = new StringBuilder();
        currentDirectory.getChildrens().stream()
            .sorted((child1, child2) -> child1.getName().compareToIgnoreCase(child2.getName()))
            .forEach(child -> result.append(child.getName()).append("\t").append(child.getSize()).append("\n"));
        return result.toString();
    }

    /**
     * Handles the make directory command.
     *
     * @param command The make directory command to execute.
     * @return The result of the command execution.
     */
    private String doCommand(MakeDirectoryCommand command) {
        // Split the path into parts
        String[] pathParts = command.getDirectoryPath().split("/");
        // Loop through the path parts to navigate to the desired folder
        FolderInode currentFolder = currentDirectory;
        boolean created = false;
        for (String pathPart : pathParts) {
            // Check if the folder already exists
            FolderInode folder = currentFolder.getFolderInode(pathPart);
            if (folder == null) {
                // Create a new folder with the given name
                FolderInode newFolder = new FolderInode(pathPart);
                if (currentFolder.addInode(newFolder)) {
                    created = true;
                    currentFolder = newFolder;
                } else {
                    return this.doCommand(new ErrorCommand("Directory already exists"));
                }
            } else {
                currentFolder = folder;
            }
        }
        // Check if the folder was created
        if (created) {
            return "";
        } else {
            return this.doCommand(new ErrorCommand("Directory already exists"));
        }
    }

    /**
     * Handles the touch command.
     *
     * @param command The touch command to execute.
     * @return The result of the command execution.
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
