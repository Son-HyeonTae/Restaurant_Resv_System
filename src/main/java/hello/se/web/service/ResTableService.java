package hello.se.web.service;

import hello.se.domain.DBdata.ResTable;
import hello.se.domain.DBdata.Reservation;
import hello.se.domain.respository.ResTableRepository;
import hello.se.domain.respository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ResTableService {
    private ResTableRepository resTableRepository;
    private ReservationRepository reservationRepository;

    @Autowired
    public ResTableService(ResTableRepository resTableRepository, ReservationRepository reservationRepository) {
        this.resTableRepository = resTableRepository;
        this.reservationRepository = reservationRepository;
    }

    //Restable에 errorBoolean할당
    public boolean addIsCovers(Reservation reservation) {
        List<ResTable> tableList = resTableRepository.findAll();
        return coversValidation(reservation, tableList);
    }

    /**
     * 정수 코드
     * coversValidation()
     * dateValidation()
     */
    // 테이블id값 받아와서 서로 비교 해서 인원 검증
    // false => error가 존재
    private boolean coversValidation(Reservation reservation, List<ResTable> tableList) {
        for (ResTable resTable : tableList) {
            if (resTable.getOid() == reservation.getTable_id()) {
                if (resTable.getCovers() < reservation.getCovers()) {
                    return false;
                }
            }
        }
        return true;
    }

    //error가 검출되면 DB에서 errorRow 삭제
    public void remove(boolean error, Reservation reservation) {
        if (!error) {
            reservationRepository.remove(reservation);
        }
    }

    public int findCovers(Reservation reservation) {
        ResTable target = resTableRepository.findTable(reservation.getTable_id());
        return target.getCovers();
    }

    //정수코드 날짜 검증
    public boolean dateValidation(Reservation reservation) {
        LocalDate nowDate = LocalDate.now();
        if (reservation.getDate().isBefore(nowDate)) {
            return false;
        }
        return true;
    }

    public boolean timeDuplication(Reservation newReservation, int id) {
        List<Reservation> reservationList = reservationRepository.findForTableId(id);

        for (Reservation temp : reservationList) {
            if (newReservation.getDate().equals(temp.getDate())) {
                if (newReservation.getTime().isAfter(temp.getTime()) && newReservation.getTime().isBefore(temp.getEndTime())) {
                    return false;
                }
            }
        }
        return true;
    }
}
