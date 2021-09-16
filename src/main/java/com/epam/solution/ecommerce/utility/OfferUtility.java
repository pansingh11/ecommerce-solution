package com.epam.solution.ecommerce.utility;

import com.epam.solution.ecommerce.constant.ApplicationConstant;
import com.epam.solution.ecommerce.model.OfferSummary;

public class OfferUtility {

	public static OfferSummary getOfferSummary(final String productName) {

		final OfferSummary offerSummary = new OfferSummary();
		offerSummary.setOfferProductName(productName);
		offerSummary.setOfferSummary(ApplicationConstant.BUY_ONE_GET_ONE_FREE_ON + productName);
		offerSummary.setOfferId(1L);
		return offerSummary;
	}

	public static OfferSummary getCountPriceOfferSummary(final String productName , final Long profitCount, final Long actualCount) {
		
		final OfferSummary offerSummary = new OfferSummary();
		offerSummary.setOfferProductName(productName);
		offerSummary.setOfferSummary(profitCount + ApplicationConstant.FOR_THE_PRICE_OF + actualCount + ApplicationConstant.ON + productName);
		offerSummary.setOfferId(1L);
		return offerSummary;
	}

}
