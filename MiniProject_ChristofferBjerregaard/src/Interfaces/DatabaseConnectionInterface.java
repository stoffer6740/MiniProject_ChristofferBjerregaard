package Interfaces;

import BE.Country;
import DAL.DatabaseConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christoffer on 12-05-2015.
 */
public interface DatabaseConnectionInterface {
    public String addCountry(String name, String alpha2, String alpha3);
    public Connection openDBConnection();
    public List<Country> getCountries();
    public Country getCountry(int id);
}
