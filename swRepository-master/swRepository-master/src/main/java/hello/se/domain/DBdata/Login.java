package hello.se.domain.DBdata;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Login")
public class Login {
    @Id
    @Column(name = "login_id", nullable = false)
    private String id;

    @Column(name = "password")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reservation_oid")
    private Reservation reservation;

    @Column(name = "username")
    private String username;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "login_id")
    private Customer customer;

}
