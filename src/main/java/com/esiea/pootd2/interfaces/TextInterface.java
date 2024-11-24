package com.esiea.pootd2.interfaces;

import java.util.Scanner;

import com.esiea.pootd2.controllers.IExplorerController;

/**
 * Text interface for the user.
 */
public class TextInterface extends AbstractInterface {

    /**
     * Constructs a TextInterface with the given controller.
     *
     * @param controller The controller to use.
     */
    public TextInterface(IExplorerController controller) {
        super(controller);
    }

    /**
     * Runs the TextInterface.
     */
    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String input;

        // We loop until the user enters 'exit'
        while (true) {
            System.out.print(this.controller.getCurrentDirectory() + "> ");
            input = scanner.nextLine();

            if (input.equals("exit")) {
                System.exit(0);
            }

            System.out.print(controller.executeCommand(input));
        }
    }
}
