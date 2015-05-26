package dk.cbjerregaard.miniproject.Service;

import dk.cbjerregaard.miniproject.Shared.RegistryConfig;
import dk.cbjerregaard.miniproject.Shared.ServerConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by prep on 20-02-2015.
 */
public class RmiServerImpl extends UnicastRemoteObject implements RmiServer {
    private static CurrencyLoader currencyCache = CurrencyLoader.INSTANCE;
    private static HashMap<String, Double> currencyExchange = new HashMap<>();
    private static int rmiStatus;
    private static int currencyStatus;

    public RmiServerImpl() throws RemoteException {
        super(0);
    }

    public static void main(String args[]) throws Exception {
        System.out.println("RMI server started");

        try { //special exception handler for registry creation
            Registry registry = LocateRegistry.createRegistry(RegistryConfig.REGISTRY_PORT);
            registry.rebind(RegistryConfig.INSTANCE_NAME, new RmiServerImpl());
            System.out.println("java RMI registry created.");
            rmiStatus = 1;
        } catch (RemoteException e) {
            //do nothing, error means registry already exists
            System.out.println("java RMI registry already exists.");
        }

        //Instantiate Service.RmiServer
        RmiServerImpl obj = new RmiServerImpl();

        // Bind this object instance to the name "Service.RmiServer"
        Naming.rebind(ServerConfig.SERVER_ADDRESS, obj);
        System.out.println("PeerServer bound in registry");

        // Fetch currencies from yahoo
        System.out.println("Fetching initial currencies...");
        fillCurrencyCache();
        System.out.println("All currencies are up to date");
    }

    protected static void fillCurrencyCache() {
        List<String> currencies = currencyCache.getCurrencyList();

        for (String sourceCurrency : currencies) {
            for (String targetCurrency : currencies) {
                String appendedCurrency = sourceCurrency + targetCurrency;

                Double value = fetchSingleCurrency(appendedCurrency);
                currencyExchange.put(appendedCurrency, value);
            }
        }

        currencyStatus = 1;
    }

    private static double fetchSingleCurrency(String currency) {
        String req = "http://download.finance.yahoo.com/d/quotes.csv?s=" +currency.toUpperCase() +"=X&f=l1";        String exchangeValue = "";

        try {
            URL url = new URL(req);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            exchangeValue = in.readLine();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Double.valueOf(exchangeValue);
    }

    @Override
    public double exchangeRate(String sourceCurrency, String targetCurrency, Double amount) {
        String appendedCurrency = sourceCurrency + targetCurrency;

        if (currencyExchange.containsKey(appendedCurrency))
            return currencyExchange.get(appendedCurrency) * amount;
        else {
            double value = fetchSingleCurrency(appendedCurrency);
            currencyExchange.put(appendedCurrency, value);
            return value * amount;
        }
    }

    @Override
    public double exchangeRate(String sourceCurrency, String targetCurrency) {
        return exchangeRate(sourceCurrency, targetCurrency, 1d);
    }

    @Override
    public void getClientInfo() throws ServerNotActiveException {
        // log it instead if a logger was implemented
        System.err.println("Connected client: " + RemoteServer.getClientHost());
    }

    @Override
    public int getClientStatus() throws RemoteException {
        if(rmiStatus == 1) {
            return rmiStatus;
        }
        return 0;
    }

    @Override
    public int getCurrencyStatus() throws RemoteException {
        if(currencyStatus == 1) {
            return currencyStatus;
        }
        return 0;
    }
}