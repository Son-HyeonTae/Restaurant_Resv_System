package hello.se.web.Form;

import org.springframework.stereotype.Component;

@Component
public class CancelForm {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
