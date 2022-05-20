package hello.se.web.controller;

import hello.se.domain.DBdata.Login;
import hello.se.domain.respository.LoginRepository;
import hello.se.web.Form.LoginForm;
import hello.se.web.Form.LoginValidationForm;
import hello.se.web.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class LoginController {
    private LoginRepository loginRepository;
    private LoginService loginService;

    @Autowired
    public LoginController(LoginRepository loginRepository, LoginService loginService) {
        this.loginService = loginService;
        this.loginRepository = loginRepository;
    }

    @PostMapping("/login/enter")
    public String enterLogin(@Validated @ModelAttribute("loginValidation") LoginValidationForm validationForm,
                             RedirectAttributes redirectAttributes, HttpServletRequest request) {
        Login currentUser = loginService.inspectLogin(validationForm);
        HttpSession session = request.getSession();

        if (currentUser == null) {
            log.info("login fail");
            return "redirect:/login";
        }

        redirectAttributes.addAttribute("key", currentUser.getKey());
        session.setAttribute("user", currentUser);
        log.info("login success");
        return "redirect:/book/{key}";
    }

    @PostMapping("/login/register")
    public String addInfo(@ModelAttribute LoginForm loginForm) {
        loginRepository.saveWeb(loginForm);
        return "redirect:/";
    }

    @GetMapping("/logout/{key}")
    public String logout(@PathVariable Long key,HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "redirect:/";
    }
}