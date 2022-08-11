package exam.revision.CryptoPractice.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Crypto {

    private String fsym; //ETH
    private String tsyms;//KRW,JPY,GBP..ETC
    private String price;

  
    public String getFsym() {
        return fsym;
    }
    public void setFsym(String fsym) {
        this.fsym = fsym;
    }
    public String getTsyms() {
        return tsyms;
    }
    public void setTsyms(String tsyms) {
        this.tsyms = tsyms;
    }

    public static Crypto create(JsonObject jo ){

        Crypto c = new Crypto();
        c.setFsym(jo.getString("fsym"));
        c.setTsyms(jo.getString("tsyms"));
        c.setPrice(jo.getString("price"));

        return c;

    }

    public JsonObject toJson(){

        return Json.createObjectBuilder()
                .add("fsym", fsym)
                .add("tsyms", tsyms)
                .add("price", price)
                .build();
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    
}
