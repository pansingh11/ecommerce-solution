package com.epam.solution.ecommerce.service.impl;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.epam.solution.ecommerce.dao.impl.OfferDaoImpl;
import com.epam.solution.ecommerce.exception.OfferException;
import com.epam.solution.ecommerce.model.OfferSummary;
import com.epam.solution.ecommerce.utility.OfferUtility;

@ExtendWith(SpringExtension.class)
public class OfferServiceImplTest {
	
	@InjectMocks
	private OfferServiceImpl offerServiceImpl;
	
	@Mock
	private OfferDaoImpl offerDaoImpl;
	
	@Test
	public void createOfferBuyOneGetOneFree() throws Exception {
		
		final String product = "Apple";
		final OfferSummary offerSummary = OfferUtility.getOfferSummary(product);
		
		
		Mockito.when(offerDaoImpl.createOffer(Mockito.any(OfferSummary.class))).thenReturn(1L);
		final OfferSummary actualOfferSummary = offerServiceImpl.createOfferBuyOneGetOneFree(product);
		
		Assert.assertEquals(offerSummary, actualOfferSummary);

	}
	
	@Test
	public void createOfferCountPriceOffer() throws Exception {
		
		final String product = "Apple";
		final Long profitCount = 6L;
		final Long actualCount = 3L;
		final OfferSummary offerSummary = OfferUtility.getCountPriceOfferSummary(product, profitCount, actualCount);
		
		Mockito.when(offerDaoImpl.createOffer(Mockito.any(OfferSummary.class))).thenReturn(1L);
		
		final OfferSummary actualOrderSummary = offerServiceImpl.createOfferCountPriceOffer(product ,profitCount, actualCount);
		
		Assert.assertEquals(offerSummary, actualOrderSummary);

	}
	
	@Test
	public void createOfferBuyOneGetOneFree_Exception() throws Exception {
		
		final String product = null;
		
		Mockito.when(offerDaoImpl.createOffer(Mockito.any(OfferSummary.class))).thenReturn(1L);
		
		Assert.assertThrows(OfferException.class, () -> offerServiceImpl.createOfferBuyOneGetOneFree(product));

	}
	
	@Test
	public void createOfferCountPriceOffer_Exception_product() throws Exception {
		
		final String product = null;
		final Long profitCount = 6L;
		final Long actualCount = 3L;
		
		Mockito.when(offerDaoImpl.createOffer(Mockito.any(OfferSummary.class))).thenReturn(1L);
		
		Assert.assertThrows(OfferException.class, () -> offerServiceImpl.createOfferCountPriceOffer(product, profitCount, actualCount));

	}
	
	@Test
	public void createOfferCountPriceOffer_Exception_profitCount() throws Exception {
		
		final String product = "Apple";
		final Long profitCount = 6L;
		final Long actualCount = 13L;
		
		Mockito.when(offerDaoImpl.createOffer(Mockito.any(OfferSummary.class))).thenReturn(1L);
		
		Assert.assertThrows(OfferException.class, () -> offerServiceImpl.createOfferCountPriceOffer(product, profitCount, actualCount));

	}

}
