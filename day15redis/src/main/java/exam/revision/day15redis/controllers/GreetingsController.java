package exam.revision.day15redis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = {"/",""})
public class GreetingsController {

    @Autowired 
    @Qualifier("redislab")
    private RedisTemplate<String,String> redisTemp;

    @GetMapping
    public String getGreeting(Model model){
        ValueOperations<String, String> op = redisTemp.opsForValue();
        Object greet = op.get("greet");
        model.addAttribute("hello",greet.toString());
        return "index";
    }

    @PostMapping
    public String postGreeting(@RequestBody MultiValueMap<String,String> form,String message, Model model){
       
        ValueOperations<String,String> op = redisTemp.opsForValue();       
        message = form.getFirst("message");
        op.set("greet", message);
        model.addAttribute("hello", message);

        return "index";

    }
}
