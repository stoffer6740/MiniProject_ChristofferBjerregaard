package dk.cbjerregaard.miniproject.ConsoleApplication;

import dk.cbjerregaard.miniproject.DAL.CountryDBManager;
import dk.cbjerregaard.miniproject.Service.RmiConnector;

import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by Christoffer on 12-05-2015.
 */
public abstract class Console {
    public static void main (String args[]) throws SQLException, ServerNotActiveException, RemoteException {
        int swValue;
        CountryDBManager cdbm = CountryDBManager.INSTANCE;
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
        System.out.println("Input one of the options please ...");
        swValue = input.nextInt();

// Switch construct
        switch (swValue) {
            case 1:
                System.out.println("Trying to delete DB");
//                DatabaseConnection.deleteDatabase();
                cdbm.deleteDatabase();
                break;
            case 2:
                System.out.println("Creating DB");
                cdbm.createDatabase();
                break;
            case 3:
                Scanner add = new Scanner(System.in);
                System.out.println("Add new country.");
                System.out.println("Type name of country (ex. 'Denmark'): ");
                String name = add.nextLine();
                System.out.println("Type alpha2 code of country (ex. 'dk'): ");
                String alpha2 = add.nextLine();
                System.out.println("Type alpha3 code of country (ex. 'DNK'): ");
                String alpha3 = add.nextLine();
                System.out.println("Press enter to add country: " + name + ", " + alpha2 + ", " + alpha3);
                add.nextLine();
                cdbm.addCountry(name, alpha2, alpha3);
                break;
            case 4:
                System.out.println("Checking RMI");
                RmiConnector.INSTANCE.getRmiServer().getClientInfo();
                break;
            default:
                System.out.println("Invalid selection");
                break;
        }
    }

}
