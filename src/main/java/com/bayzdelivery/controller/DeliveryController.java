package com.bayzdelivery.controller;


import com.bayzdelivery.model.Delivery;
import com.bayzdelivery.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/delivery")
public class DeliveryController {


    @Autowired
    DeliveryService deliveryService;


    @PostMapping
    public ResponseEntity<Delivery> createNewDelivery(@RequestBody final Delivery delivery) {
        return ResponseEntity.ok(this.deliveryService.save(delivery));
    }


    @GetMapping(path = "/{delivery-id}")
    public ResponseEntity<Delivery> getDeliveryById(@PathVariable(name = "delivery-id", required = true) final Long deliveryId) {
        final Delivery delivery = this.deliveryService.findById(deliveryId);
        if (delivery != null)
            return ResponseEntity.ok(delivery);
        return ResponseEntity.notFound().build();
    }


    @GetMapping(path = "/top-delivery-men")
    public ResponseEntity<Map<String, Object>> getTopDeliveryMen(
            @RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final Instant startTime,
            @RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final Instant endTime) {
        final List<Map<String, Object>> topDeliveryMen = this.deliveryService.getTopDeliveryMenByCommission(startTime, endTime);
        final Double averageCommission = this.deliveryService.getAverageCommission(startTime, endTime);
        final Map<String, Object> response = new HashMap<>();
        response.put("topDeliveryMen", topDeliveryMen);
        response.put("averageCommission", averageCommission);
        return ResponseEntity.ok(response);
    }
}