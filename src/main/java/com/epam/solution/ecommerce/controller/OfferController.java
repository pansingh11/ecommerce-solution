package com.epam.solution.ecommerce.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epam.solution.ecommerce.service.OfferService;


@RestController
@RequestMapping("/offers")
public class OfferController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OfferController.class);

	@Autowired
	private OfferService offerService;

	@GetMapping(value = "/createOffer/buyOneGetOneFree/{productName}")
	public Object createOfferBuyOneGetOneFree(final HttpServletRequest httpServletRequest,
			final HttpServletResponse httpServletResponse, @PathVariable final String productName) {
		LOGGER.info("Creating offer for: {}", productName);
		return offerService.createOfferBuyOneGetOneFree(productName);
	}

	@GetMapping(value = "/createOffer/countPriceOffer/{productName}")
	public Object createOfferCountPriceOffer(final HttpServletRequest httpServletRequest,
			final HttpServletResponse httpServletResponse, @PathVariable final String productName,
			@RequestParam Long profitCount, @RequestParam Long actualCount) {
		LOGGER.info("Creating offer for: {}", productName);
		return offerService.createOfferCountPriceOffer(productName, profitCount, actualCount);
	}

}