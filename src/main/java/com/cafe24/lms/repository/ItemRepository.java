package com.cafe24.lms.repository;

import com.cafe24.lms.domain.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("SELECT i FROM Item i ORDER BY i.no DESC")
    Page<Item> findAllByPaging(Pageable pageable);

    Item findItemByNo(Long no);
}
