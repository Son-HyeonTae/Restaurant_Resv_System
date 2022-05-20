package hello.se.web.controller;

import hello.se.domain.DBdata.Login;
import hello.se.domain.DBdata.Reservation;
import hello.se.domain.respository.LoginRepository;
import hello.se.domain.respository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class modifyController {
    private ReservationRepository reservationRepository;
    private LoginRepository loginRepository;

    @Autowired
    public modifyController(ReservationRepository reservationRepository, LoginRepository loginRepository) {
        this.reservationRepository = reservationRepository;
        this.loginRepository = loginRepository;
    }

    @GetMapping("/modify/{key}")
    public String modifyView(@PathVariable Long key, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Login currentUser = (Login) session.getAttribute("user");

        List<Reservation> BookList = reservationRepository.findAll(currentUser);
        model.addAttribute("bookList", BookList);
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("login", currentUser);
        return "SW-Project-main/bookList";
    }
}
