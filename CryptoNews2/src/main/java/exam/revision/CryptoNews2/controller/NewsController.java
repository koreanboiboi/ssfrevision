package exam.revision.CryptoNews2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import exam.revision.CryptoNews2.models.News;
import exam.revision.CryptoNews2.services.NewsService;

@Controller
@RequestMapping("/news")
public class NewsController {
    
    @Autowired
    private NewsService svc;

    @GetMapping
    public String getNews(Model model){

        List<News> news = svc.getNews();
        
        model.addAttribute("news",news);



        return "news";

    }
}
