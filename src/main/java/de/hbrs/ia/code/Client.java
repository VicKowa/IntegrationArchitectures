package de.hbrs.ia.code;

import de.hbrs.ia.commands.*;

import java.util.Map;

public class Client {
    public static void main(String[] args) {
        MongoDBHandler.get().setupConnection("localhost", 27017, "cliDatabase");

        // create CLIParser and add commands
        CLIParser parser = new CLIParser(Map.of(
                "create_salesman", new CreateSalesmanCmd(),
                "add_spr", new AddSocialPerformanceRecordCmd(),
                "read_salesman", new ReadSalesmanCmd(),
                "read_all_salesmen", new ReadAllSalesmanCmd(),
                "read_spr", new ReadSocialPerformanceRecordCmd(),
                "remove_salesman", new RemoveSalesmanCmd(),
                "remove_spr", new RemoveSocialPerformanceRecordCmd()
        ));

        // run the parser
        parser.run();
    }
}
