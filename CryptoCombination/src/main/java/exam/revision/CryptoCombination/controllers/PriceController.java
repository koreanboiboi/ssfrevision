package exam.revision.CryptoCombination.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import exam.revision.CryptoCombination.services.PriceService;


@Controller
@RequestMapping("/price")
public class PriceController {

@Autowired
private PriceService priceSvc;

    @GetMapping
    public String getPrice(@RequestParam(name="coin") String coin,
                            @RequestParam(name="currency") String currency
                            ,Model model){
    
    model.addAttribute("coin",coin);
    model.addAttribute("currency",priceSvc.getCoinPrice(coin,currency));
    //model.addAttribute("currency",priceSvc.getCurrencyPrice(coin,currency));


        return "price";
    }
    
}
