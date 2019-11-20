package Views;

import Models.Commands.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class TextMenu {
    private final Map<String, Command> commands;

    TextMenu() {
        commands = new HashMap<>();
    }

    void addCommand(Command c) {
        commands.put(c.getKey(), c);
    }

    private void printMenu() {
        for (Command com : commands.values()) {
            String line = String.format("%4s: %s", com.getKey(), com.getDescription());
            System.out.println(line);
        }
        System.out.println();
    }

    void show() {
        Scanner scanner = new Scanner(System.in);
        // noinspection InfiniteLoopStatement
        while (true) {
            printMenu();
            System.out.print("Input the option: ");
            String key = scanner.nextLine();
            Command com = commands.get(key);
            if (com == null) {
                System.out.println("Invalid Option");
                continue;
            }
            com.execute();
        }
    }
}

