package exam.revision.CryptoCombination.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import exam.revision.CryptoCombination.services.CoinService;

@Controller
@RequestMapping("/coin")
public class CoinControllers {

    @Autowired
    private CoinService coinSvc;

    @GetMapping
    public String getCoin(Model model){

        List<String> coinList =  coinSvc.getCoin();
        model.addAttribute("coinList",coinList);

        return "coin";
    }
    
}
