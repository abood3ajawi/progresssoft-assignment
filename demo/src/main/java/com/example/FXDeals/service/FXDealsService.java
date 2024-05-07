package com.example.FXDeals.service;

import com.example.FXDeals.model.FXDeals;
import com.example.FXDeals.repository.FXDealsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FXDealsService {

    private static final Logger logger = LoggerFactory.getLogger(FXDealsService.class);

    @Autowired
    private FXDealsRepository FXDealsRepository;

    public FXDeals saveProduct(FXDeals FXdeals) {
        try {
            if (FXdeals == null) {
                throw new IllegalArgumentException("FXDeals object cannot be null");
            }
            return FXDealsRepository.save(FXdeals);
        } catch (IllegalArgumentException ex) {
            logger.error("Invalid argument: {}", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            logger.error("Error occurred while saving FXDeals: {}", ex.getMessage());
            throw new FXDealsServiceException("Error occurred while saving FXDeals", ex);
        }
    }
    public int checkIfCurrencyCodeExist(String getToCurrencyIso,String getFromCurrencyIso) {
        return FXDealsRepository.checkIfCurrencyCodeExist(getToCurrencyIso,getFromCurrencyIso);
    }
    public static class FXDealsServiceException extends RuntimeException {
        public FXDealsServiceException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
