package hello.se.domain.DBdata;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;

@Data
@Component
public class LoginDTO {
    @NotEmpty(message = "id는 필수 입력입니다.")
    private String id;

    @NotEmpty(message = "password는 필수 입력입니다.")
    private String password;

    @NotEmpty(message = "이름을 입력해 주세요")
    private String name;

    @NotEmpty(message = "phone번호를 입력해주세요.")
    private String phoneNumber;

    public void clear() {
        name = null;
        phoneNumber = null;
        id = null;
        password = null;
    }
}
