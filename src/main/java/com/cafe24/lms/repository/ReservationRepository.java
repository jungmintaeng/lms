package com.cafe24.lms.repository;

import com.cafe24.lms.domain.Reservation;
import com.cafe24.lms.domain.ReservationId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface ReservationRepository extends JpaRepository<Reservation, ReservationId> {
    @Query("SELECT r FROM Reservation r WHERE r.item.no=:itemNo AND r.user.no=:userNo")
    Reservation findReservationByUserAndItem(@Param("userNo") Long userNo, @Param("itemNo") Long itemNo);

    @Query("SELECT MAX(r.returnDate) FROM Reservation r WHERE r.item.no=:itemNo")
    Date findLastReturnDateByItemNo(@Param("itemNo") Long itemNo);

    @Query("SELECT r from Reservation r GROUP BY r.item HAVING r.item.no=:itemNo AND r.returnDate=MAX(r.returnDate)")
    Reservation findLastReservationByItem(@Param("itemNo") Long itemNo);

    Page<Reservation> findAllByOrderByItemDescOrderNoAsc(Pageable pageable);
}
