package com.esiea.pootd2.interfaces;

import java.util.Scanner;

import com.esiea.pootd2.controllers.IExplorerController;

public class TextInterface extends AbstractInterface {

    public TextInterface(IExplorerController controller) {
        super(controller);
    }

    @Override
    public void run() {
        // On crée un scanner pour lire les entrées de l'utilisateur
        Scanner scanner = new Scanner(System.in);
        String input;

        // On boucle tant que l'utilisateur n'a pas entré 'exit'
        while (true) {
            System.out.print("> ");
            input = scanner.nextLine();

            // Si "exit" est entré, on arrête le programme
            if (input.equals("exit")) {
                System.exit(0);
            }

            System.out.print(controller.executeCommand(input));
        }
    }
}
