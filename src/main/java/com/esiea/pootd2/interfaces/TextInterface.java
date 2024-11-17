package com.esiea.pootd2.interfaces;

import com.esiea.pootd2.controllers.ExplorerController;
import java.util.Scanner;

public class TextInterface implements IUserInterface {
    private ExplorerController controller;

    public TextInterface(ExplorerController controller) {
        this.controller = controller;
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
            System.out.println(controller.executeCommand(input));
        }
    }
}
