package exam.revision.CryptoPractice2.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class CryptoModel {

    private String coin;
    private String currency;

    public String getCoin() {return coin;}
    public void setCoin(String coin) {this.coin = coin;}

    public String getCurrency() {return currency;}
    public void setCurrency(String currency) {this.currency = currency;}

    public static CryptoModel create(JsonObject jo ){

        CryptoModel c = new CryptoModel();
        c.setCoin(jo.getString("fsym"));
        c.setCurrency(jo.getString("tsyms"));

        return c;

    }

    public JsonObject toJson(){

        return Json.createObjectBuilder()
                .add("fsym", coin)
                .add("tsyms", currency)
                .build();
    }

}
