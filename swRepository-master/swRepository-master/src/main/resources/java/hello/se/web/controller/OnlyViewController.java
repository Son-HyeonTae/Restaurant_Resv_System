package hello.se.web.controller;

import hello.se.domain.DBdata.Reservation;
import hello.se.web.Form.LoginForm;
import hello.se.web.Form.LoginValidationForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class OnlyViewController {

    @GetMapping()
    public String homeView() {
        log.info("first page");
        return "SW-Project-main/index";
    }

    @GetMapping("/menu")
    public String menuView() {
        log.info("menu page");
        return "SW-Project-main/menu";
    }

    @GetMapping("/book")
    public String bookView(Model model) {
        model.addAttribute("reservation", new Reservation());
        log.info("book page");
        return "SW-Project-main/book";
    }

    @GetMapping("/about")
    public String aboutView() {
        log.info("about page");
        return "SW-Project-main/about";
    }

    @GetMapping("/login")
    public String loginView(Model model) {
        model.addAttribute("loginValidationForm", new LoginValidationForm());
        model.addAttribute("loginForm", new LoginForm());
        log.info("login page");
        return "SW-Project-main/login_signup";
    }

    /*@GetMapping("/login/enter")
    public String loginEnter() {
        log.info("login enter");
        return "SW-Project-main/";
    }

    @GetMapping("/login/register")
    public String loginRegister() {
        log.info("login register");
        return "SW-Project-main/";
    }*/
}
