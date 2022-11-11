package com.microservices.repository;

import com.microservices.entity.BookUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface BookUserRepository extends JpaRepository<BookUser, Long> {
    @Query(
            value = "SELECT * FROM book_user u WHERE u.email = :email",
            nativeQuery = true)
    Collection<BookUser> findByEmail(@Param("email") String email);
}
