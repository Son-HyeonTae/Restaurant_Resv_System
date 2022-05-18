package hello.se.domain.respository;

import hello.se.domain.DBdata.Login;
import hello.se.domain.DBdata.ResTable;
import hello.se.domain.DBdata.Reservation;
import hello.se.web.Form.LoginValidationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class LoginRepository {
    @PersistenceContext
    EntityManager em;
    hello.se.web.Form.LoginForm loginForm;
    ReservationRepository reservationRepository;
    CustomerRepository customerRepository;
    private Login findCustomer;

    @Autowired
    public LoginRepository(hello.se.web.Form.LoginForm loginForm,
                           ReservationRepository reservationRepository,
                           CustomerRepository customerRepository) {
        this.loginForm = loginForm;
        this.reservationRepository = reservationRepository;
        this.customerRepository = customerRepository;
    }

    //관리자 등록
    public void init() {
        Login admin = new Login();
        admin.setId("test");
        admin.setPassword("1234");
        admin.setUsername("관리자");
        admin.setPhoneNumber("010-1111-1111");
        em.persist(admin);
    }

    //테스트용
    public Login save(String id, String password, String username, String phoneNumber) {
        loginForm.setId(id);
        loginForm.setPassword(password);
        loginForm.setUsername(username);
        loginForm.setPhoneNumber(phoneNumber);

        Login login = new Login();
        login.setId(loginForm.getId());
        login.setPassword(loginForm.getPassword());
        login.setUsername(loginForm.getUsername());
        login.setPhoneNumber(loginForm.getPhoneNumber());
        loginForm.clear();

        em.persist(login);
        return login;
    }

    //웹용
    public Login saveWeb(hello.se.web.Form.LoginForm loginForm) {
        Login login = new Login();
        login.setLogin(loginForm);
        em.persist(login);
        return login;
    }

    //DB에서 로그인 정보를 가져와서 검증
    public Login findFromDB(LoginValidationForm validationForm) {
        Login login = em.find(Login.class, validationForm.getId());
        System.out.println(login.getId());
        System.out.println(validationForm.getId());

        if (!login.equals(null)) {
            if (login.getPassword().equals(validationForm.getPassword())) {
                return login;
            }
        }
        return null;
    }

    //id로 단건 조회
    public Login findFromDB(String id) {
        return em.find(Login.class, id);
    }

    //해당 id로 모두 조회
    public List<Login> findAll(String id) {
        return em.createQuery("select l from Login l where l.id =: id", Login.class)
                .setParameter("id", id)
                .getResultList();
    }

    //Id로 DB에서 select한 쿼리들을 찾음
    public Optional<Login> findById(String id) {
        return findAll(id).stream().filter(l -> l.getId().equals(id))
                .findFirst();
    }

    //key로 해당 user 단건 조회
    public Login findByKey(Long key) {
        return em.find(Login.class, key);
    }




    /*//테이블의 번호를 바꿈
    public Login modifyTableNumber(String id, Reservation newReservation, ResTable resTable) {
        findCustomer = findFromDB(id);
        Reservation findRes = findCustomer.getReservation();

        if (timeValidation(findCustomer.getReservation(), newReservation)
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
        findCustomer = findFromDB(id);
        Reservation findRes = findCustomer.getReservation();

        if (timeValidation(findCustomer.getReservation(), newReservation)
                && isCovers(findCustomer.getReservation(), resTable)) {
            if (!findRes.getTable_id().equals(newReservation.getTable_id())) {
                findRes.setDate(newReservation.getDate());
            }
        }

        return findCustomer;
    }

    //예약된 테이블의 시간 변경
    public Login modifyTableTime(String id, Reservation newReservation, ResTable resTable) {
        findCustomer = findFromDB(id);
        Reservation findRes = findCustomer.getReservation();

        if (timeValidation(findCustomer.getReservation(), newReservation)
                && isCovers(findCustomer.getReservation(), resTable)) {
            if (!findRes.getTable_id().equals(newReservation.getTable_id())) {
                findRes.setTime(newReservation.getTime());
            }
        }
        return findCustomer;
    }*/

 /* 여기부터 일단 해보긴 했음*/
    // 테이블id값 받아와서 서로 비교 해서 인원 검증
    private boolean coversValidation(Reservation reservation, ResTable resTable) {

        if (reservation.getCovers() > resTable.getTable_id().getCovers()) {
            return false;
        }
        return true;
    }

    private boolean dateValidation(Reservation reservation)
    {
        LocalDate nowDate=LocalDate.now();
        if(reservation.getDate().isBefore(nowDate)) {
            return false;
        }
        return true;
    }

    private boolean timeValidation(Reservation reservation)
    {
        LocalDate nowDate=LocalDate.now();
        LocalTime nowTime=LocalTime.now();
        if(reservation.getDate().equals(nowDate)) {
            if (reservation.getTime().isBefore(nowTime)) {
                return false;
            }
        }
        return true;
    }

    private boolean monitor(Reservation reservation, ResTable resTable)
    {
        if(coversValidation(resrvation,restable)&&dateValidation(reservation)&&timeValidation(reservation))
        {
            return true;
        }
        return false;
    }

    /**
     * date, time 검증
     */
    private boolean timeValidation(Reservation reservation, Reservation target) {
        if (reservation.getDate().equals(target.getDate())) {
            if (reservation.getTime().isEqual(target.getTime())) {
                return false;
            }
        } else {
            return true;
        }
        return true;
    }
}
