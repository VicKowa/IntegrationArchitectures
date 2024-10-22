package de.hbrs.ia.commands;

import de.hbrs.ia.code.SalesmanMongoImpl;
import de.hbrs.ia.model.SalesMan;
import de.hbrs.ia.model.SocialPerformanceRecord;
import org.bson.json.JsonWriterSettings;

import java.util.List;
import java.util.Scanner;

public class RemoveSocialPerformanceRecordCmd implements CLIParser.Cmd {

    @Override
    public boolean execute(String[] args) {
        // check if input is correct
        if (args.length != 1) {
            System.out.println("Usage: remove_spr <sid>");
            return false;
        }

        int sid = 0;

        try {
            sid = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: sid must be an integer");
            return false;
        }

        SalesmanMongoImpl impl = new SalesmanMongoImpl();

        SalesMan salesman = impl.readSalesMan(sid);

        if (salesman == null) {
            System.out.println("Salesman not found");
            return false;
        }

        List<SocialPerformanceRecord> records = impl.readSocialPerformanceRecord(
                salesman
        );

        if (records.isEmpty()) {
            System.out.println("No social performance records found for salesman with sid: " + sid);
            return false;
        }

        JsonWriterSettings prettyPrint = JsonWriterSettings.builder().indent(true).build();

        // print all existing social performance records of salesman with matching sid
        System.out.println("> ---");
        for (int i = 0; i < records.size(); i++) {
            System.out.println("> " + (i + 1) + ":\n" + records.get(i).toDocument().toJson(prettyPrint));
            System.out.println("> ---");
        }

        // assign them an identifier
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the identifier of the record to delete: ");

        int identifier = scanner.nextInt();

        if (identifier < 1 || identifier > records.size()) {
            System.out.println("Invalid identifier");
            return false;
        }

        SocialPerformanceRecord selectedRecord = records.get(identifier - 1);

        // print the selected record and ask for confirmation
        System.out.println("> Selected record:\n" + selectedRecord);
        System.out.print("> Are you sure you want to delete this record? (y/n): ");
        String confirmation = scanner.next();

        if (!confirmation.equalsIgnoreCase("y")) {
            System.out.println("Deletion cancelled");
            return false;
        }

        // delete the record
        impl.removeSocialPerformanceRecord(selectedRecord, salesman);

        // print a success message
        System.out.println("Record deleted successfully");

        return true;
    }
}
