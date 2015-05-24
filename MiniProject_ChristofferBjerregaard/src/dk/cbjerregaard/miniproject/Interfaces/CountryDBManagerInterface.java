package dk.cbjerregaard.miniproject.Interfaces;

import dk.cbjerregaard.miniproject.BE.Country;
import dk.cbjerregaard.miniproject.DAL.CountryDBManager;
import dk.cbjerregaard.miniproject.DAL.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Christoffer on 12-05-2015.
 */
public interface CountryDBManagerInterface {
    List<Country> getCountries() throws SQLException;
    Country getCountry(int id);
    void deleteCountry(int id);
    void addCountry(String name, String alpha2, String alpha3);
    void createDatabase() throws SQLException;
    void deleteDatabase() throws SQLException;
}
