package exam.revision.blockchain.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import exam.revision.blockchain.models.Blockchain;

@Service
public class BlockchainService {

    private static final String URL = "https://min-api.cryptocompare.com/data/blockchain/list";

    @Value("${API_KEY")
    private String key;
    String payload;
    public String getData(){

        String url = UriComponentsBuilder.fromUriString(URL)
                                          .queryParam("api_key", key)
                                          .toUriString();

        RequestEntity<Void> req = RequestEntity.get(url).build();
        RestTemplate temp = new RestTemplate();
        ResponseEntity <String> resp = temp.exchange(req,String.class);
        payload = resp.getBody();

        return payload;
    }

    public Optional<Blockchain> getBlockchainById(Integer id){
        return getBlockchainById(id.toString());
    }

    public Optional<Blockchain> getBlockchainById(String id){
        return Optional.of(Blockchain.create(id));
    }
    
}
