package hello.se.web.controller;

import hello.se.domain.DBdata.Login;
import hello.se.domain.DBdata.Reservation;
import hello.se.web.Form.LoginForm;
import hello.se.web.Form.LoginValidationForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class OnlyViewController {

    @GetMapping()
    public String homeView(Model model) {
        model.addAttribute("login", new Login());
        log.info("first page");
        return "SW-Project-main/index";
    }

    @GetMapping("/menu")
    public String menuView() {
        log.info("menu page");
        return "SW-Project-main/menu";
    }

    @GetMapping("/book")
    public String bookView(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Login currentUser = (Login) session.getAttribute("user");
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("login", currentUser);
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
//        model.addAttribute("login", new Login());
        log.info("login page");
        return "SW-Project-main/login_signup";
    }

    @GetMapping("/index/{key}")
    public String loginIndexView(@PathVariable Long key, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Login currentUser = (Login) session.getAttribute("user");
        model.addAttribute("login", currentUser);
        return "SW-Project-main/loginIndex";
    }

    @GetMapping("/menu/{key}")
    public String loginMenuView(@PathVariable Long key, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Login currentUser = (Login) session.getAttribute("user");
        model.addAttribute("login", currentUser);
        return "SW-Project-main/loginMenu";
    }

    @GetMapping("/about/{key}")
    public String loginAboutView(@PathVariable Long key, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Login currentUser = (Login) session.getAttribute("user");
        model.addAttribute("login", currentUser);
        return "SW-Project-main/loginAbout";
    }
    @GetMapping("/book/{key}")
    public String addLoginReservation(@PathVariable Long key, Model model,HttpServletRequest request) {
        HttpSession session = request.getSession();
        Login currentUser = (Login) session.getAttribute("user");
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("login", currentUser);
        return "SW-Project-main/loginBook";
    }

    @GetMapping("/login/{key}")
    public String enteredLoginView(@PathVariable Long key, Model model,HttpServletRequest request) {
        HttpSession session = request.getSession();
        Login currentUser = (Login) session.getAttribute("user");
        model.addAttribute("loginValidationForm", new LoginValidationForm());
        model.addAttribute("loginForm", new LoginForm());
        return "SW-Project-main/logout_signup";
    }
    //
}
