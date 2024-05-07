package com.example.FXDeals;

import com.example.FXDeals.controller.FXDealsController;
import com.example.FXDeals.model.FXDeals;
import com.example.FXDeals.service.FXDealsService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class FXDealsControllerTest {

	@Mock
	private FXDealsService fxDealsService;

	@InjectMocks
	private FXDealsController fxDealsController;

	@Test
	void testSaveProduct_ValidInput() {

		FXDeals fxDeals = new FXDeals();
		fxDeals.setFromCurrencyIso("USD");
		fxDeals.setToCurrencyIso("EUR");
		fxDeals.setDealAmount(BigDecimal.valueOf(10));


		when(fxDealsService.checkIfCurrencyCodeExist("USD", "EUR")).thenReturn(2);
		when(fxDealsService.saveProduct(any(FXDeals.class))).thenReturn(fxDeals);


		ResponseEntity<Object> response = fxDealsController.saveProduct(fxDeals);


		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(fxDeals, response.getBody());
		verify(fxDealsService, times(1)).saveProduct(fxDeals);
	}

	@Test
	void testSaveProduct_InvalidInput() {
		FXDeals fxDeals = new FXDeals();
		fxDeals.setFromCurrencyIso("USD");
		fxDeals.setToCurrencyIso("GSP");
		fxDeals.setDealAmount(BigDecimal.valueOf(10));


		when(fxDealsService.checkIfCurrencyCodeExist("USD", "EUR")).thenReturn(0);


		ResponseEntity<Object> response = fxDealsController.saveProduct(fxDeals);


		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("currency not exist in the standard iso currency list", response.getBody());
		verify(fxDealsService, never()).saveProduct(any());
	}


}
