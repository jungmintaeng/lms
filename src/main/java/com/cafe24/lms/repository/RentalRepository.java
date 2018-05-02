package com.cafe24.lms.repository;

import com.cafe24.lms.domain.Rental;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    @Query("SELECT r FROM Rental r WHERE r.returnDate >= current_date AND r.item.no = :itemNo")
    Rental findByItemAndReturnDate(@Param("itemNo") Long itemNo);

    Page<Rental> findAllByOrderByItemDesc(Pageable pageable);
}
