package com.example.FXDeals.validator;

import com.example.FXDeals.model.FXDeals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class CheckingFXDealsFields {

    private static final Logger logger = LoggerFactory.getLogger(CheckingFXDealsFields.class);

    private final FXDeals FXdeals;

    public CheckingFXDealsFields(FXDeals FXdeals) {
        this.FXdeals = FXdeals;
    }

    public boolean isValidDealAmount() {
        BigDecimal dealAmount = FXdeals.getDealAmount();
        if (dealAmount == null) {
            logger.error("Deal amount is null");
            return false;
        }
        return dealAmount.compareTo(BigDecimal.ZERO) >= 0;
    }

    public boolean isValidFromCurrencyIso() {
        String fromCurrencyIso = FXdeals.getFromCurrencyIso();
        if (fromCurrencyIso == null) {
            logger.error("From currency ISO is null");
            return false;
        }
        return fromCurrencyIso.length() == 3 && Pattern.matches("[A-Za-z]+", fromCurrencyIso);
    }

    public boolean isValidToCurrencyIso() {
        String toCurrencyIso = FXdeals.getToCurrencyIso();
        if (toCurrencyIso == null) {
            logger.error("To currency ISO is null");
            return false;
        }
        return toCurrencyIso.length() == 3 && Pattern.matches("[A-Za-z]+", toCurrencyIso);
    }

    public String result() {
        if (!isValidDealAmount()) {
            return "Invalid deals amount";
        } else if (!isValidFromCurrencyIso()) {
            return "Invalid format from_currency_iso";
        } else if (!isValidToCurrencyIso()) {
            return "Invalid format to_currency_iso";
        }
        return "OK";
    }
}
