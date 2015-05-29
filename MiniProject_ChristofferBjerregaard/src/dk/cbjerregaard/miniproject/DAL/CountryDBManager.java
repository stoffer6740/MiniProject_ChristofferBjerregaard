package dk.cbjerregaard.miniproject.DAL;

import com.sun.org.apache.xpath.internal.operations.Bool;
import dk.cbjerregaard.miniproject.BE.Country;
import dk.cbjerregaard.miniproject.Interfaces.CountryDBManagerInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christoffer on 24-05-2015.
 */
public enum CountryDBManager implements CountryDBManagerInterface {
    INSTANCE;

    private DatabaseConnection databaseConnection;

    CountryDBManager() {
        databaseConnection = DatabaseConnection.INSTANCE.getInstance();
    }

    public List<Country> getCountries() {
        List<Country> countries = new ArrayList<>();
        Statement stmt = null;
        try (Connection con = databaseConnection.getConnection()) {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, name, alpha2, alpha3 FROM Country");
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
            con.close();
            System.out.println("getCountries - Returning " + countries.size() + " countries.");
            return countries;
        } catch (SQLException e) {
            System.out.println(e.getClass().getName() + " " + e.getMessage());
        }
        return null;
    }

    public Country getCountry(int id) {
        Country country = new Country();
        Statement stmt = null;
        try (Connection con = databaseConnection.getConnection()) {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, name, alpha2, alpha3 FROM Country WHERE id = " + id);
            while (rs.next()) {
                int cid = rs.getInt("id");
                String cname = rs.getString("name");
                String calpha2 = rs.getString("alpha2");
                String calpha3 = rs.getString("alpha3");
                country = new Country(cid, cname, calpha2, calpha3);
            }
            rs.close();
            stmt.close();
            con.close();
            System.out.println("getCountry - Returning data about " + country.getName() + " to the user.");
            return country;
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + " " + e.getMessage());
        }
        return country;
    }

    public void addCountry(String name, String alpha2, String alpha3) {
        Statement stmt = null;
        try (Connection con = databaseConnection.getConnection()) {
            stmt = con.createStatement();
            con.setAutoCommit(false);
            String sql = "INSERT INTO Country (name, alpha2, alpha3) VALUES(?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, name);
                ps.setString(2, alpha2);
                ps.setString(3, alpha3);
            ps.execute();
            stmt.close();
            con.commit();
            con.close();
            System.out.println("Added country: " + name);
        } catch (Exception e) {
            System.out.println("Failed to add country");
            System.out.println(e.getClass().getName() + " " + e.getMessage());
        }
    }

    public void deleteCountry(int id) {
        Statement stmt = null;
        try (Connection con = databaseConnection.getConnection()) {
            stmt = con.createStatement();
            con.setAutoCommit(false);
            String sql = "DELETE FROM Country where id = " + id;
            stmt.executeUpdate(sql);
            con.commit();
            System.out.println("Deleted country with id: " + id);
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + " " + e.getMessage());
            System.out.println("Could not delete the country with id: " + id);
        }
        System.out.println("Deletion complete.");
    }

    public void editCountry(int id, String name, String alpha2, String alpha3) {
        Statement stmt = null;
        try (Connection con = databaseConnection.getConnection()) {
            stmt = con.createStatement();
            con.setAutoCommit(false);
            String sql = "UPDATE Country " +
                            "SET name = ?, alpha2 = ?, alpha3 = ? " +
                            "WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, name);
                ps.setString(2, alpha2);
                ps.setString(3, alpha3);
                ps.setInt(4, id);
            ps.execute();
            stmt.close();
            con.commit();
            con.close();
        }
        catch (Exception e) {
            System.out.println(e.getClass().getName() + " " + e.getMessage());
        }
    }


    public void createDatabase() throws SQLException {
        Statement stmt = null;
        try (Connection con = databaseConnection.getConnection()) {
            stmt = con.createStatement();
            String sql = "CREATE TABLE Country (" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name     TEXT                    NOT NULL, " +
                    "alpha2   TEXT                    NOT NULL, " +
                    "alpha3   TEXT                    NOT NULL" +
                    ");";
            stmt.executeUpdate(sql);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteDatabase() {
        Statement stmt = null;
        try (Connection con = databaseConnection.getConnection()) {
            stmt = con.createStatement();
            String sql = "DROP TABLE Country";
            stmt.executeUpdate(sql);
            stmt.close();
            con.close();
            System.out.println("Table dropped successfully.");
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + " " + e.getMessage());
        }
    }
}
