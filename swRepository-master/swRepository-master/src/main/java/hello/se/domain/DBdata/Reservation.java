package hello.se.domain.DBdata;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
//예약정보
public class Reservation {
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

    @Column(name = "customer_id")
    private Integer customer_id;

    /*@Column(name = "booking_name")
    private String name;

    @Column(name = "booking_phoneNumber")
    private String phoneNumber;*/

    @Column(name = "arrivalTime")
    private LocalDateTime arrivalTime;

    @OneToOne(mappedBy = "reservation")
    private Login login;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "resTable_oid")
    private ResTable resTable;

    public Reservation() {
    }

    public Reservation(Integer oid, Integer covers, LocalDate date, LocalDateTime time,
                       Integer table_id, Integer customer_id, LocalDateTime arrivalTime) {
        this.oid = oid;
        this.covers = covers;
        this.date = date;
        this.time = time;
        this.table_id = table_id;
        this.customer_id = customer_id;
        this.arrivalTime = arrivalTime;
    }
}
