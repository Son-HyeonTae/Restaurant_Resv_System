package hello.se.web.controller;

import hello.se.domain.DBdata.Reservation;
import hello.se.domain.respository.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ReservationController {

    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
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


}
