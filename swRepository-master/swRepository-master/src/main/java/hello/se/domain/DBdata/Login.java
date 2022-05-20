package hello.se.domain.DBdata;

import hello.se.web.Form.LoginForm;
import hello.se.web.Form.ReservationForm;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "Login")
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "login_key")
    private Long key;

    @Column(name = "login_id", nullable = false)
    private String id;

    @Column(name = "password")
    private String password;

    /*@OneToMany(mappedBy = "login")
    private List<Reservation> reservations = new ArrayList<>();*/

    @Column(name = "username")
    private String username;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "login_id")
    private Customer customer;*/

    public void setLogin(LoginForm loginForm) {
        this.id = loginForm.getId();
        this.password = loginForm.getPassword();
        this.username = loginForm.getUsername();
        this.phoneNumber = loginForm.getPhoneNumber();
    }

    public Login() {
    }
}
