package de.hbrs.ia.commands;

import de.hbrs.ia.commands.CLIParser.Cmd;
import de.hbrs.ia.code.SalesmanMongoImpl;
import de.hbrs.ia.model.SalesMan;

public class CreateSalesmanCmd implements Cmd {

    /**
     * Create a salesman in the database
     *
     * @param args the arguments for the command
     * @return true if the command was executed successfully, false otherwise
     * */
    @Override
    public boolean execute(String[] args) {
        // check if input is correct
        if (args.length != 3) {
            System.out.println("Usage: create_salesman <firstname> <lastname> <sid>");
            return false;
        }

        String firstname = args[0];
        String lastname = args[1];
        int sid = 0;

        try {
            sid = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: sid must be an integer");
            return false;
        }

        SalesMan salesman = new SalesMan(firstname, lastname, sid);
        new SalesmanMongoImpl().createSalesMan(salesman);

        return true;
    }
}
