package exam.revision.blockchain.models;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Blockchain {
    private Integer id;
    private String symbol;
    private String partner_symbol;
    private Integer data_available_from;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public String getPartner_symbol() {
        return partner_symbol;
    }
    public void setPartner_symbol(String partner_symbol) {
        this.partner_symbol = partner_symbol;
    }
    public Integer getData_available_from() {
        return data_available_from;
    }
    public void setData_available_from(Integer data_available_from) {
        this.data_available_from = data_available_from;
    } 

    public static Blockchain create(String jsonStr){
        StringReader strReader = new StringReader(jsonStr);
        JsonReader reader = Json.createReader(strReader);
        return create(reader.readObject());
    }

    public static Blockchain create(JsonObject jo){
        Blockchain blockchain = new Blockchain();

        blockchain.setId(jo.getInt("id"));
        blockchain.setSymbol(jo.getString("symbol"));
        blockchain.setPartner_symbol(jo.getString("partner_symbol"));
        blockchain.setData_available_from(jo.getInt("data_available_from"));
        return blockchain;
    }
    public JsonObject toJson(){

        return Json.createObjectBuilder().add("id", id)
        .add("symbol", symbol).add("partner_symbol", partner_symbol)
        .add("data_available_from", data_available_from)
        .build();
    }
}
