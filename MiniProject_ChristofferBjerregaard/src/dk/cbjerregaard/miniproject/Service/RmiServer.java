package dk.cbjerregaard.miniproject.Service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Christoffer on 26-05-2015.
 */
public interface RmiServer extends Remote {
    double exchangeRate(String sourceCurrency, String targetCurrency, Double amount) throws RemoteException;

    double exchangeRate(String sourceCurrency, String targetCurrency) throws RemoteException;

    void getClientInfo() throws ServerNotActiveException, RemoteException;

    int getClientStatus() throws RemoteException;

    int getCurrencyStatus() throws RemoteException;

//    void setUpdaterSettings(int delay, int period, TimeUnit timeUnit) throws RemoteException;
}
