package com.epam.solution.ecommerce.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epam.solution.ecommerce.dao.OfferDao;
import com.epam.solution.ecommerce.model.OfferSummary;

@Repository
public class OfferDaoImpl implements OfferDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OfferDaoImpl.class);


	@Override
	public Long createOffer(final OfferSummary offerSummary) {
		
		LOGGER.debug("Successfully offer created for : {}", offerSummary);
		return 1L;
	}

}
