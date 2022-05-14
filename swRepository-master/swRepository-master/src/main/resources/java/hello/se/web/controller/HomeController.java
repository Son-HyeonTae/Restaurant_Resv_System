package hello.se.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String homeView() {
        return "SW-Project-main/index";
    }

    @GetMapping("/menu")
    public String menuView() {
        return "SW-Project-main/menu";
    }

    @GetMapping("/book")
    public String bookView() {
        return "SW-Project-main/book";
    }

    @GetMapping("/about")
    public String aboutView() {
        return "SW-Project-main/about";
    }
}
