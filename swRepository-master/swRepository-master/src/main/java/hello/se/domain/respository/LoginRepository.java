package hello.se.domain.respository;

import hello.se.domain.DBdata.Login;
import hello.se.domain.DBdata.LoginDTO;
import hello.se.domain.DBdata.ResTable;
import hello.se.domain.DBdata.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class LoginRepository {
    @PersistenceContext
    EntityManager em;
    LoginDTO loginDTO;
    ReservationRepository reservationRepository;
    CustomerRepository customerRepository;
    private Login findCustomer;

    @Autowired
    public LoginRepository(LoginDTO loginDTO,
                           ReservationRepository reservationRepository,
                           CustomerRepository customerRepository) {
        this.loginDTO = loginDTO;
        this.reservationRepository = reservationRepository;
        this.customerRepository = customerRepository;
    }

    public Login save(String id, String password,String username,String phoneNumber) {
        loginDTO.setId(id);
        loginDTO.setPassword(password);
        loginDTO.setName(username);
        loginDTO.setPhoneNumber(phoneNumber);

        Login login = new Login();
        login.setId(loginDTO.getId());
        login.setPassword(loginDTO.getPassword());
        login.setUsername(loginDTO.getName());
        login.setPhoneNumber(loginDTO.getPhoneNumber());
        loginDTO.clear();

        em.persist(login);
        return login;
    }

    public Login findForId(String id) {
        return em.find(Login.class, id);
    }

    //테이블의 번호를 바꿈
    public Login modifyTableNumber(String id, Reservation newReservation, ResTable resTable) {
        findCustomer = findForId(id);
        Reservation findRes = findCustomer.getReservation();

        if (timeValidation(findCustomer.getReservation(),newReservation)
                && isCovers(findCustomer.getReservation(), resTable)) {
            if (!findRes.getTable_id().equals(newReservation.getTable_id())) {
                findCustomer.getReservation().setResTable(resTable);
//                findRes.setTable_id(newReservation.getTable_id());
            }
        }

        return findCustomer;
    }

    //예약된 테이블의 일자 변경
    public Login modifyTableDate(String id, Reservation newReservation, ResTable resTable) {
        findCustomer = findForId(id);
        Reservation findRes = findCustomer.getReservation();

        if (timeValidation(findCustomer.getReservation(),newReservation)
                && isCovers(findCustomer.getReservation(), resTable)) {
            if (!findRes.getTable_id().equals(newReservation.getTable_id())) {
                findRes.setDate(newReservation.getDate());
            }
        }

        return findCustomer;
    }

    //예약된 테이블의 시간 변경
    public Login modifyTableTime(String id, Reservation newReservation, ResTable resTable) {
        findCustomer = findForId(id);
        Reservation findRes = findCustomer.getReservation();

        if (timeValidation(findCustomer.getReservation(), newReservation)
                && isCovers(findCustomer.getReservation(), resTable)) {
            if (!findRes.getTable_id().equals(newReservation.getTable_id())) {
                findRes.setTime(newReservation.getTime());
            }
        }
        return findCustomer;
    }


    //covers 검증
    private boolean isCovers(Reservation reservation, ResTable resTable) {
        if (reservation.getCovers() > resTable.getPlaces()) {
            return false;
        }
        return true;
    }

    /**
     * date, time 검증
     */
    private boolean timeValidation(Reservation reservation, Reservation target) {
        if (reservation.getDate().isEqual(target.getDate())) {
            if (reservation.getTime().isEqual(target.getTime())) {
                return false;
            }
        } else {
            return true;
        }
        return true;
    }
}
