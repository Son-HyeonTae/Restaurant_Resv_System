package hello.se.web.Form;

import org.springframework.stereotype.Component;

@Component
public class ModifyForm {
    private Integer modifyId;

    public Integer getModifyId() {
        return modifyId;
    }

    public void setModifyId(Integer modifyId) {
        this.modifyId = modifyId;
    }
}
