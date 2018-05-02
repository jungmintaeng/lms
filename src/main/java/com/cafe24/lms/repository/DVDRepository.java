package com.cafe24.lms.repository;

import com.cafe24.lms.domain.DVD;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DVDRepository extends JpaRepository<DVD, Long> {
}
