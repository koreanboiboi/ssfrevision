package exam.revision.CryptoPractice2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import exam.revision.CryptoPractice2.models.CryptoModel;

@Service
public class CryptoService {
    
    private static final String URL = "https://min-api.cryptocompare.com/data/price";

    @Value("API_KEY")
    private String key;

    public List<CryptoModel> getCryptoModel(String coin, String currency){

        RestTemplate template = new RestTemplate();
        

        String url = UriComponentsBuilder.fromUriString(URL)
                        .queryParam("fsym", coin)
                        .queryParam("tsyms", currency)
                        .queryParam("API_KEY", key)
                        .toUriString();

        RequestEntity req = RequestEntity.get(url).build();
        ResponseEntity<String> resp = template.exchange(req, String.class);

        // ResponseEntity<String> resp = template.getForEntity(url, String.class);
         System.out.printf("Status code: %d\n",resp.getStatusCodeValue());
         System.out.printf("Payload: %s\n", resp.getBody());

         return null;

    }

}
