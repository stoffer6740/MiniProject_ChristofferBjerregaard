package dk.cbjerregaard.miniproject.Controllers;

import dk.cbjerregaard.miniproject.Application.Server;
import dk.cbjerregaard.miniproject.BE.Country;
import dk.cbjerregaard.miniproject.Config.RegistryConfig;
import dk.cbjerregaard.miniproject.Config.ServerConfig;
import dk.cbjerregaard.miniproject.DAL.CountryDBManager;
import dk.cbjerregaard.miniproject.Service.RmiConnector;
import dk.cbjerregaard.miniproject.Service.RmiServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Christoffer on 11-05-2015.
 */
@RestController
public class CountryController {
    RmiServer server;
    private static CountryDBManager _cdbManager = CountryDBManager.INSTANCE;
    private static RmiConnector _rmiConnector = RmiConnector.INSTANCE;

    //returns all countries
    @RequestMapping(value = "/get/countries", method = RequestMethod.GET)
    public List getCountries() {
        return _cdbManager.getCountries();
    }

    //returns one country
    @RequestMapping(value = "/get/country", method = RequestMethod.GET)
    public Country getCountry(@RequestParam(value = "id") int id) {
        return _cdbManager.getCountry(id);
    }

    //Returns exchange rate between two currencies, or converts them, based on parameters.
    @RequestMapping(value = "/get/JSONCountryCurrencies", method = RequestMethod.GET)
    public Double getJSONCountryCurrencies(@RequestParam(value = "sourceCurrency") String sourceCurrency,
                                            @RequestParam(value = "targetCurrency") String targetCurrency,
                                            @RequestParam(value = "amount", required = false) Double amount) throws RemoteException {
        if(amount == null) {
            return _rmiConnector.getRmiServer().exchangeRate(sourceCurrency, targetCurrency);
        }
        else {
            return _rmiConnector.getRmiServer().exchangeRate(sourceCurrency, targetCurrency, amount);
        }
    }

    //Returns the status of the RMI server
    @RequestMapping(value = "/get/RmiServer", method = RequestMethod.GET)
    public String getRmiServer() throws RemoteException, MalformedURLException, NotBoundException, ServerNotActiveException {
        try (Socket ignored = new Socket(ServerConfig.SERVER_IP, RegistryConfig.REGISTRY_PORT)) {
            try {
                server = (RmiServer) Naming.lookup(ServerConfig.SERVER_ADDRESS);
                // Log client on RMI server
                _rmiConnector.getRmiServer().getClientInfo();
                return "{\"status\":\"1\"}";
            } catch (NotBoundException | MalformedURLException | RemoteException | ServerNotActiveException e) {
                System.out.println(e.getMessage());
            }
        } catch (IOException ex) {
            return "{\"status\":\"0\"}";
        }
        return "{\"status\":\"0\"}";
    }


    //Accepts 4 parameters. Name, Alpha2, Alpha3, Exchangerate
    //adds one country
    @RequestMapping(value = "/post/country", method = RequestMethod.POST)
    public String addCountry(  @RequestParam(value = "name") String name,
                               @RequestParam(value = "alpha2") String alpha2,
                               @RequestParam(value = "alpha3") String alpha3) throws SQLException {
        _cdbManager.addCountry(name, alpha2, alpha3);
        return "{\"message\":\"Successfully added country '" + name + "' with id\"}";
    }

    //Deletes one country
    @RequestMapping(value = "/delete/country", method = RequestMethod.DELETE)
    public String deleteCountry(@RequestParam(value = "id") int id) {
        _cdbManager.deleteCountry(id);
        return "{\"message\":\"Success\"}";
    }

    // Edits a country
    @RequestMapping(value = "/put/country", method = RequestMethod.PUT)
    public Country editCountry(@RequestParam(value = "id")int id,
                               @RequestParam(value = "name")String name,
                               @RequestParam(value = "alpha2")String alpha2,
                               @RequestParam(value = "alpha3")String alpha3) {
        System.out.println("Editing " + id);
        _cdbManager.editCountry(id, name, alpha2, alpha3);
        return new Country();
    }
}