package exam.revision.day16weather.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import exam.revision.day16weather.models.Weather;
import exam.revision.day16weather.services.WeatherService;

@Controller
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherSvc;

    @GetMapping
    public String getWeather(@RequestParam String city ,Model model){

        List<Weather> weather = weatherSvc.getWeather(city);
        model.addAttribute("city",city);
        model.addAttribute("weather",weather);
        return "weather";

    }
    
}
