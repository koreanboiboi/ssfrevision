package exam.revision.CryptoCombination.services;

import java.io.Reader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
public class CoinService {

    private static final String COIN_URL = "https://min-api.cryptocompare.com/data/blockchain/list";
    @Value("${api_key}")
    private String apiKey;
    

    public List<String> getCoin(){

        String coinUrl = UriComponentsBuilder.fromUriString(COIN_URL)
                                             .queryParam("api_key", apiKey)
                                             .toUriString();

        RequestEntity<Void> requestCoin = RequestEntity.get(coinUrl).build();
        RestTemplate restTemp = new RestTemplate();

        ResponseEntity<String> responseCoin = restTemp.exchange(requestCoin, String.class);
        String coinPayload = responseCoin.getBody();

        Reader strReader = new StringReader(coinPayload);
        JsonReader jsonReader = Json.createReader(strReader);
        JsonObject jsonObject = jsonReader.readObject();
        JsonObject coinName = jsonObject.getJsonObject("Data");
        Set<String> coin = coinName.keySet();
        List<String> coinList = new LinkedList<>(coin);

        return coinList;

    }

}
