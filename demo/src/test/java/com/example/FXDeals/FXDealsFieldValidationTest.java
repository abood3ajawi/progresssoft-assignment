package com.example.FXDeals;

import com.example.FXDeals.model.FXDeals;
import com.example.FXDeals.validator.CheckingFXDealsFields;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class FXDealsFieldValidationTest {

    private static final Logger logger = LoggerFactory.getLogger(FXDealsFieldValidationTest.class);

    @Test
    void testIsValidDealAmount_Valid() {

        FXDeals fxDeals = new FXDeals();
        fxDeals.setDealAmount(new BigDecimal("1000"));
        CheckingFXDealsFields checker = new CheckingFXDealsFields(fxDeals);


        boolean isValid = checker.isValidDealAmount();


        assertTrue(isValid);
    }

    @Test
    void testIsValidFromCurrencyIso_Valid() {

        FXDeals fxDeals = new FXDeals();
        fxDeals.setFromCurrencyIso("USD");
        CheckingFXDealsFields checker = new CheckingFXDealsFields(fxDeals);


        boolean isValid = checker.isValidFromCurrencyIso();


        assertTrue(isValid);
    }

    @Test
    void testIsValidFromCurrencyIso_InvalidFormat() {

        FXDeals fxDeals = new FXDeals();
        fxDeals.setFromCurrencyIso("USDD");
        CheckingFXDealsFields checker = new CheckingFXDealsFields(fxDeals);


        boolean isValid = checker.isValidFromCurrencyIso();


        assertFalse(isValid);
        logger.error("Invalid format from_currency_iso");
    }

    @Test
    void testIsValidToCurrencyIso_Valid() {
        FXDeals fxDeals = new FXDeals();
        fxDeals.setToCurrencyIso("EUR");
        CheckingFXDealsFields checker = new CheckingFXDealsFields(fxDeals);


        boolean isValid = checker.isValidToCurrencyIso();


        assertTrue(isValid);
    }

    @Test
    void testIsValidToCurrencyIso_InvalidFormat() {

        FXDeals fxDeals = new FXDeals();
        fxDeals.setToCurrencyIso("EURO");
        CheckingFXDealsFields checker = new CheckingFXDealsFields(fxDeals);


        boolean isValid = checker.isValidToCurrencyIso();


        assertFalse(isValid);
        logger.error("Invalid format to_currency_iso");
    }

    @Test
    void testResult_ValidDeal() {

        FXDeals fxDeals = new FXDeals();
        fxDeals.setDealAmount(new BigDecimal("1000"));
        fxDeals.setFromCurrencyIso("USD");
        fxDeals.setToCurrencyIso("EUR");
        CheckingFXDealsFields checker = new CheckingFXDealsFields(fxDeals);


        String result = checker.result();


        assertEquals("OK", result);
    }

}
