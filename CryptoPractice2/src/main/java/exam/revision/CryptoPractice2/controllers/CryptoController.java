package exam.revision.CryptoPractice2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import exam.revision.CryptoPractice2.models.CryptoModel;
import exam.revision.CryptoPractice2.services.CryptoService;

@RestController
@RequestMapping(path="/coinprice", produces =MediaType.APPLICATION_JSON_VALUE)
public class CryptoController {

    @Autowired
    private CryptoService cryptoSvc;

    @GetMapping
    public String getCoin(@RequestParam(name = "coin") String coin,
                          @RequestParam(name = "currency") String currency,
                          @RequestParam(name = "price") String price,
                          Model model ) {

    
    System.out.printf("coin:%s  currency:%s  price:%s\n",coin,currency,price);

    List<CryptoModel> crypto = cryptoSvc.getCryptoModel(coin, currency);
         model.addAttribute("coin" , coin);
         model.addAttribute("currency" , currency);

    return null;
        
    }
    


}
