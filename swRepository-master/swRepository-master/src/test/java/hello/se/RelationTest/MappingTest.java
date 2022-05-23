package hello.se.RelationTest;

import hello.se.domain.DBdata.Login;
import hello.se.domain.DBdata.Reservation;
import hello.se.domain.respository.LoginRepository;
import hello.se.domain.respository.ReservationRepository;
import hello.se.web.Form.LoginForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
public class MappingTest {
    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    /*@Test
    public void 로그인_저장() throws Exception {
        //given
        LoginForm login1 = new LoginForm();
        login1.setId("1111");
        login1.setPassword("1111");
        login1.setUsername("가나다");
        login1.setPhoneNumber("1111");

        loginRepository.saveWeb(login1);
        //when
        Optional<Login> temp = loginRepository.findById(login1.getId());
        Login current = temp.get();

        System.out.println(current.getKey());
        //then
        save();

        Login byKey = loginRepository.findByKey(current.getKey());
        List<Reservation> reservationList = byKey.getReservationList();

        for (Reservation reservation : reservationList) {
            System.out.println(reservation.getTable_id());
        }

    }*/

    private void save() throws Exception {
        //given
        Reservation reservation1 = new Reservation();
        reservation1.setCovers(2);
        reservation1.setTable_id(1);
        //when
        reservationRepository.save(reservation1);

        Reservation reservation2 = new Reservation();
        reservation2.setCovers(2);
        reservation2.setTable_id(2);
        reservationRepository.save(reservation2);

        //then

    }
}
