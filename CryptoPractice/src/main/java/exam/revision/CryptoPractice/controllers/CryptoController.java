package exam.revision.CryptoPractice.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import exam.revision.CryptoPractice.models.Crypto;
import exam.revision.CryptoPractice.services.CryptoService;

@Controller
@RequestMapping(path="/price")
public class CryptoController {

    @Autowired
    private CryptoService cryptoSvc;

    @GetMapping
    public String getPrice(
            @RequestParam(name="coin") String coin,
            @RequestParam(name="currency") String currency, 
            Model model){

        System.out.printf("coin : %s, currency : %s\n", coin, currency);

         List<Crypto> crypto = cryptoSvc.getCrypto(coin, currency);
         model.addAttribute("coin" , coin);
         model.addAttribute("currency" , currency);

        return "price";

    }


    
}
