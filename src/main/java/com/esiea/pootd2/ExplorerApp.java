package com.esiea.pootd2;

import com.esiea.pootd2.interfaces.HttpInterface;
import com.esiea.pootd2.interfaces.IUserInterface;
import com.esiea.pootd2.interfaces.TextInterface;
import com.esiea.pootd2.controllers.ExplorerController;
import com.esiea.pootd2.controllers.IExplorerController;

/**
 * ExplorerApp class representing the main application.
 */
public class ExplorerApp {
    /**
     * Prints the help message.
     */
    public static void printHelp() {
        System.out.println("""
        Usage: java ExplorerApp <interface_type>

            <interface_type>: text or http
        """);
    }

    /**
     * Main method for the ExplorerApp.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            printHelp();
            return;
        }

        IExplorerController explorerController = new ExplorerController();
        IUserInterface explorerInterface = null;

        String interfaceType = args[0];
        if ("http".equals(interfaceType))
            explorerInterface = new HttpInterface(explorerController);
        else if ("text".equals(interfaceType))
            explorerInterface = new TextInterface(explorerController);
        else {
            System.err.format("Unknown interface type: %s%n", interfaceType);
            return;
        }

        assert explorerInterface != null;

        explorerInterface.run();
    }
}