package hello.se.domain.DBdata;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;


//예약정보
@Entity
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "res_oid")
    private Integer oid;

    @Column(name = "name")
    private String name;

    @Column(name = "covers")
    private Integer covers;

    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Column(name = "time")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime time;

    @Column(name = "table_id")
    private Integer table_id;

    /*@Column(name = "customer_id")
    private Integer customer_id;*/

    @Column(name = "email")
    private String email;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "endTime")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime endTime;

    @Column(name = "login_key")
    private Long loginKey;

    @Column(name = "coversError")
    private Boolean error;

   /* @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "resTable_oid")
    private ResTable resTable;*/

    public Reservation() {
    }

    public Reservation(Integer oid, Integer covers, LocalDate date, LocalTime time,
                       Integer table_id, Integer customer_id, LocalTime endTime) {
        this.oid = oid;
        this.covers = covers;
        this.date = date;
        this.time = time;
        this.table_id = table_id;
//        this.customer_id = customer_id;
        this.endTime = endTime;
    }

    /*public void setLogin(Login login) {
        this.login = login;
        login.getReservations().add(this);
    }*/
}
