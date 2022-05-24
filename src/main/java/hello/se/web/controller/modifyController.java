package hello.se.web.controller;

import hello.se.domain.DBdata.Login;
import hello.se.domain.DBdata.Reservation;
import hello.se.domain.respository.LoginRepository;
import hello.se.domain.respository.ResTableRepository;
import hello.se.domain.respository.ReservationRepository;
import hello.se.web.Form.CancelForm;
import hello.se.web.Form.ModifyForm;
import hello.se.web.bridge.ModelBinding;
import hello.se.web.service.ResTableService;
import hello.se.web.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.List;

@Controller
public class modifyController {
    private ReservationRepository reservationRepository;
    private LoginRepository loginRepository;
    private Boolean isCovers = true;
    private ResTableRepository resTableRepository;
    private ResTableService resTableService;
    private ModelBinding modelBinding;

    @Autowired
    public modifyController(ReservationRepository reservationRepository, LoginRepository loginRepository,
                            ResTableService resTableService, ResTableRepository resTableRepository,
                            ModelBinding modelBinding) {
        this.modelBinding = modelBinding;
        this.resTableRepository = resTableRepository;
        this.reservationRepository = reservationRepository;
        this.loginRepository = loginRepository;
        this.resTableService = resTableService;
    }

    @GetMapping("/modify/{key}")
    public String modifyView(@PathVariable Long key, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Login currentUser = (Login) session.getAttribute("user");

        List<Reservation> BookList = reservationRepository.findForLoginKey(currentUser.getKey());
        model.addAttribute("list", BookList);

        model.addAttribute("reservation", new Reservation());
        model.addAttribute("modifyForm", new ModifyForm());
        model.addAttribute("cancel", new CancelForm());
        model.addAttribute("login", currentUser);
//        modelToReservationAndTable(model, currentUser);
        modelBinding.modelToReservationAndTable(model,currentUser);
        model.addAttribute("coversError", true);
        model.addAttribute("isCurrentDate", true);
        model.addAttribute("isCurrentTime", true);
        model.addAttribute("isDuplicate", true);
        model.addAttribute("duplicateTime", LocalTime.now());

        ModelBinding.temp(model, reservationRepository);

        return "SW-Project-main/bookList";
    }

    @PostMapping("/modify/{key}")
    public String modifyInfo(@PathVariable Long key, @ModelAttribute Reservation reservation,
                             @ModelAttribute ModifyForm modifyForm, HttpServletRequest request,
                             RedirectAttributes redirectAttributes, Model model, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Login currentUser = (Login) session.getAttribute("user");
        reservation.setLoginKey(currentUser.getKey());

        //수정할 번호를 받아서 DB에서 예약1개를 가져옴
        Reservation modifiedBook = reservationRepository.findReservation(modifyForm.getModifyId());
        System.out.println("modifiedBook => " + modifiedBook.getName());

        isCovers = resTableService.addIsCovers(reservation);
        reservation.setError(isCovers);
        reservation.setEndTime(reservation.getTime().plusHours(2));
        reservationRepository.save(reservation);
        resTableService.remove(isCovers, reservation);

        boolean isCurrentDate = resTableService.dateValidation(reservation);
        System.out.println("isCurrentDate : " + isCurrentDate);
        resTableService.remove(isCurrentDate, reservation);

        boolean isDuplicate = resTableService.timeDuplication(reservation, reservation.getTable_id());
        System.out.println("isDuplicate : " + isDuplicate);
        resTableService.remove(isDuplicate, reservation);

        if (!isCovers) {
            model.addAttribute("reservation", new Reservation());
            model.addAttribute("login", currentUser);
//            modelToReservationAndTable(model, currentUser);
            modelBinding.modelToReservationAndTable(model,currentUser);
            model.addAttribute("cancel", new CancelForm());
            model.addAttribute("coversError", false);
            model.addAttribute("errorTable", resTableRepository.findTable(reservation.getTable_id()));
            model.addAttribute("isCurrentDate", true);
            model.addAttribute("isDuplicate", true);
            extracted(model);
            return "SW-Project-main/bookList";
        }

        if (!isCurrentDate) {
            model.addAttribute("reservation", new Reservation());
            model.addAttribute("login", currentUser);
//            modelToReservationAndTable(model, currentUser);
            modelBinding.modelToReservationAndTable(model,currentUser);
            model.addAttribute("cancel", new CancelForm());
            model.addAttribute("coversError", true);
            model.addAttribute("errorTable", resTableRepository.findTable(reservation.getTable_id()));
            model.addAttribute("isCurrentDate", false);
            model.addAttribute("isDuplicate", true);
            extracted(model);
            return "SW-Project-main/bookList";
        }

        if (!isDuplicate) {
            model.addAttribute("reservation", new Reservation());
            model.addAttribute("login", currentUser);
            model.addAttribute("cancel", new CancelForm());
//            modelToReservationAndTable(model, currentUser);
            modelBinding.modelToReservationAndTable(model,currentUser);
            model.addAttribute("coversError", true);
            model.addAttribute("errorTable", resTableRepository.findTable(reservation.getTable_id()));
            model.addAttribute("isCurrentDate", true);
            model.addAttribute("isDuplicate", false);
            model.addAttribute("duplicateTime", reservation.getTime());
            extracted(model);
            return "SW-Project-main/bookList";
        }

        modifiedBook.setCovers(reservation.getCovers());
        modifiedBook.setTable_id(reservation.getTable_id());
        modifiedBook.setTime(reservation.getTime());
        modifiedBook.setEndTime(reservation.getEndTime());
        modifiedBook.setDate(reservation.getDate());

        reservationRepository.remove(reservation);
        reservationRepository.save(modifiedBook);

        redirectAttributes.addAttribute("key", currentUser.getKey());
        return "redirect:/modify/{key}";
    }

    private void extracted(Model model) {
        ModelBinding.temp(model, reservationRepository);
    }

    /*private void modelToReservationAndTable(Model model, Login currentUser) {
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
    }*/
}
