package hello.se.web.controller;

import hello.se.domain.DBdata.Login;
import hello.se.domain.DBdata.Reservation;
import hello.se.domain.respository.LoginRepository;
import hello.se.domain.respository.ResTableRepository;
import hello.se.domain.respository.ReservationRepository;
import hello.se.web.Form.CancelForm;
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
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class CancelController {
    private ReservationRepository reservationRepository;
    private ReservationService reservationService;

    @Autowired
    public CancelController(ReservationRepository reservationRepository,ReservationService reservationService) {
        this.reservationRepository = reservationRepository;
        this.reservationService = reservationService;
    }

    @GetMapping("/cancel/{key}")
    public String cancelView(@PathVariable Long key, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Login currentUser = (Login) session.getAttribute("user");

        List<Reservation> BookList = reservationRepository.findForLoginKey(currentUser.getKey());
        model.addAttribute("list", BookList);
        model.addAttribute("login", currentUser);
        model.addAttribute("cancelForm", new CancelForm());

        return "SW-Project-main/cancelBook";
    }

    @PostMapping("/cancel/{key}")
    public String cancelAction(@PathVariable Long key, @ModelAttribute CancelForm cancelForm, HttpServletRequest request,
                               RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession();
        Login currentUser = (Login) session.getAttribute("user");

        Optional<Reservation> temp = reservationService.findOneForCancel(cancelForm);
        Reservation cancelBook = temp.get();

        reservationRepository.remove(cancelBook);
        redirectAttributes.addAttribute("key", currentUser.getKey());
        return "redirect:/book/{key}";
    }
}
