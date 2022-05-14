package hello.se.web.Form;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;

@Data
@Component
public class LoginForm {
    @NotEmpty(message = "id는 필수 입력입니다.")
    private String id;

    @NotEmpty(message = "password는 필수 입력입니다.")
    private String password;

    @NotEmpty(message = "이름을 입력해 주세요")
    private String username;

    @NotEmpty(message = "phone번호를 입력해주세요.")
    private String phoneNumber;

    public void clear() {
        username = null;
        phoneNumber = null;
        id = null;
        password = null;
    }
}
