package exam.revision.CryptoPractice.services;

import java.io.Reader;
import java.io.StringReader;
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

import exam.revision.CryptoPractice.models.Crypto;
import exam.revision.CryptoPractice.repository.CryptoRepository;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;


@Service
public class CryptoService {
    
    
    // @Autowired
    // @Qualifier("redislab")
    // private RedisTemplate<String, String> redisTemplate;

    // public List<String> keys(){
    //     Set<String> keys = redisTemplate.keys("[0-9]*");
    //     List<String> result = new LinkedList<>(keys);
    //     return result.stream().toList();
    // }
    
    // public Object get(String id){
    //     ValueOperations<String,String> valueOps = redisTemplate.opsForValue();
    //     return valueOps.get(id);
    // }

    private static final String URL = "https://min-api.cryptocompare.com/data/price";

    @Value("${API_KEY")
    private String key;
    
    @Autowired
    private CryptoRepository cryptoRepo;

    public List<Crypto> getCrypto(String fsym, String tsyms){
        
        Optional<String> opt = cryptoRepo.get(tsyms);
        String payload;

        if(opt.isEmpty()){

        try {
            
            String url = UriComponentsBuilder.fromUriString(URL)
                     .queryParam("fsym", fsym)
                     .queryParam("tsyms", tsyms)
                     .queryParam("API_KEY", key)
                     .toUriString();


        RequestEntity<Void> req = RequestEntity.get(url).build();

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp;

        resp = template.exchange(req, String.class);

        payload = resp.getBody();
            
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e);
            return Collections.emptyList();
        }
    } else {
        payload = opt.get();
        System.out.println(payload);
    }
        

        Reader strReade = new StringReader(payload);
        JsonReader jsonReader = Json.createReader(strReade);
        JsonObject cryptoResult = jsonReader.readObject();
        JsonArray result = cryptoResult.getJsonArray("price");
        List<Crypto> list = new LinkedList<>();
        for(int i = 0; i <result.size();i++){
            JsonObject jo = result.getJsonObject(i);
            list.add(Crypto.create(jo));

        }
        return list;
    }

}
