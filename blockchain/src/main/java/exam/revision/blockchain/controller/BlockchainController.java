package exam.revision.blockchain.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import exam.revision.blockchain.models.Blockchain;
import exam.revision.blockchain.services.BlockchainService;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@Controller
@RequestMapping(path="/blockchain")
public class BlockchainController {

    @Autowired
    private BlockchainService blockchainSvc;

    @GetMapping("/blockchain")
    public String getAllBlockchain(){
        
        blockchainSvc.getData();

        return "blockchain";
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<String> getBlockchain(@PathVariable String id){

        Optional<Blockchain> opt = blockchainSvc.getBlockchainById(id);
        if(opt.isEmpty()){
            JsonObject error = Json.createObjectBuilder()
                                    .add("error", "ID %s not found"
                                    .formatted(id)).build();

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.toString());
        }
        Blockchain blockchain = opt.get();
        return ResponseEntity.ok(blockchain.toJson().toString());
    }
    
}
