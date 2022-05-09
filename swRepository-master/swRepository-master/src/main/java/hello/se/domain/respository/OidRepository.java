package hello.se.domain.respository;

import hello.se.domain.DBdata.Oid;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class OidRepository {
    @PersistenceContext
    EntityManager em;

    public Oid save() {
        Oid oid = new Oid();
        oid.setLast_id(1);

        em.persist(oid);
        return oid;
    }

    public Oid findOid(int last_id) {
        return em.find(Oid.class, last_id);
    }
}
