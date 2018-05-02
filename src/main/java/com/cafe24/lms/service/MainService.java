package com.cafe24.lms.service;

import com.cafe24.lms.domain.Item;
import com.cafe24.lms.domain.Rental;
import com.cafe24.lms.domain.Reservation;
import com.cafe24.lms.domain.User;
import com.cafe24.lms.repository.ItemRepository;
import com.cafe24.lms.repository.RentalRepository;
import com.cafe24.lms.repository.ReservationRepository;
import com.cafe24.lms.repository.UserRepository;
import com.cafe24.security.Auth;
import com.cafe24.security.SHA256Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class MainService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    public Page<Item> findAllItems(Integer page){
        PageRequest pageRequest = new PageRequest(page - 1, 5);
        return itemRepository.findAllByPaging(pageRequest);
    }

    @Transactional
    public boolean rent(Long userNo, Long itemNo){
        if(rentalRepository.findByItemAndReturnDate(itemNo) == null){
            User user = userRepository.findUserByNo(userNo);
            Item item = itemRepository.findItemByNo(itemNo);
            System.out.println(item.getNo());
            Date now = new Date();
            Date dateAfter7Days = new Date();
            dateAfter7Days.setTime( now.getTime() + (long) 1000 * 60 * 60 * 24 * 7 );
            Rental rental = new Rental();
            rental.setItem(item);
            rental.setUser(user);
            rental.setBorrowDate(now);
            rental.setReturnDate(dateAfter7Days);
            rentalRepository.save(rental);
            return true;
        }

        return false;
    }

    @Transactional
    public Reservation reserve(Long userNo, Long itemNo){
        Rental rental = rentalRepository.findByItemAndReturnDate(itemNo);

        if(rental == null){
            return null;
        }

        if(reservationRepository.findReservationByUserAndItem(userNo, itemNo) == null){
            Reservation lastReservation = reservationRepository.findLastReservationByItem(itemNo);
            Item item = itemRepository.findItemByNo(itemNo);
            User user = userRepository.findUserByNo(userNo);
            Date startDate = lastReservation == null ?
                    rental.getReturnDate() :
                    lastReservation.getReturnDate();
            startDate.setTime(startDate.getTime() + (long) 1000 * 60 * 60 * 24 * 1);
            Date dateAfter7Days = new Date();
            dateAfter7Days.setTime( startDate.getTime() + (long) 1000 * 60 * 60 * 24 * 7 );

            Reservation reservation = new Reservation();
            reservation.setOrderNo(
                    lastReservation == null ?
                    1 :
                    lastReservation.getOrderNo() + 1
            );
            reservation.setItem(item);
            reservation.setUser(user);
            reservation.setBorrowDate(startDate);
            reservation.setReturnDate(dateAfter7Days);
            reservationRepository.save(reservation);
            return reservation;
        }

        return null;
    }

    @Transactional
    public void insertAdmin(){
        if(userRepository.findUserByEmail("admin@admin") == null){
            User user = new User();
            user.setEmail("admin@admin");
            user.setName("admin");
            user.setPassword("admin");
            user.setSalt(SHA256Util.generateSalt());
            user.setPassword(SHA256Util.getEncrypt(user.getPassword(), user.getSalt()));
            user.setRole(Auth.Role.ADMIN);
            userRepository.save(user);
        }
    }
}
