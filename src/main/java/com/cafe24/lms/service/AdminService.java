package com.cafe24.lms.service;

import com.cafe24.lms.domain.Rental;
import com.cafe24.lms.domain.Reservation;
import com.cafe24.lms.repository.RentalRepository;
import com.cafe24.lms.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    public Page<Rental> findRental(Integer page){
        PageRequest pageRequest = new PageRequest(page - 1, 5);
        return rentalRepository.findAllByOrderByItemDesc(pageRequest);
    }

    public Page<Reservation> findReservation(Integer page){
        PageRequest pageRequest = new PageRequest(page - 1, 5);
        return reservationRepository.findAllByOrderByItemDescOrderNoAsc(pageRequest);
    }
}
