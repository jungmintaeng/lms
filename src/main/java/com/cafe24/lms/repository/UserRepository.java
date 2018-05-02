package com.cafe24.lms.repository;

import com.cafe24.lms.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByNo(Long no);

    User findUserByEmail(@Param("email") String email);

    @Modifying
    @Query("UPDATE User u SET u.name=:name, u.gender=:gender")
    int updateWithoutPassword(@Param("name") String name, @Param("gender") Character gender);

    @Modifying
    @Query("UPDATE User u SET u.name=:name, u.gender=:gender, u.password=:password, u.salt=:salt")
    int updateWithPassword(@Param("name") String name, @Param("gender") Character gender, @Param("salt") String salt ,@Param("password") String password);
}
