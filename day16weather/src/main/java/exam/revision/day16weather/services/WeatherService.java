package exam.revision.day16weather.services;

import java.io.Reader;
import java.io.StringReader;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import exam.revision.day16weather.models.Weather;
import exam.revision.day16weather.repositories.WeatherRepository;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class WeatherService {

    //since we are calling from API
    private static final String URL = "https://api.openweathermap.org/data/2.5/weather";

    @Value("${API_KEY}")
    private String key;

    @Autowired
    private WeatherRepository weatherRepo;


    public List<Weather> getWeather(String city){

        Optional<String> opt = weatherRepo.get(city);
        String payload;
        if(opt.isEmpty()){

            try {

                String url = UriComponentsBuilder.fromUriString(URL)
                                         .queryParam("q", URLEncoder.encode(city,"UTF-8"))
                                         .queryParam("appid", key)
                                         .toUriString();

        RequestEntity<Void> request = RequestEntity.get(url).build();
        RestTemplate template = new RestTemplate();
        ResponseEntity <String> response;
        response = template.exchange(request, String.class);

         payload = response.getBody();
                
            } catch (Exception e) {
                //TODO: handle exception
                System.err.println(e);
                return Collections.emptyList();
            }
        } else{
            payload = opt.get();
            System.out.println(payload);
        }

        //-------------------------------------------------------------------------------
        
        // String url = UriComponentsBuilder.fromUriString(URL)
        //                                  .queryParam("q", city)
        //                                  .queryParam("appid", key)
        //                                  .toUriString();

        // RequestEntity<Void> request = RequestEntity.get(url).build();
        // RestTemplate template = new RestTemplate();
        // ResponseEntity <String> response;
        // response = template.exchange(request, String.class);

        // String payload = response.getBody();

        Reader strReader = new StringReader(payload);
        JsonReader jsonReader = Json.createReader(strReader);
        JsonObject weatherResult = jsonReader.readObject();
        JsonArray cities = weatherResult.getJsonArray("weather");

        List<Weather> list = new LinkedList<>();

        for(int i =0; i < cities.size(); i++){
            JsonObject jo = cities.getJsonObject(i);
            list.add(Weather.create(jo));
        }
        return list;
    }
    
}
