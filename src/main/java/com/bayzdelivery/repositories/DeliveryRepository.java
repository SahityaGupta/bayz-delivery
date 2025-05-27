package com.bayzdelivery.repositories;


import com.bayzdelivery.model.Delivery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.time.Instant;
import java.util.List;


@RestResource(exported = false)
public interface DeliveryRepository extends CrudRepository<Delivery, Long> {


    @Query("SELECT d.deliveryManId, SUM((d.price * 0.05) + (d.distance * 0.5)) as totalCommission FROM Delivery d WHERE d.startTime >= :startTime AND d.endTime <= :endTime GROUP BY d.deliveryManId ORDER BY totalCommission DESC")
    List<Object[]> findTopDeliveryMenByCommission(@Param("startTime") Instant startTime, @Param("endTime") Instant endTime);


    @Query("SELECT AVG((d.price * 0.05) + (d.distance * 0.5)) FROM Delivery d WHERE d.startTime >= :startTime AND d.endTime <= :endTime")
    Double findAverageCommission(@Param("startTime") Instant startTime, @Param("endTime") Instant endTime);
}