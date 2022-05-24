package hello.se.web.controller;

import hello.se.domain.DBdata.Login;
import hello.se.domain.DBdata.Reservation;
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
public class AdminController {
    private ReservationRepository reservationRepository;

    @Autowired
    public AdminController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @GetMapping("/admin/{key}")
    public String adminView(Model model, HttpServletRequest request, @PathVariable Long key) {
        HttpSession session = request.getSession();
        Login admin = (Login) session.getAttribute("user");
        model.addAttribute("login", admin);
        List<Reservation> allList = reservationRepository.findAll();
        model.addAttribute("allList", allList);

        return "SW-Project-main/adminList";
    }
}
