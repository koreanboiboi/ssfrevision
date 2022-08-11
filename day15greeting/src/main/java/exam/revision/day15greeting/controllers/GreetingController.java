package exam.revision.day15greeting.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/greeting")
public class GreetingController {

    // @GetMapping(path = {"/{name}"})
    // public String getGreetingWithName(@PathVariable String name, Model model){

    //     model.addAttribute("name",name);

    //     return "greeting";

    // }

        @GetMapping
        public String getGreeting(@RequestParam String get, Model model){
            model.addAttribute("get" , get);

            return "greeting";
        }

        @PostMapping
        public String postGreeting(@RequestBody MultiValueMap<String,String> form, String post, Model model){

            post = form.getFirst("post");
            model.addAttribute("post", post);

            return "greeting";

        }

}
