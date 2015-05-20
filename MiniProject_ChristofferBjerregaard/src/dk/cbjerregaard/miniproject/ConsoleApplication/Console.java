package dk.cbjerregaard.miniproject.ConsoleApplication;

import dk.cbjerregaard.miniproject.DAL.DatabaseConnection;
import dk.cbjerregaard.miniproject.Interfaces.DatabaseConnectionInterface;

import java.util.Scanner;

/**
 * Created by Christoffer on 12-05-2015.
 */
public abstract class Console implements DatabaseConnectionInterface {
    public static void main (String args[]) {
        int swValue;
        Scanner input = new Scanner(System.in);
        System.out.println("============================");
        System.out.println("|   MINIPROJECT CONSOLE    |");
        System.out.println("============================");
        System.out.println("| Options:                 |");
        System.out.println("|        1. Delete DB      |");
        System.out.println("|        2. Create DB      |");
        System.out.println("|        3. Add country    |");
        System.out.println("|        4. Exit           |");
        System.out.println("============================");
        swValue = input.nextInt();

// Switch construct
        switch (swValue) {
            case 1:
                System.out.println("Trying to delete DB");
                DatabaseConnection.deleteDatabase();
                break;
            case 2:
                System.out.println("Creating DB");
                DatabaseConnection.createDatabase();
                break;
            case 3:
                System.out.println("Add new country.");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid selection");
                break; // This break is not really necessary
        }
    }

}
