package com.bayzdelivery.service;


import com.bayzdelivery.model.Delivery;
import com.bayzdelivery.repositories.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;


@Service
public class DeliveryServiceImpl implements DeliveryService {


    @Autowired
    DeliveryRepository deliveryRepository;


    @Override
    public Delivery save(final Delivery delivery) {
        // Check if delivery man is already handling a delivery
        final Long deliveryManId = delivery.getDeliveryManId();
        if (null != deliveryManId) {
            final List<Delivery> activeDeliveries = this.findActiveDeliveriesByDeliveryManId(deliveryManId);
            if (!activeDeliveries.isEmpty()) {
                throw new IllegalStateException("Delivery man is already handling a delivery. Cannot assign a new delivery until the current one is completed.");
            }
        }
        // No need to calculate and set commission; it will be calculated at runtime
        return this.deliveryRepository.save(delivery);
    }


    @Override
    public Delivery findById(final Long deliveryId) {
        final Optional<Delivery> optionalDelivery = this.deliveryRepository.findById(deliveryId);
        return optionalDelivery.orElse(null);
    }


    @Override
    public List<Map<String, Object>> getTopDeliveryMenByCommission(final Instant startTime, final Instant endTime) {
        final List<Object[]> results = this.deliveryRepository.findTopDeliveryMenByCommission(startTime, endTime);
        final List<Map<String, Object>> topDeliveryMen = new ArrayList<>();
        int count = 0;
        for (final Object[] result : results) {
            if (3 <= count) break;
            final Map<String, Object> entry = new HashMap<>();
            entry.put("deliveryManId", result[0]); // Long value representing delivery man ID
            entry.put("totalCommission", result[1]); // Total commission calculated in query
            topDeliveryMen.add(entry);
            count++;
        }
        return topDeliveryMen;
    }


    @Override
    public Double getAverageCommission(final Instant startTime, final Instant endTime) {
        return this.deliveryRepository.findAverageCommission(startTime, endTime);
    }


    // Helper method to find active deliveries for a delivery man
    private List<Delivery> findActiveDeliveriesByDeliveryManId(final Long deliveryManId) {
        final List<Delivery> allDeliveries = (List<Delivery>) this.deliveryRepository.findAll();
        final List<Delivery> activeDeliveries = new ArrayList<>();
        for (final Delivery delivery : allDeliveries) {
            if (null != delivery.getDeliveryManId() && delivery.getDeliveryManId().equals(deliveryManId)
                    && null != delivery.getStartTime() && null == delivery.getEndTime()) {
                activeDeliveries.add(delivery);
            }
        }
        return activeDeliveries;
    }
}