package hello.se.web.Form;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;

@Data
@Component
public class LoginValidationForm {
    @NotEmpty(message = "id는 필수 입력입니다.")
    private String id;

    @NotEmpty(message = "password는 필수 입력입니다.")
    private String password;

}
