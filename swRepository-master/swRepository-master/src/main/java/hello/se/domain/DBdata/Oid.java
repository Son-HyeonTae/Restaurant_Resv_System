package hello.se.domain.DBdata;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Oid {
    @Id
    @Column(name = "last_id", nullable = false)
    private Integer last_id;
}
