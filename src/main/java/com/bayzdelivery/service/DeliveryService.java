package com.bayzdelivery.service;

import com.bayzdelivery.model.Delivery;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public interface DeliveryService {

    public Delivery save(Delivery delivery);

    public Delivery findById(Long deliveryId);

    public List<Map<String, Object>> getTopDeliveryMenByCommission(Instant startTime, Instant endTime);

    public Double getAverageCommission(Instant startTime, Instant endTime);
}
