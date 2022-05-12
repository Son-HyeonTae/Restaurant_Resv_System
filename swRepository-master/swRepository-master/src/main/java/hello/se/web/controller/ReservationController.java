package hello.se.web.controller;

import hello.se.domain.DBdata.Login;
import hello.se.domain.DBdata.Reservation;
import hello.se.domain.respository.LoginRepository;
import hello.se.domain.respository.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class ReservationController {
    private ReservationRepository reservationRepository;
    private LoginRepository loginRepository;

    @Autowired
    public ReservationController(ReservationRepository reservationRepository, LoginRepository loginRepository) {
        this.reservationRepository = reservationRepository;
        this.loginRepository = loginRepository;
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
    public String addLoginReservation(@PathVariable Long key, @ModelAttribute Reservation reservation, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Login currentRes = (Login) session.getAttribute("user");
        currentRes.setReservation(reservation);
        Reservation reservation1 = currentRes.getReservation();

        log.info("login reservation", reservation1.getCovers());
        return "redirect:/";
    }
}
