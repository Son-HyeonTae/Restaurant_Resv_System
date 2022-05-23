package hello.se.web.controller;

import hello.se.domain.DBdata.Login;
import hello.se.domain.DBdata.ResTable;
import hello.se.domain.DBdata.Reservation;
import hello.se.domain.respository.ResTableRepository;
import hello.se.domain.respository.ReservationRepository;
import hello.se.web.Form.LoginValidationForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalTime;
import java.util.List;

@Controller
@Slf4j
public class OnlyViewController {
    private ResTableRepository resTableRepository;
    private ReservationRepository reservationRepository;

    @Autowired
    public OnlyViewController(ResTableRepository resTableRepository, ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
        this.resTableRepository = resTableRepository;
        resTableRepository.init();
//        reservationRepository.init();
    }

    @GetMapping()
    public String homeView(Model model) {
        model.addAttribute("login", new Login());
        log.info("first page");
        return "SW-Project-main/index";
    }

    @GetMapping("/menu")
    public String menuView() {
        log.info("menu page");
        return "SW-Project-main/menu";
    }

    @GetMapping("/book")
    public String bookView(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Login currentUser = (Login) session.getAttribute("user");
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("login", currentUser);
        log.info("book page");
        return "SW-Project-main/book";
    }

    @GetMapping("/about")
    public String aboutView() {
        log.info("about page");
        return "SW-Project-main/about";
    }

    @GetMapping("/login")
    public String loginView(Model model) {
        model.addAttribute("loginValidationForm", new LoginValidationForm());
        model.addAttribute("loginForm", new hello.se.web.Form.LoginForm());
//        model.addAttribute("login", new Login());
        log.info("login page");
        return "SW-Project-main/login_signup";
    }

    @GetMapping("/index/{key}")
    public String loginIndexView(@PathVariable Long key, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Login currentUser = (Login) session.getAttribute("user");
        model.addAttribute("login", currentUser);
        return "SW-Project-main/loginIndex";
    }

    @GetMapping("/menu/{key}")
    public String loginMenuView(@PathVariable Long key, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Login currentUser = (Login) session.getAttribute("user");
        model.addAttribute("login", currentUser);
        return "SW-Project-main/loginMenu";
    }

    @GetMapping("/about/{key}")
    public String loginAboutView(@PathVariable Long key, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Login currentUser = (Login) session.getAttribute("user");
        model.addAttribute("login", currentUser);
        return "SW-Project-main/loginAbout";
    }

    @GetMapping("/book/{key}")
    public String addLoginReservation(@PathVariable Long key, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Login currentUser = (Login) session.getAttribute("user");

        model.addAttribute("reservation", new Reservation());
        model.addAttribute("login", currentUser);
        modelToReservationAndTable(model, currentUser);
        model.addAttribute("coversError", true);
        model.addAttribute("isCurrentDate", true);
        model.addAttribute("isCurrentTime", true);
        model.addAttribute("isDuplicate", true);
        model.addAttribute("duplicateTime", LocalTime.now());

        model.addAttribute("arr1", reservationRepository.findForTableId(1));
        model.addAttribute("arr2", reservationRepository.findForTableId(2));
        model.addAttribute("arr3", reservationRepository.findForTableId(3));
        model.addAttribute("arr4", reservationRepository.findForTableId(4));
        model.addAttribute("arr5", reservationRepository.findForTableId(5));
        model.addAttribute("arr6", reservationRepository.findForTableId(6));
        model.addAttribute("arr7", reservationRepository.findForTableId(7));
        model.addAttribute("arr8", reservationRepository.findForTableId(8));
        model.addAttribute("arr9", reservationRepository.findForTableId(9));

        return "SW-Project-main/loginBook";
    }

    @GetMapping("/login/{key}")
    public String enteredLoginView(@PathVariable Long key, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Login currentUser = (Login) session.getAttribute("user");
        model.addAttribute("loginValidationForm", new LoginValidationForm());
        model.addAttribute("loginForm", new hello.se.web.Form.LoginForm());
        return "SW-Project-main/logout_signup";
    }

    private void modelToReservationAndTable(Model model, Login currentUser) {
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