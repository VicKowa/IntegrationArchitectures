package de.hbrs.ia.commands;

import de.hbrs.ia.code.SalesmanMongoImpl;
import de.hbrs.ia.model.SalesMan;

public class RemoveSalesmanCmd implements CLIParser.Cmd {

    /**
     * Remove a salesman from the database
     *
     * @param args the arguments for the command
     * @return true if the command was executed successfully, false otherwise
     * */
    @Override
    public boolean execute(String[] args) {
        // check if input is correct
        if (args.length != 1) {
            System.out.println("> Usage: remove_salesman <sid>");
            return false;
        }

        int sid = 0;
        try {
            sid = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("> Invalid input: sid must be an integer");
            return false;
        }

        SalesmanMongoImpl impl = new SalesmanMongoImpl();
        SalesMan salesman = impl.readSalesMan(sid);

        if (salesman == null) {
            System.out.println("> Salesman not found");
            return false;
        }

        impl.removeSalesMan(salesman);

        return true;
    }
}
