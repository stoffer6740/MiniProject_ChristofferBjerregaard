package dk.cbjerregaard.miniproject.BE;

/**
 * Created by Christoffer on 11-05-2015.
 */
public class Country {
    private int id;
    private String name;
    private String alpha2;
    private String alpha3;

    public Country(int id, String name, String alpha2, String alpha3) {
        this.id             = id;
        this.name           = name;
        this.alpha2         = alpha2;
        this.alpha3         = alpha3;
    }

    public Country(int id) {
        this.id = id;
    }

    public Country() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlpha2() {
        return alpha2;
    }

    public void setAlpha2(String alpha2) {
        this.alpha2 = alpha2;
    }

    public String getAlpha3() {
        return alpha3;
    }

    public void setAlpha3(String alpha3) {
        this.alpha3 = alpha3;
    }

    public String addSuccess() {
        return "{\"message\":\"Success!\"}";
    }
}
