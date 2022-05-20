package hello.se.domain.respository;

import hello.se.domain.DBdata.ResTable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class ResTableRepository {
    @PersistenceContext
    EntityManager em;

    public ResTable save(int number, int places) {
        ResTable savedTable = new ResTable();
        savedTable.setNumber(number);
        savedTable.setCovers(places);

        em.persist(savedTable);
        return savedTable;
    }

    public void init() {
        ResTable table1 = new ResTable();
        table1.setNumber(1);
        table1.setCovers(2);
        em.persist(table1);

        ResTable table2 = new ResTable();
        table2.setNumber(2);
        table2.setCovers(2);
        em.persist(table2);

        ResTable table3 = new ResTable();
        table3.setNumber(3);
        table3.setCovers(2);
        em.persist(table3);

        ResTable table4 = new ResTable();
        table4.setNumber(4);
        table4.setCovers(3);
        em.persist(table4);

        ResTable table5 = new ResTable();
        table5.setNumber(5);
        table5.setCovers(3);
        em.persist(table5);

        ResTable table6 = new ResTable();
        table6.setNumber(6);
        table6.setCovers(3);
        em.persist(table6);

        ResTable table7 = new ResTable();
        table7.setNumber(7);
        table7.setCovers(4);
        em.persist(table7);

        ResTable table8 = new ResTable();
        table8.setNumber(8);
        table8.setCovers(4);
        em.persist(table8);

        ResTable table9 = new ResTable();
        table9.setNumber(9);
        table9.setCovers(4);
        em.persist(table9);
    }


    public ResTable findTable(int id) {
        return em.find(ResTable.class, id);
    }

    public List<ResTable> findAll() {
        return em.createQuery("select r from ResTable r", ResTable.class)
                .getResultList();
    }
}
