package dk.cbjerregaard.miniproject.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by prep on 24-02-2015.
 */
public enum CurrencyLoader {
    INSTANCE;

    private List<String> currencyList;

    CurrencyLoader() {
        InputStream inputstream = getClass().getResourceAsStream("/Currency");
        currencyList = new ArrayList<String>();
        int data = 0;
        try {
            data = inputstream.read();
            String currency = "";
            while (data != -1) {
                currency += (char) data;

                data = inputstream.read();
            }
            inputstream.close();

            for (String str : currency.split("\n")) {
                currencyList.add(str.trim());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getCurrencyList() {
        return currencyList;
    }
}
