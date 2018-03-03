package by.home.hryhoryeu.vote.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/vote")
public class HomeController {

    @RequestMapping(value = "")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/view/{id}")
    public String showVote(@PathVariable("id") String id) {
        return "showVote";
    }

    @RequestMapping(value = "/view/add")
    public String addVote() {
        return "addVote";
    }

    @RequestMapping(value = "/view/remove/{id}/{pwd}")
    public String removeVote(@PathVariable("id") String id,
                             @PathVariable("pwd") String pwd) {
        return "remove";
    }

}
