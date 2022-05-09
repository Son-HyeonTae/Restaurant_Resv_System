package hello.se.web.controller;

import hello.se.domain.DBdata.Login;
import hello.se.domain.respository.LoginRepository;
import hello.se.web.Form.LoginForm;
import hello.se.web.Form.LoginValidationForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@Slf4j
public class LoginController {
    private LoginRepository loginRepository;

    @Autowired
    public LoginController(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @PostMapping("/login/enter")
    public String enterLogin(@Validated @ModelAttribute("loginValidation") LoginValidationForm validationForm) {
        Login findUser = loginRepository.findFromDB(validationForm);
        Optional<Login> wrapperLogin = Optional.of(findUser);

        if (wrapperLogin.isEmpty()) {
            log.info("not exist");
            return "redirect:/login";
        } else {
            log.info("current user={}", findUser.getId(), findUser.getUsername());
            return "redirect:/";
        }
    }

    @PostMapping("/login/register")
    public String addInfo(@ModelAttribute LoginForm loginForm) {
        loginRepository.saveWeb(loginForm);
        return "redirect:/";
    }
}
