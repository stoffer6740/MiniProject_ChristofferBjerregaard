package dk.cbjerregaard.miniproject.Controllers;

import dk.cbjerregaard.miniproject.BE.Country;
import dk.cbjerregaard.miniproject.DAL.CountryDBManager;
import dk.cbjerregaard.miniproject.DAL.DatabaseConnection;
import dk.cbjerregaard.miniproject.Interfaces.CountryDBManagerInterface;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christoffer on 11-05-2015.
 */
@RestController
public class CountryController {
    private CountryDBManager cdbm = null;

    //Accepts 4 parameters. Name, Alpha2, Alpha3, Exchangerate
    //adds one country
    @RequestMapping(value = "/add/country", method = RequestMethod.POST)
    public String addCountry(  @RequestParam(value = "name") String name,
                               @RequestParam(value = "alpha2") String alpha2,
                               @RequestParam(value = "alpha3") String alpha3) throws SQLException {
        cdbm = new CountryDBManager();
        cdbm.addCountry(name, alpha2, alpha3);
        return "{\"message\":\"Successfully added country '" + name + "' with id\"}";
    }
//
//    @RequestMapping(value = "/quickadd", method = RequestMethod.POST)
//    public String quickAddCountries() {
//        if(addMore) {
//            db.addCountry("France", "fr", "FRA");
//            db.addCountry("Germany", "de", "DEU");
//            db.addCountry("United States", "us", "USA");
//            addMore = false;
//            System.out.println("Successfully added 3 countries");
//            return "{\"message\":\"Success\"}";
//        }
//        System.out.println("Already added 3 countries");
//        return "{\"message\":\"Already added 3 countries\"}";
//    }

//    //Deletes one country
    @RequestMapping(value = "/delete/country", method = RequestMethod.DELETE)
    public String deleteCountry(@RequestParam(value = "id") int id) throws SQLException {
        cdbm = new CountryDBManager();
        cdbm.deleteCountry(id);
        return "{\"message\":\"Success\"}";
    }

    //returns one country
    @RequestMapping(value = "/country", method = RequestMethod.GET)
    public Country getCountry(@RequestParam(value = "id") int id) throws SQLException {
        cdbm = new CountryDBManager();
        return cdbm.getCountry(id);
    }

    // Edits a country
    @RequestMapping(value = "/edit/country", method = RequestMethod.PUT)
    public Country editCountry(@RequestParam(value = "id")int id) {
        return new Country();
    }

    //returns all countries
    @RequestMapping(value = "/countries", method = RequestMethod.GET)
    public List getCountries() throws SQLException {
        cdbm = new CountryDBManager();
        return cdbm.getCountries();
    }
}
