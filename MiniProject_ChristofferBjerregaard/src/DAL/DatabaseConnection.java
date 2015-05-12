package DAL;

import BE.Country;
import Interfaces.DatabaseConnectionInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christoffer on 12-05-2015.
 */
public class DatabaseConnection implements DatabaseConnectionInterface {
    public static final String DATABASE_FILE = "jdbc:sqlite:miniproject_db.db";

    public static void createDatabase() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(DATABASE_FILE);
            System.out.println("Opened database successfully.");

            stmt = c.createStatement();
            String sql = "CREATE TABLE Country (" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name     TEXT                    NOT NULL, " +
                    "alpha2   TEXT                    NOT NULL, " +
                    "alpha3   TEXT                    NOT NULL" +
                    ");";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        }
        catch (Exception e) {
            System.out.println(e.getClass().getName() + " " + e.getMessage());
        }
        System.out.println("Table created successfully.");
    }

    public static void deleteDatabase() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(DATABASE_FILE);
            System.out.println("Opened database successfully.");

            stmt = c.createStatement();
            String sql = "DROP TABLE Country";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
            System.out.println("Table dropped successfully.");
        }
        catch (Exception e) {
            System.out.println(e.getClass().getName() + " " + e.getMessage());
        }
    }

    public Connection openDBConnection() {
        createDatabase();
        Connection c = null;
        try {
            c = DriverManager.getConnection(DATABASE_FILE);
            System.out.println("Opened database successfully.");
            return c;
        }catch (Exception e) {
            System.out.println(e.getClass().getName() + " " + e.getMessage());
        }
        System.out.println("Failed to open database.");
        return c;
    }

    public String addCountry(String name, String alpha2, String alpha3) {
        Connection c = openDBConnection();
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            c.setAutoCommit(false);
            String sql =  "INSERT INTO Country (name, alpha2, alpha3) " +
            String.format("VALUES ('" + name + "','" + alpha2 + "','" + alpha3 + "');");
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
            return "Successfully added country";
        }catch (Exception e) {
            System.out.println(e.getClass().getName() + " " + e.getMessage());
        }
        return "Failed to add country";
    }

    public Country getCountry(int id) {
        Country country = new Country();
        Connection c = openDBConnection();
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, name, alpha2, alpha3 FROM Country WHERE id = " + id);

            System.out.println("Selecting country with id: " + id);
            while (rs.next()) {
                int cid = rs.getInt("id");
                String cname = rs.getString("name");
                String calpha2 = rs.getString("alpha2");
                String calpha3 = rs.getString("alpha3");
                country = new Country(cid, cname, calpha2, calpha3);
            }
            rs.close();
            stmt.close();
            c.close();
            System.out.println("return countries list success " + country.toString());
            return country;
        }catch (Exception e){
            System.out.println(e.getClass().getName() + " " + e.getMessage());
        }
        System.out.println("Could not return a country with id: " + id);
        return country;
    }


    public List<Country> getCountries() {
        List<Country> countries = new ArrayList<>();
        Connection c = openDBConnection();
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, name, alpha2, alpha3 FROM Country");
            System.out.println("Selecting all from Country");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String alpha2 = rs.getString("alpha2");
                String alpha3 = rs.getString("alpha3");
                Country country = new Country(id, name, alpha2, alpha3);
                countries.add(country);
            }
            rs.close();
            stmt.close();
            c.close();
            System.out.println("return countries list success " + countries.size());
            return countries;
        }catch (Exception e){
            System.out.println(e.getClass().getName() + " " + e.getMessage());
        }
        System.out.println("return countries list");
        return countries;
    }
}
