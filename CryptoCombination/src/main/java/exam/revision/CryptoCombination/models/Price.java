// package exam.revision.CryptoCombination.models;

// import jakarta.json.Json;
// import jakarta.json.JsonObject;

// public class Price {

//     private String coin;
//     private String currency;

//     public String getCoin() {
//         return coin;
//     }
//     public void setCoin(String coin) {
//         this.coin = coin;
//     }
//     public String getCurrency() {
//         return currency;
//     }
//     public void setCurrency(String currency) {
//         this.currency = currency;
//     }
    
//     public static Price create(JsonObject jo) {

//         Price p = new Price();
//         p.setCoin(jo.getString("coin"));
//         p.setCurrency(jo.getString("currency"));
        

//         return p;

//     }

//     public JsonObject toJson(){

//         return Json.createObjectBuilder()
//                 .add("coin", coin)
//                 .add("currency", currency)
//                 .build();
//     }

// }
