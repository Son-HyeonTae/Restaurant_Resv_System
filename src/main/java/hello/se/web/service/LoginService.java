package hello.se.web.service;

import hello.se.domain.DBdata.Login;
import hello.se.domain.respository.LoginRepository;
import hello.se.web.Form.LoginValidationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final LoginRepository loginRepository;

    @Autowired
    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
        loginRepository.init();
    }

    public Login inspectLogin(LoginValidationForm loginValidationForm) {
        return loginRepository.findById(loginValidationForm.getId())
                .filter(l -> l.getPassword().equals(loginValidationForm.getPassword()))
                .orElse(null);
    }
}
