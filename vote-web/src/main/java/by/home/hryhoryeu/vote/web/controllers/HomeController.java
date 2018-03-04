package by.home.hryhoryeu.vote.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/vote")
public class HomeController {

    @RequestMapping()
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/view/{id}")
    public String showVote() {
        return "showVote";
    }

    @RequestMapping(value = "/view/add")
    public String addVote() {
        return "addVote";
    }

    @RequestMapping(value = "/view/remove/{id}/{pwd}")
    public String removeVote() {
        return "remove";
    }

    @RequestMapping(value = "/view/results/{id}")
    public String getResults() {
        return "results";
    }

}
