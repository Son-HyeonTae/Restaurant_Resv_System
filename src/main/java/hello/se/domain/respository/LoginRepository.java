package hello.se.domain.respository;

import hello.se.domain.DBdata.Login;
import hello.se.domain.DBdata.ResTable;
import hello.se.domain.DBdata.Reservation;
import hello.se.web.Form.LoginForm;
import hello.se.web.Form.LoginValidationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class LoginRepository {
    @PersistenceContext
    EntityManager em;
    LoginForm loginForm;
    ReservationRepository reservationRepository;
    private Login findCustomer;

    @Autowired
    public LoginRepository(hello.se.web.Form.LoginForm loginForm,
                           ReservationRepository reservationRepository) {
        this.loginForm = loginForm;
        this.reservationRepository = reservationRepository;
    }

    //관리자/user1등록
    public void init() {
        Login admin = new Login();
        admin.setId("test");
        admin.setPassword("1234");
        admin.setUsername("admin");
        admin.setPhoneNumber("010-1111-1111");
        em.persist(admin);

        Login user1 = new Login();
        user1.setId("aaaa");
        user1.setPassword("1111");
        user1.setUsername("user1");
        user1.setPhoneNumber("010-2222-2222");
        em.persist(user1);

        Login user2 = new Login();
        user2.setId("qqqq");
        user2.setPassword("2222");
        user2.setUsername("user2");
        user2.setPhoneNumber("010-3333-3333");
        em.persist(user2);
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
    public Login saveWeb(LoginForm loginForm) {
        Login login = new Login();
        login.setLogin(loginForm);
        em.persist(login);
        return login;
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
}
