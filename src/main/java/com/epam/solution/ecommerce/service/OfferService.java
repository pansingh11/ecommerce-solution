package com.epam.solution.ecommerce.service;

import com.epam.solution.ecommerce.model.OfferSummary;

public interface OfferService  {

	OfferSummary createOfferBuyOneGetOneFree(String productName);

	OfferSummary createOfferCountPriceOffer(String productName, Long profitCount, Long actualCount);
	
}
