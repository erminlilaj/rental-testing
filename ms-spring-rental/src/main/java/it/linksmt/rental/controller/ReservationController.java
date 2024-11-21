package it.linksmt.rental.controller;

import it.linksmt.rental.dto.CreateReservationRequest;
import it.linksmt.rental.dto.ReservationResponse;
import it.linksmt.rental.dto.ReservationStatisticsResponse;
import it.linksmt.rental.entity.ReservationEntity;
import it.linksmt.rental.entity.UserEntity;
import it.linksmt.rental.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/create")
    public ResponseEntity<ReservationResponse> createReservation(@Valid @RequestBody CreateReservationRequest reservationRequest) {
            ReservationResponse createdReservation = reservationService.createReservation(reservationRequest);
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(createdReservation);
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<ReservationResponse> cancelReservation(@PathVariable Long id) {

        ReservationResponse cancelledReservation = reservationService.cancelReservation(id);
        return ResponseEntity.ok(cancelledReservation);
    }
    @GetMapping
    public ResponseEntity<List<ReservationResponse>> getAllReservations() {
        List<ReservationResponse> reservations = reservationService.findAllReservations();
        return ResponseEntity.status(HttpStatus.OK).body(reservations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponse> getReservationById(@PathVariable("id") Long id) {
       ReservationResponse reservations=reservationService.getReservationById(id);
        return ResponseEntity.status(HttpStatus.OK).body(reservations);
    }
    @GetMapping("/statistics/{MM-yyyy}")
    public ResponseEntity<List<ReservationStatisticsResponse>> getReservationStatistics(@PathVariable("MM-yyyy") String date) {
        List<ReservationStatisticsResponse> statisticsResponses = reservationService.getReservationStatistics(date);
        return ResponseEntity.ok(statisticsResponses);
    }


}
