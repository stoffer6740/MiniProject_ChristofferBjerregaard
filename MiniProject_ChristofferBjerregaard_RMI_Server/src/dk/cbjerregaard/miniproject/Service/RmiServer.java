package dk.cbjerregaard.miniproject.Service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by prep on 20-02-2015.
 */
public interface RmiServer extends Remote {
    double exchangeRate(String sourceCurrency, String targetCurrency, Double amount) throws RemoteException;

    double exchangeRate(String sourceCurrency, String targetCurrency) throws RemoteException;

    void getClientInfo() throws ServerNotActiveException, RemoteException;

    HashMap showAppendedCurrencyList() throws RemoteException;

    HashMap showCurrencyList() throws RemoteException;

    public List showAvailableCountries() throws RemoteException;
}