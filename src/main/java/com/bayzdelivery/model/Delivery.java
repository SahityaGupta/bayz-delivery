package com.bayzdelivery.model;

        

import java.io.Serializable;
import java.time.Instant;

        

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;



@Entity
@Table(name = "delivery")
public class Delivery implements Serializable {



    private static final long serialVersionUID = 123765351514001L;



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;



    @NotNull
    @Column(name = "start_time")
    Instant startTime;



    @NotNull
    @Column(name = "end_time")
    Instant endTime;



    @Column(name = "distance")
    Long distance;



    @Column(name = "price")
    Long price;

    @JoinColumn(name = "delivery_man_id", referencedColumnName = "id")
    Long deliveryManId;

    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    Long customerId;
    
    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }



    public Instant getStartTime() {
        return startTime;
    }



    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }



    public Instant getEndTime() {
        return endTime;
    }



    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }



    public Long getDistance() {
        return distance;
    }



    public void setDistance(Long distance) {
        this.distance = distance;
    }



    public Long getPrice() {
        return price;
    }



    public void setPrice(Long price) {
        this.price = price;
    }



    public Long getDeliveryManId() {
        return deliveryManId;
    }



    public void setDeliveryMan(Long deliveryManId) {
        this.deliveryManId = deliveryManId;
    }



    public Long getCustomerId() {
        return customerId;
    }



    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }



    // Method to calculate commission at runtime
    public Long calculateCommission() {
        if (price != null && distance != null) {
            return (long) (price * 0.05 + distance * 0.5);
        }
        return 0L;
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((distance == null) ? 0 : distance.hashCode());
        result = prime * result + ((deliveryManId == null) ? 0 : deliveryManId.hashCode());
        result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
        result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
        return result;
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Delivery other = (Delivery) obj;
        if (distance == null) {
            if (other.distance != null)
                return false;
        } else if (!distance.equals(other.distance))
            return false;
        if (deliveryManId == null) {
            if (other.deliveryManId != null)
                return false;
        } else if (!deliveryManId.equals(other.deliveryManId))
            return false;
        if (endTime == null) {
            if (other.endTime != null)
                return false;
        } else if (!endTime.equals(other.endTime))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (customerId == null) {
            if (other.customerId != null)
                return false;
        } else if (!customerId.equals(other.customerId))
            return false;
        if (startTime == null) {
            if (other.startTime != null)
                return false;
        } else if (!startTime.equals(other.startTime))
            return false;
        return true;
    }



    @Override
    public String toString() {
        return "Delivery [id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + ", distance=" + distance + ", deliveryManId=" + deliveryManId + ", customerId=" + customerId + "]";
    }
}