package hello.se.RelationTest;

import hello.se.domain.DBdata.Customer;
import hello.se.domain.DBdata.Login;
import hello.se.domain.DBdata.ResTable;
import hello.se.domain.DBdata.Reservation;
import hello.se.domain.respository.CustomerRepository;
import hello.se.domain.respository.LoginRepository;
import hello.se.domain.respository.ResTableRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@Transactional
@Rollback()
public class LoginTest {
    @Autowired
    LoginRepository loginRepository;
    @Autowired
    ResTableRepository resTableRepository;
    @Autowired
    CustomerRepository customerRepository;

    Login user1;
    Login user2;
    Reservation reservation1;
    Reservation reservation2;

    private Customer init() {
        Customer c1 = new Customer();
        c1.setName("가나다");
        c1.setPhoneNumber("010-1111-1111");
        return c1;
    }

    @Test
    @Rollback(false)
    public void 로그인() throws Exception {
        user1 = loginRepository.save("test", "test!","가나다","010-1111-1111");
        user2 = loginRepository.save("apple", "apple!","김철수","010-2222-1111");
        Assertions.assertThat(user1.getId()).isEqualTo("test");
    }

    @Test
    public void 테이블_번호_바꾸기() throws Exception {
        user1 = loginRepository.save("test", "test!","가나다","010-1111-1111");
        user2 = loginRepository.save("apple", "apple!","김철수","010-2222-1111");

        Reservation reservation1 = new Reservation(1, 2,
                LocalDate.of(2022, 05, 30),
                LocalDateTime.of(2022, 05, 31, 12, 00),
                1, 1,
                LocalDateTime.of(2022, 05, 31, 13, 00));
        user1.setReservation(reservation1);

        Reservation reservation2 = new Reservation(2, 2,
                LocalDate.of(2022, 05, 31),
                LocalDateTime.of(2022, 05, 31, 11, 00),
                2, 2,
                LocalDateTime.of(2022, 05, 31, 13, 00));
        user2.setReservation(reservation2);

        ResTable savedResTable1 = resTableRepository.save(2, 2);
        user1.getReservation().setResTable(savedResTable1);

        ResTable savedResTable2 = resTableRepository.save(3, 2);
        user2.getReservation().setResTable(savedResTable2);

        Login update = loginRepository.modifyTableNumber(user1.getId(), reservation2, savedResTable2);
        Assertions.assertThat(update.getReservation().getResTable().getOid()).isEqualTo(2);
    }

    @Test
    public void date_변경() throws Exception {
        user1 = loginRepository.save("test", "test!","가나다","010-1111-1111");
        user2 = loginRepository.save("apple", "apple!","김철수","010-2222-1111");

        reservation1 = new Reservation(1, 2,
                LocalDate.of(2022, 05, 30),
                LocalDateTime.of(2022, 05, 31, 12, 00),
                1, 1,
                LocalDateTime.of(2022, 05, 31, 13, 00));
        user1.setReservation(reservation1);

        reservation2 = new Reservation(2, 2,
                LocalDate.of(2022, 05, 31),
                LocalDateTime.of(2022, 05, 31, 11, 00),
                2, 2,
                LocalDateTime.of(2022, 05, 31, 13, 00));
        user2.setReservation(reservation2);

        ResTable savedResTable1 = resTableRepository.save(2, 2);
        user1.getReservation().setResTable(savedResTable1);

        ResTable savedResTable2 = resTableRepository.save(3, 2);
        user2.getReservation().setResTable(savedResTable2);

        Login update = loginRepository.modifyTableDate(user1.getId(), reservation2, savedResTable2);
        Assertions.assertThat(update.getReservation().getDate()).isEqualTo(reservation2.getDate());
    }

    @Test
    public void time_변경() throws Exception {
        user1 = loginRepository.save("test", "test!","가나다","010-1111-1111");
        user2 = loginRepository.save("apple", "apple!","김철수","010-2222-1111");

        reservation1 = new Reservation(1, 2,
                LocalDate.of(2022, 05, 30),
                LocalDateTime.of(2022, 05, 31, 12, 00),
                1, 1,
                LocalDateTime.of(2022, 05, 31, 13, 00));
        user1.setReservation(reservation1);

        reservation2 = new Reservation(2, 2,
                LocalDate.of(2022, 05, 31),
                LocalDateTime.of(2022, 05, 31, 11, 00),
                2, 2,
                LocalDateTime.of(2022, 05, 31, 13, 00));
        user2.setReservation(reservation2);

        ResTable savedResTable1 = resTableRepository.save(2, 2);
        user1.getReservation().setResTable(savedResTable1);

        ResTable savedResTable2 = resTableRepository.save(3, 2);
        user2.getReservation().setResTable(savedResTable2);

        Login update = loginRepository.modifyTableTime(user1.getId(), reservation2, savedResTable2);
        Assertions.assertThat(update.getReservation().getTime()).isEqualTo(reservation2.getTime());

    }
}

