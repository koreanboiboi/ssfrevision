package exam.revision.CryptoCombination.services;

import java.io.Reader;
import java.io.StringReader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class PriceService {

    private static final String PRICE_URL = "https://min-api.cryptocompare.com/data/price";

    @Value("${api_key")
    private String key;

    public String getCoinPrice(String coin, String currency){
    
        String priceUrl = UriComponentsBuilder.fromUriString(PRICE_URL)
                                              .queryParam("fsym",coin)
                                              .queryParam("tsyms",currency)
                                              .queryParam("api_key", key)
                                              .toUriString();

        RequestEntity<Void> reqPrice = RequestEntity.get(priceUrl).build();
        RestTemplate restTemp = new RestTemplate();
        ResponseEntity<String> respPrice = restTemp.exchange(reqPrice, String.class);
        String pricePayload = respPrice.getBody();

        Reader strReader = new StringReader(pricePayload);
        JsonReader jsonReader = Json.createReader(strReader);
        JsonObject jsonObject = jsonReader.readObject();
        String priceResult = jsonObject.toString();
        
        


        return priceResult;

    }
    
}
