package hello.se.web.service;

import hello.se.domain.DBdata.ResTable;
import hello.se.domain.DBdata.Reservation;
import hello.se.domain.respository.ResTableRepository;
import hello.se.domain.respository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    private ResTableRepository resTableRepository;
    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ResTableRepository resTableRepository, ReservationRepository reservationRepository) {
        this.resTableRepository = resTableRepository;
        this.reservationRepository = reservationRepository;
    }

    public void remove(boolean error, Reservation reservation) {
        if (!error) {
            reservationRepository.remove(reservation);
        }
    }
}