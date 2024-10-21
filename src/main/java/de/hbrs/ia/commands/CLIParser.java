package de.hbrs.ia.commands;

import java.util.*;

public class CLIParser {
    private final Map<String, Cmd> cmdList;

    public CLIParser(Map<String, Cmd> cmdList) {
        this.cmdList = cmdList;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");

            String[] args = scanner.nextLine().split(" ");

            if (args.length == 0)
                continue;

            final String cmd = args[0];

            switch(cmd) {
                case "help":
                    help();
                    continue;
                case "exit":
                    return;
                default:
                    break;
            }

            if (cmd.isEmpty() || !cmdList.containsKey(cmd)) {
                System.out.println("Unknown command: " + cmd + "\nFor more information please type 'help'");
                continue;
            }

            if (!cmdList.get(cmd).execute(Arrays.copyOfRange(args, 1, args.length))) {
                System.out.println("Command failed: " + cmd);
            } else {
                System.out.println("Command successful: " + cmd);
            }
        }
    }

    private void help() {
        System.out.println("Available commands:");

        for (String cmd : cmdList.keySet()) {
            System.out.println("  " + cmd);
        }
    }

    @FunctionalInterface
    public interface Cmd {
        boolean execute(String[] args);
    }
}
