package de.hbrs.ia.commands;

import de.hbrs.ia.code.SalesmanMongoImpl;
import de.hbrs.ia.model.SalesMan;
import org.bson.Document;
import org.bson.json.JsonWriterSettings;

import java.util.List;

public class ReadAllSalesmanCmd implements CLIParser.Cmd {

    /**
     * Read all salesmen from the database
     *
     * @param args the arguments for the command
     * @return true if the command was executed successfully, false otherwise
     * */
    @Override
    public boolean execute(String[] args) {
        // check if input is correct
        if (args.length != 0) {
            System.out.println("Usage: read_salesman");
            return false;
        }

        List<SalesMan> salesmen = new SalesmanMongoImpl().readAllSalesMen();

        JsonWriterSettings prettyPrint = JsonWriterSettings.builder().indent(true).build();

        System.out.println("> Salesmen:\n> ---");
        for (SalesMan salesman : salesmen)
            System.out.println(salesman.toDocument().toJson(prettyPrint) + "\n> ---");

        return true;
    }
}
