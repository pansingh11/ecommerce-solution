package com.epam.solution.ecommerce.dao.impl;

import java.math.BigInteger;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.epam.solution.ecommerce.model.OfferSummary;
import com.epam.solution.ecommerce.utility.OfferUtility;

@ExtendWith(SpringExtension.class)
public class OfferDaoImplTest {
	
	@InjectMocks
	private OfferDaoImpl offerDaoImpl;
	
	
	@Test
	public void createOffer() throws Exception {
		
		final OfferSummary orderSummary = OfferUtility.getOfferSummary("Apple");
		
		final Long orderId = offerDaoImpl.createOffer(orderSummary);
		
		Assert.assertEquals(BigInteger.ONE.intValue(), orderId.intValue());

	}

}
