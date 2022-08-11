package exam.revision.blockchain1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import exam.revision.blockchain1.service.CryptoService;

@Controller
@RequestMapping({""})
public class CryptoController {

@Autowired
private CryptoService crSvc;

    @GetMapping
    public String getPrice(Model model){

        List<String> keyList = crSvc.getCoin();
        model.addAttribute("keyList",keyList);


        return "index";

    }
    
}
