package hello.se.web.controller;

import hello.se.domain.DBdata.Login;
import hello.se.domain.DBdata.ResTable;
import hello.se.domain.DBdata.Reservation;
import hello.se.domain.respository.LoginRepository;
import hello.se.domain.respository.ResTableRepository;
import hello.se.domain.respository.ReservationRepository;
import hello.se.web.bridge.ModelBinding;
import hello.se.web.service.ResTableService;
import hello.se.web.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalTime;
import java.util.List;

@Controller
@Slf4j
public class ReservationController {
    private ReservationRepository reservationRepository;
    private LoginRepository loginRepository;
    private ResTableRepository resTableRepository;
    private ResTableService resTableService;
    private Boolean isCovers = true;
    private ReservationService reservationService;
    private ModelBinding modelBinding;

    @Autowired
    public ReservationController(ReservationRepository reservationRepository, LoginRepository loginRepository,
                                 ResTableService resTableService, ResTableRepository resTableRepository,
                                 ReservationService reservationService, ModelBinding modelBinding) {
        this.modelBinding = modelBinding;
        this.reservationRepository = reservationRepository;
        this.loginRepository = loginRepository;
        this.resTableService = resTableService;
        this.resTableRepository = resTableRepository;
        this.reservationService = reservationService;
    }

    @PostMapping("/book")
    public String addReservation(@ModelAttribute Reservation reservation) {

        log.info("reservation phone={}", reservation.getPhoneNumber());
        log.info("reservation date={}", reservation.getDate());
        log.info("reservation name={}", reservation.getName());
        log.info("reservation covers={}", reservation.getCovers());
        log.info("reservation email={}", reservation.getEmail());

        reservationRepository.save(reservation);
        return "redirect:/";
    }

    @PostMapping("/book/{key}")
    public String addLoginReservation(@PathVariable Long key, @ModelAttribute Reservation reservation,
                                      HttpServletRequest request, RedirectAttributes redirectAttributes, Model model) {
        HttpSession session = request.getSession();
        Login currentUser = (Login) session.getAttribute("user");

        reservation.setLoginKey(currentUser.getKey());
        isCovers = resTableService.addIsCovers(reservation);
        reservation.setError(isCovers);
        reservation.setEndTime(reservation.getTime().plusHours(2));
        reservationRepository.save(reservation);
        resTableService.remove(isCovers, reservation);

        boolean isCurrentDate = resTableService.dateValidation(reservation);
        System.out.println("isCurrentDate : "+isCurrentDate);
        resTableService.remove(isCurrentDate, reservation);

        boolean isDuplicate = resTableService.timeDuplication(reservation, reservation.getTable_id());
        System.out.println("isDuplicate : " + isDuplicate);
        resTableService.remove(isDuplicate, reservation);

        if (!isCovers) {
            model.addAttribute("reservation", new Reservation());
            model.addAttribute("login", currentUser);
//            modelToReservationAndTable(model, currentUser);
            modelBinding.modelToReservationAndTable(model,currentUser);
            model.addAttribute("coversError", false);
            model.addAttribute("errorTable", resTableRepository.findTable(reservation.getTable_id()));
            model.addAttribute("isCurrentDate", true);
            model.addAttribute("isDuplicate", true);
            extracted(model);
            return "SW-Project-main/loginBook";
        }

        if (!isCurrentDate) {
            model.addAttribute("reservation", new Reservation());
            model.addAttribute("login", currentUser);
//            modelToReservationAndTable(model, currentUser);
            modelBinding.modelToReservationAndTable(model,currentUser);
            model.addAttribute("coversError", true);
            model.addAttribute("errorTable", resTableRepository.findTable(reservation.getTable_id()));
            model.addAttribute("isCurrentDate", false);
            model.addAttribute("isDuplicate", true);
            extracted(model);
            return "SW-Project-main/loginBook";
        }

        if (!isDuplicate) {
            model.addAttribute("reservation", new Reservation());
            model.addAttribute("login", currentUser);
//            modelToReservationAndTable(model, currentUser);
            modelBinding.modelToReservationAndTable(model,currentUser);
            model.addAttribute("coversError", true);
            model.addAttribute("errorTable", resTableRepository.findTable(reservation.getTable_id()));
            model.addAttribute("isCurrentDate", true);
            model.addAttribute("isDuplicate", false);
            model.addAttribute("duplicateTime", reservation.getTime());
            extracted(model);
            return "SW-Project-main/loginBook";
        }

        redirectAttributes.addAttribute("key", currentUser.getKey());
        return "redirect:/book/{key}";
    }

    private void extracted(Model model) {
        ModelBinding.temp(model, reservationRepository);
    }

   /* private void modelToReservationAndTable(Model model, Login currentUser) {
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
