package Controllers;

import BE.Country;
import DAL.DatabaseConnection;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christoffer on 11-05-2015.
 */
@RestController
public class CountryController   {
    public List<Country> clist = new ArrayList<>(); //Stores countries in memory
//    public Map<String, Country> countryMap = new HashMap<>();
    public boolean addMore = true;
    public DatabaseConnection db = new DatabaseConnection();

    //Accepts 4 parameters. Name, Alpha2, Alpha3, Exchangerate
    @RequestMapping(value = "/add/country", method = RequestMethod.POST)
    public String addCountry(  @RequestParam(value = "name") String name,
                                @RequestParam(value = "alpha2") String alpha2,
                                @RequestParam(value = "alpha3") String alpha3) {
        db.addCountry(name, alpha2, alpha3);
        return "{\"message\":\"Successfully added country '"+ name + "' with id\"}";
    }

    @RequestMapping(value = "/quickadd", method = RequestMethod.POST)
    public String quickAddCountries() {
        if(addMore) {
            db.addCountry("France", "fr", "FRA");
            db.addCountry("Germany", "de", "DEU");
            db.addCountry("United States", "us", "USA");
            addMore = false;
            System.out.println("Successfully added 3 countries");
            return "{\"message\":\"Success\"}";
        }
        System.out.println("Already added 3 countries");
        return "{\"message\":\"Already added 3 countries\"}";
    }

    @RequestMapping(value = "/delete/country", method = RequestMethod.DELETE)
    public Country deleteCountry(@RequestParam(value = "id") int id) {
        System.out.println("Deleting country with id " + id + " and name " + clist.get(id - 1).getName());
        Country c = new Country(id);
        return clist.remove(c.getId());
    }

    @RequestMapping(value = "/country", method = RequestMethod.GET)
    public Country getCountry(@RequestParam(value = "id") int id) {
        Country country = db.getCountry(id);
        System.out.println("Getting country with id " + id + " and name " + country.getName());
        return country;
    }

    @RequestMapping(value = "/countries", method = RequestMethod.GET)
    public List getCountries() {
        List<Country> countries = db.getCountries();
        System.out.println("Getting all " + countries.size() + " countries");
        return countries;
    }
}
