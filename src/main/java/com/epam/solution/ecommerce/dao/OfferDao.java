package com.epam.solution.ecommerce.dao;

import com.epam.solution.ecommerce.model.OfferSummary;

public interface OfferDao {
	
	Long createOffer(OfferSummary offerSummary);

}
