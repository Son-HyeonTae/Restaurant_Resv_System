package hello.se.domain.DBdata;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class WalkIn {
    @Id
    @Column(name = "oid", nullable = false)
    private Integer oid;

    @Column(name = "covers")
    private Integer covers;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "time")
    private LocalDateTime time;

    @Column(name = "table_id")
    private Integer table_id;
}
