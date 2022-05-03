package hello.se.domain.respository;

import hello.se.domain.DBdata.Reservation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ReservationRepository {
    @PersistenceContext
    EntityManager em;

    public Reservation save(Reservation reservation) {
        if (reservation.getOid() == null) {
            em.persist(reservation);
        } else {
            em.merge(reservation);
        }
//        em.persist(reservation);
        return reservation;
    }

    public Reservation findReservation(int oid) {
        return em.find(Reservation.class, oid);
    }

    public List<Reservation> findAll() {
        return em.createQuery("select r from Reservation r", Reservation.class)
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

    public List<Reservation> findForArriveTime(LocalDateTime arrival) {
        return em.createQuery("select r from Reservation r where r.arrivalTime = :arrival", Reservation.class)
                .setParameter("arrival", arrival)
                .getResultList();
    }

    public List<Reservation> findForCustomerId(int customerId) {
        return em.createQuery("select r from Reservation r where r.customer_id = :customerId", Reservation.class)
                .setParameter("customerId", customerId)
                .getResultList();
    }

    public List<Reservation> findForTableId(int tableId) {
        return em.createQuery("select r from Reservation r where r.table_id = :tableId", Reservation.class)
                .setParameter("tableId", tableId)
                .getResultList();
    }


}
