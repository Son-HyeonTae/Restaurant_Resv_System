package hello.se.domain.DBdata;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue
    @Column(name = "oid", nullable = false)
    private Integer oid;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;

    @OneToOne(mappedBy = "customer")
    private Login login;
}
