package hello.se.web.bridge;

import hello.se.domain.DBdata.Login;
import hello.se.domain.respository.ResTableRepository;
import hello.se.domain.respository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class ModelBinding {
    private ReservationRepository reservationRepository;
    private ResTableRepository resTableRepository;

    @Autowired
    public ModelBinding(ReservationRepository reservationRepository,ResTableRepository resTableRepository) {
        this.reservationRepository = reservationRepository;
        this.resTableRepository = resTableRepository;
    }

    public void expected(Model model) {
        temp(model, reservationRepository);
    }

    public static void temp(Model model, ReservationRepository reservationRepository) {
        model.addAttribute("arr1", reservationRepository.findForTableId(1));
        model.addAttribute("arr2", reservationRepository.findForTableId(2));
        model.addAttribute("arr3", reservationRepository.findForTableId(3));
        model.addAttribute("arr4", reservationRepository.findForTableId(4));
        model.addAttribute("arr5", reservationRepository.findForTableId(5));
        model.addAttribute("arr6", reservationRepository.findForTableId(6));
        model.addAttribute("arr7", reservationRepository.findForTableId(7));
        model.addAttribute("arr8", reservationRepository.findForTableId(8));
        model.addAttribute("arr9", reservationRepository.findForTableId(9));
    }

    public void modelToReservationAndTable(Model model, Login currentUser) {
        getTable1(model, 1);
        getTable2(model, 2);
        getTable3(model, 3);
        getTable4(model, 4);
        getTable5(model, 5);
        getTable6(model, 6);
        getTable7(model, 7);
        getTable8(model, 8);
        getTable9(model, 9);
    }

    private Model getTable1(Model model, int id) {
        return model.addAttribute("tableNum1", resTableRepository.findTable(id));
    }

    private Model getTable2(Model model, int id) {
        return model.addAttribute("tableNum2", resTableRepository.findTable(id));
    }

    private Model getTable3(Model model, int id) {
        return model.addAttribute("tableNum3", resTableRepository.findTable(id));
    }

    private Model getTable4(Model model, int id) {
        return model.addAttribute("tableNum4", resTableRepository.findTable(id));
    }

    private Model getTable5(Model model, int id) {
        return model.addAttribute("tableNum5", resTableRepository.findTable(id));
    }

    private Model getTable6(Model model, int id) {
        return model.addAttribute("tableNum6", resTableRepository.findTable(id));
    }

    private Model getTable7(Model model, int id) {
        return model.addAttribute("tableNum7", resTableRepository.findTable(id));
    }

    private Model getTable8(Model model, int id) {
        return model.addAttribute("tableNum8", resTableRepository.findTable(id));
    }

    private Model getTable9(Model model, int id) {
        return model.addAttribute("tableNum9", resTableRepository.findTable(id));
    }
}
