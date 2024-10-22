package de.hbrs.ia.commands;

import de.hbrs.ia.code.SalesmanMongoImpl;
import de.hbrs.ia.model.SalesMan;
import org.bson.json.JsonWriterSettings;

public class ReadSalesmanCmd implements CLIParser.Cmd {

    /**
     * Read a salesman from the database
     *
     * @param args the arguments for the command
     * @return true if the command was executed successfully, false otherwise
     * */
    @Override
    public boolean execute(String[] args) {
        // check if input is correct
        if (args.length != 1) {
            System.out.println("Usage: read_salesman <sid>");
            return false;
        }

        int sid = 0;

        try {
            sid = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: sid must be an integer");
            return false;
        }

        SalesMan salesman = new SalesmanMongoImpl().readSalesMan(sid);

        if (salesman == null) {
            System.out.println("Salesman not found");
            return false;
        }

        JsonWriterSettings prettyPrint = JsonWriterSettings.builder().indent(true).build();

        System.out.println("> Salesman:\n" + salesman.toDocument().toJson(prettyPrint));

        return true;
    }
}
