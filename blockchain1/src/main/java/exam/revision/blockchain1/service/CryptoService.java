package exam.revision.blockchain1.service;

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
public class CryptoService {

    private static final String URL = "https://min-api.cryptocompare.com/data/blockchain/list";
    @Value("${api_key}")
    private String key;


    public List<String> getCoin(){

        String url = UriComponentsBuilder.fromUriString(URL).queryParam("api_key", key).toUriString();

        RequestEntity<Void> req = RequestEntity.get(url).build();
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> resp = restTemplate.exchange(req, String.class);
        String payload = resp.getBody();

        Reader strReader = new StringReader(payload);
        JsonReader jsonReader = Json.createReader(strReader);
        JsonObject jsonObject = jsonReader.readObject();
        JsonObject data = jsonObject.getJsonObject("Data");
        Set<String> keys = data.keySet();
        List<String> keyList = new LinkedList<>(keys);



        return keyList;
    }
    
}
