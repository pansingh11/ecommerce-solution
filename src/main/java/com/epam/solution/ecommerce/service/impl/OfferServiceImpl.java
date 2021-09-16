package com.epam.solution.ecommerce.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.epam.solution.ecommerce.constant.ApplicationConstant;
import com.epam.solution.ecommerce.dao.OfferDao;
import com.epam.solution.ecommerce.exception.OfferException;
import com.epam.solution.ecommerce.model.OfferSummary;
import com.epam.solution.ecommerce.service.OfferService;

@Service
public class OfferServiceImpl implements OfferService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OfferServiceImpl.class);
	
	@Autowired
	private OfferDao offerDao;

	@Override
	public OfferSummary createOfferBuyOneGetOneFree(final String productName) {
		
		if(productName == null || productName.isEmpty()) {
			throw new OfferException("No product found for creating offer", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		final OfferSummary offerSummary = new OfferSummary();
		offerSummary.setOfferProductName(productName);
		offerSummary.setOfferSummary(ApplicationConstant.BUY_ONE_GET_ONE_FREE_ON + productName);
		final Long offerId = offerDao.createOffer(offerSummary);
		offerSummary.setOfferId(offerId);
		LOGGER.info("Successfully offer created for : {}", productName);
		return offerSummary;
	}

	@Override
	public OfferSummary createOfferCountPriceOffer(final String productName, final Long profitCount, final Long actualCount) {
		
		if(productName == null || productName.isEmpty()) {
			throw new OfferException("No product found for creating offer", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(profitCount < actualCount) {
			throw new OfferException("profitCount should greater than actualCount for "+productName, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		final OfferSummary offerSummary = new OfferSummary();
		offerSummary.setOfferProductName(productName);
		offerSummary.setOfferSummary(profitCount + ApplicationConstant.FOR_THE_PRICE_OF + actualCount + ApplicationConstant.ON + productName);
		final Long offerId = offerDao.createOffer(offerSummary);
		offerSummary.setOfferId(offerId);
		LOGGER.info("Successfully offer created for : {}", productName);
		return offerSummary;
	}

}
