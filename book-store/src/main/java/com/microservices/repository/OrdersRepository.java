package com.microservices.repository;

import com.microservices.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Collection;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    @Query(value = "SELECT SUM(price) FROM orders o WHERE date > now() - interval '24 hours'",
            nativeQuery = true)
    Collection<Double> getSellsAmount24hours();

    @Query(value = "SELECT * FROM orders o WHERE date > now() - interval '24 hours'",
            nativeQuery = true)
    Collection<Orders> getSells24hours();

}
