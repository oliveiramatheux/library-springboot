package com.microservices.repository;

import com.microservices.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Collection;

public interface BooksRepository extends JpaRepository<Books, Long> {
    @Query(value = "SELECT * FROM books b WHERE b.title = :title", nativeQuery = true)
    Collection<Books> findByTitle(@Param("title") String title);
}
