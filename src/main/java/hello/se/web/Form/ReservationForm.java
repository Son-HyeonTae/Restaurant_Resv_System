package hello.se.web.Form;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Component
public class ReservationForm {
    private String name;

    private Integer covers;

    private Date date;

    private LocalDateTime time;

    private Integer table_id;

    private String email;

    private String phoneNumber;

    private LocalDateTime arrivalTime;
}
