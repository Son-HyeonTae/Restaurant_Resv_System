package hello.se.domain.respository;

import hello.se.domain.DBdata.WalkIn;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class WalkInRepository {

    @PersistenceContext
    EntityManager em;

    public WalkIn save() {
        WalkIn walkIn = new WalkIn();
        walkIn.setOid(1);
        walkIn.setCovers(2);
        walkIn.setDate(LocalDate.of(2022, 05, 05));
        walkIn.setTime(LocalDateTime.of(2022, 05, 05, 13, 00));
        walkIn.setTable_id(1);

        em.persist(walkIn);
        return walkIn;
    }

    public WalkIn save(WalkIn walkIn) {
        /*WalkIn walkIn = new WalkIn();
        walkIn.setOid(1);
        walkIn.setCovers(2);
        walkIn.setDate(LocalDate.of(2022, 05, 05));
        walkIn.setTime(LocalDateTime.of(2022, 05, 05, 13, 00));
        walkIn.setTable_id(1);*/

        em.persist(walkIn);
        return walkIn;
    }

    public WalkIn findWalkIn(int oid) {
        return em.find(WalkIn.class, oid);
    }

    public List<WalkIn> findAll() {
        return em.createQuery("select w from WalkIn w", WalkIn.class)
                .getResultList();
    }

    public List<WalkIn> findForCovers(int covers) {
        return em.createQuery("select w from WalkIn w where w.covers = :covers", WalkIn.class)
                .setParameter("covers", covers)
                .getResultList();
    }
}
