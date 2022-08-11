package exam.revision.CryptoNews.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import exam.revision.CryptoNews.services.NewsService;

@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsSvc;


    @GetMapping
    public String getNews(Model model){

        // List<JsonObject> list = newsSvc.getNews();

        
        model.addAttribute("news",newsSvc.getNews());
        
       
        
        return "news";
        
    }


    
}
