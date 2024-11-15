package com.esiea.pootd2.interfaces;

import java.util.Scanner;

public class TextInterface implements IUserInterface {

    @Override
    public void run() {
        // On crée un scanner pour lire les entrées de l'utilisateur
        Scanner scanner = new Scanner(System.in);
        String input;

        // On boucle tant que l'utilisateur n'a pas entré 'exit'
        while (true) {
            System.out.print("> ");
            input = scanner.nextLine();
            String[] parts = input.split(" ");

            // Pour le moment on met un switch mais si on plus tard dans l'exo on doit faire des classes pour les commandes, on utilisera sûrement du polymorphisme pour réduire le code et le rendre plus propre
            switch (parts[0]) {
                case "exit":
                    scanner.close();
                    return;
                case "ls":
                    break;
                case "mkdir":
                    break;
                case "cd":
                    break;
                case "touch":
                    break;
                default:
            }
        }
    }
}
