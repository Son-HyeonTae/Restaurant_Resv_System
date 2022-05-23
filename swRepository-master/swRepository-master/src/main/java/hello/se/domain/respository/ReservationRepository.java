package hello.se.domain.respository;

import hello.se.domain.DBdata.Login;
import hello.se.domain.DBdata.ResTable;
import hello.se.domain.DBdata.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
@Transactional
public class ReservationRepository {
    @PersistenceContext
    EntityManager em;

    public Reservation save(Reservation reservation) {
        if (reservation.getOid() == null) {
            em.persist(reservation);
        } else {
            em.merge(reservation);
        }
        return reservation;
    }

    /*public void init() {
        Reservation reservation1 = new Reservation();
        reservation1.setName("user1");
        reservation1.setPhoneNumber("010-2222-2222");
        reservation1.setTime(LocalTime.of(14, 00));
        reservation1.setEndTime(reservation1.getTime().plusHours(2));
        reservation1.setCovers(2);
        reservation1.setEmail("a@a.com");
        reservation1.setDate(LocalDate.now());
        reservation1.setError(true);
        reservation1.setLoginKey(2L);
        reservation1.setTable_id(1);
        em.persist(reservation1);
    }*/

    /*public Reservation bothSaveLogin(Reservation reservation, Login login) {
        if (reservation.getOid() == null) {
            reservation.setLogin(login);
            em.persist(reservation);
        } else {
            em.merge(reservation);
        }
//        em.persist(reservation);
        return reservation;
    }*/

    public Reservation findReservation(int oid) {
        return em.find(Reservation.class, oid);
    }

    public List<Reservation> findAll(Login login) {
        return em.createQuery("select r from Reservation r inner join Login l on r.loginKey =: key", Reservation.class)
                .setParameter("key", login.getKey())
                .getResultList();
    }

    public List<Reservation> findForOid(int oid) {
        return em.createQuery("select r from Reservation r where r.oid = :oid", Reservation.class)
                .setParameter("oid", oid)
                .getResultList();
    }

    public List<Reservation> findForTime(LocalDateTime time) {
        return em.createQuery("select r from Reservation r where r.time = :time", Reservation.class)
                .setParameter("time", time)
                .getResultList();
    }

    public List<Reservation> findForDate(LocalDateTime date) {
        return em.createQuery("select r from Reservation r where r.date = :date", Reservation.class)
                .setParameter("date", date)
                .getResultList();
    }

    public List<Reservation> findForEndTime(LocalTime endTime) {
        return em.createQuery("select r from Reservation r where r.endTime = :endTime", Reservation.class)
                .setParameter("endTime", endTime)
                .getResultList();
    }

    public List<Reservation> findForTableId(int tableId) {
        return em.createQuery("select r from Reservation r where r.table_id = :tableId", Reservation.class)
                .setParameter("tableId", tableId)
                .getResultList();
    }

    public void remove(Reservation reservation) {
        em.remove(reservation);
    }
}
