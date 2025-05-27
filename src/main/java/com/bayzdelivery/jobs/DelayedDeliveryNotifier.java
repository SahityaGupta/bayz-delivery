package com.bayzdelivery.jobs;

import com.bayzdelivery.model.Delivery;
import com.bayzdelivery.repositories.DeliveryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Component
public class DelayedDeliveryNotifier {

    private static final Logger LOG = LoggerFactory.getLogger(DelayedDeliveryNotifier.class);

    @Autowired
    private DeliveryRepository deliveryRepository;

    /**
     * Scheduled task to check for delayed deliveries every 30 seconds.
     * If a delivery has started but not ended and exceeds 45 minutes, notify customer support.
     */
    @Scheduled(fixedDelay = 30000)
    public void checkDelayedDeliveries() {
        List<Delivery> deliveries = (List<Delivery>) deliveryRepository.findAll();
        Instant currentTime = Instant.now();
        for (Delivery delivery : deliveries) {
            if (delivery.getStartTime() != null && delivery.getEndTime() == null) {
                long durationInMinutes = Duration.between(delivery.getStartTime(), currentTime).toMinutes();
                if (durationInMinutes > 45) {
                    LOG.info("Delivery ID {} is delayed beyond 45 minutes. Notifying customer support.", delivery.getId());
                    notifyCustomerSupport();
                }
            }
        }
    }

    /**
     * This method should be called to notify customer support team.
     * It just writes notification on console but it may be email or push notification in real.
     * So that this method should run in an async way.
     */
    @Async
    public void notifyCustomerSupport() {
        LOG.info("Customer support team is notified!");
    }
}
