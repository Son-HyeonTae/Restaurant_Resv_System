package hello.se.RelationTest;

import hello.se.domain.DBdata.ResTable;
import hello.se.domain.respository.ResTableRepository;
import hello.se.domain.respository.ReservationRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
@Rollback(true)
public class ReservationTest {
    @Autowired
    ResTableRepository tableRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ReservationRepository reservationRepository;

    @Test
    public void 테이블_저장() throws Exception {
        //given
        ResTable saveTable = tableRepository.save(2,2);
        ResTable findTable = tableRepository.findTable(1);
        //when

        //then
        Assertions.assertThat(findTable.getNumber()).isEqualTo(saveTable.getNumber());
        Assertions.assertThat(findTable.getCovers()).isEqualTo(saveTable.getCovers());

    }

    @Test
    public void 고객_조회() throws Exception {
        //given
        Customer c1 = new Customer();
        c1.setName("가나다");
        c1.setPhoneNumber("010-1111-1111");
        Customer c2 = new Customer();
        c2.setName("가나다");
        c2.setPhoneNumber("010-2222-1111");
        Customer c3 = new Customer();
        c3.setName("김철수");
        c3.setPhoneNumber("010-3333-1111");
        Customer c4 = new Customer();
        c4.setName("김철수");
        c4.setPhoneNumber("010-3333-1111");
        Customer save1 = customerRepository.save(c1);
        Customer save2 = customerRepository.save(c2);
        Customer save3 = customerRepository.save(c3);
        Customer save4 = customerRepository.save(c4);


        /*Customer c1 = customerRepository.save("가나다", "010-1111-1111");
        Customer c2 = customerRepository.save("가나다", "010-2222-1111");
        Customer c3 = customerRepository.save("김철수", "010-3333-1111");
        Customer c4 = customerRepository.save("김철수", "010-3333-1111");*/

        List<Customer> customerList = customerRepository.findForName(save1.getName());
        List<Customer> all = customerRepository.findAll();

        //then
        System.out.println("<전부 출력>");
        for (Customer customer : all) {
            System.out.println("customer name: " + customer.getName() + " ,phoneNumber: " + customer.getPhoneNumber());
        }

        System.out.println("\n<고객 이름으로 조회>");
        for (Customer customer : customerList) {
            System.out.println("customer name: " + customer.getName() + " ,phoneNumber: " + customer.getPhoneNumber());
        }
    }

    /*@Test
    public void 예약_조회() throws Exception {
        //given
        Reservation r1 = new Reservation(1, 2,
                LocalDate.now(), LocalTime.now(), 1, 1
                , LocalTime.now());
        Reservation r2 = new Reservation(2, 3,
                LocalDate.now(), LocalTime.now(), 2, 2
                ,LocalTime.now());
        Reservation r3 = new Reservation(3, 3,
                LocalDate.now(), LocalTime.now(), 3, 3
                , LocalTime.now());
        //when
        reservationRepository.save(r1);
        reservationRepository.save(r2);
        reservationRepository.save(r3);
        //then

        List<Reservation> forArriveTime = reservationRepository.findForArriveTime(r1.getEndTime());
        for (Reservation reservation : forArriveTime) {
            System.out.println(reservation);
        }
    }*/
}
