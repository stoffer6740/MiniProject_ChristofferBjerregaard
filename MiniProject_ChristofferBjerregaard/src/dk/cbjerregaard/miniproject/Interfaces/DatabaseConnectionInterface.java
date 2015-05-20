package dk.cbjerregaard.miniproject.Interfaces;

import dk.cbjerregaard.miniproject.BE.Country;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Christoffer on 12-05-2015.
 */
public interface DatabaseConnectionInterface {
    public String addCountry(String name, String alpha2, String alpha3);
    public Connection openDBConnection();
    public List<Country> getCountries();
    public Country getCountry(int id);
    public void deleteCountry(int id);
}
