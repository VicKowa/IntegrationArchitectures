package de.hbrs.ia.commands;

import de.hbrs.ia.code.SalesmanMongoImpl;
import de.hbrs.ia.model.SocialPerformanceRecord;
import de.hbrs.ia.model.SpecifiedRecord;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class AddSocialPerformanceRecordCmd implements CLIParser.Cmd {

    /**
     * Add a social performance record to an existing salesman
     *
     * @param args the arguments for the command
     * @return true if the command was executed successfully, false otherwise
     */
    @Override
    public boolean execute(String[] args) {
        // check if input is correct
        if (args.length != 4) {
            System.out.println("Usage: add_spr <sid> <department> <totalBonus> <year>");
            return false;
        }

        String department = args[1];

        int sid = 0;
        int totalBonus = 0;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date year = null;

        try {
            year = sdf.parse(args[3]);
            sid = Integer.parseInt(args[0]);
            totalBonus = Integer.parseInt(args[2]);
        } catch (ParseException e) {
            System.out.println("Invalid year format. Please use 'yyyy'");
            return false;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: sid and totalBonus must be integers");
            return false;
        }

        System.out.println("> Please enter the specified records for the social performance record: ");
        System.out.println("> Use the format: <targetValue> <actualValue> <bonus>");

        // for each specified record, ask the user to input a value
        Scanner sc = new Scanner(System.in);
        SpecifiedRecord[] records = new SpecifiedRecord[6];
        String[] names = {
                "Leadership Competence",
                "Openness to Employees",
                "Social Behaviour to Employee",
                "Attitude to Clients",
                "Communication Skills",
                "Integrity To Company"
        };

        for (int i = 0; i < 6; i++) {
            System.out.print("> " + names[i] + ": ");

            String[] values = sc.nextLine().split(" ");

            if (values.length != 3) {
                System.out.println("Usage: <targetValue> <actualValue> <bonus>");
                i--;
                continue;
            }

            int targetValue = 0;
            int actualValue = 0;
            int bonus = 0;

            try {
                targetValue = Integer.parseInt(values[0]);
                actualValue = Integer.parseInt(values[1]);
                bonus = Integer.parseInt(values[2]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: targetValue, actualValue and bonus must be integers");
                i--;
                continue;
            }

            // create a specified record
            records[i] = new SpecifiedRecord(targetValue, actualValue, bonus);
        }

        SocialPerformanceRecord spr = new SocialPerformanceRecord(
                department,
                totalBonus,
                year,
                records[0],
                records[1],
                records[2],
                records[3],
                records[4],
                records[5]
        );

        // add the social performance record to the salesman
        SalesmanMongoImpl impl = new SalesmanMongoImpl();
        impl.addSocialPerformanceRecord(spr, impl.readSalesMan(sid));

        return true;
    }
}
