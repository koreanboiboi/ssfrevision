package exam.revision.CryptoNews.services;

import java.io.Reader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class NewsService {

    private static final String NewsURL = "https://min-api.cryptocompare.com/data/v2/news/";
    @Value("${api_key}")
    private String apikey;


    public List<JsonObject> getNews(){

        String newsurl = UriComponentsBuilder.fromUriString(NewsURL)
                                             .queryParam("lang", "EN")
                                             .queryParam("api_key", apikey)
                                             .toUriString();

        RequestEntity<Void> req = RequestEntity.get(newsurl).build();
        RestTemplate restTemp = new RestTemplate();
        ResponseEntity<String> resp = restTemp.exchange(req, String.class);
        
        String payload = resp.getBody();
        Reader strReader = new StringReader(payload);       
        JsonReader jsonReader = Json.createReader(strReader);
        JsonObject jsonObject = jsonReader.readObject();
        JsonArray data = jsonObject.getJsonArray("Data");
        
        List<JsonObject> list = new LinkedList<>();
        
        for(int i =0; i<data.size(); i++){
            list.add(data.getJsonObject(i));
        }

        // for(int i = 0; i<data.size(); i++){
        //     JsonObject jo = data.getJsonObject(i);
        //     list.add(News.create(jo));
        // }
    
        list.toString();

        return list;

    }
    
}
