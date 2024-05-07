package com.example.FXDeals.controller;

import com.example.FXDeals.model.FXDeals;
import com.example.FXDeals.validator.CheckingFXDealsFields;
import com.example.FXDeals.service.FXDealsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class FXDealsController {

    private static final Logger logger = LoggerFactory.getLogger(FXDealsController.class);

    @Autowired
    private FXDealsService FXdealsService;

    @PostMapping("/FXdeals")
    public ResponseEntity<Object> saveProduct(@RequestBody FXDeals FXdeals) {
        try {
            String result = new CheckingFXDealsFields(FXdeals).result();
            if (Objects.equals(result, "OK")) {
                if(FXdealsService.checkIfCurrencyCodeExist(FXdeals.getFromCurrencyIso(),FXdeals.getToCurrencyIso())!=2)
                    return ResponseEntity.badRequest().body("currency not exist in the standard iso currency list");
                FXdeals.setToCurrencyIso(FXdeals.getToCurrencyIso().toUpperCase());
                FXdeals.setFromCurrencyIso(FXdeals.getFromCurrencyIso().toUpperCase());
                FXDeals savedFXDeals = FXdealsService.saveProduct(FXdeals);
                return ResponseEntity.status(HttpStatus.CREATED).body(savedFXDeals);
            } else {
                return ResponseEntity.badRequest().body("Validation failed: " + result);
            }
        } catch (Exception ex) {
            logger.error("An error occurred while saving FXDeals", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request");
        }
    }
}
