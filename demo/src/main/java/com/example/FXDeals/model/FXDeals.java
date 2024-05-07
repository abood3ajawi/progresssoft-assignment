package com.example.FXDeals.model;

import jakarta.persistence.*;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "fx_deals")
@Data
public class FXDeals {

    private static final Logger logger = LoggerFactory.getLogger(FXDeals.class);

    @Id
    @Column(name = "deal_id", unique = true, nullable = false, length = 36) // Length of a UUID string
    private String dealId;

    @Column(name = "from_currency_iso", nullable = false)
    private String fromCurrencyIso;

    @Column(name = "to_currency_iso", nullable = false)
    private String toCurrencyIso;

    @Column(name = "deal_timestamp", nullable = false)
    private Timestamp dealTimestamp;

    @Column(name = "deal_amount", precision = 20, scale = 5, nullable = false)
    private BigDecimal dealAmount;

    @PrePersist
    private void setDealIdAndTimestampBeforePersist() {
        try {
            generateUniqueDealId();
            setCurrentTimestamp();
        } catch (Exception ex) {
            logger.error("Error occurred during setting deal ID and timestamp", ex);
        }
    }

    private void generateUniqueDealId() {
        try {
            this.dealId = UUID.randomUUID().toString();
        } catch (Exception ex) {
            logger.error("Error occurred during generating unique deal ID", ex);
        }
    }

    private void setCurrentTimestamp() {
        try {
            this.dealTimestamp = new Timestamp(System.currentTimeMillis());
        } catch (Exception ex) {
            logger.error("Error occurred during setting current timestamp", ex);
        }
    }
}
