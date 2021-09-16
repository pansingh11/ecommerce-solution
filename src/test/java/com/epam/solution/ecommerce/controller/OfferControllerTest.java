package com.epam.solution.ecommerce.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.epam.solution.ecommerce.model.OfferSummary;
import com.epam.solution.ecommerce.service.impl.OfferServiceImpl;
import com.epam.solution.ecommerce.utility.OfferUtility;

@WebMvcTest(OfferController.class)
public class OfferControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private OfferServiceImpl offerServiceImpl;

	@Test
	public void createOfferBuyOneGetOneFree_thenReturn200() throws Exception {

		final String expected = "{\"offerProductName\":\"Apple\",\"offerSummary\":\"buy one get one free on Apple\",\"offerId\":1}";

		final String product = "Apple";
		final OfferSummary offerSummary = OfferUtility.getOfferSummary(product);

		Mockito.when(offerServiceImpl.createOfferBuyOneGetOneFree(Mockito.anyString())).thenReturn(offerSummary);

		final MvcResult mvcResult = this.mockMvc
				.perform(get("/offers/createOffer/buyOneGetOneFree/Apple").contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk()).andReturn();
		Assert.assertEquals(expected, mvcResult.getResponse().getContentAsString());

	}

	@Test
	public void createOfferBuyOneGetOneFree_thenReturn500() throws Exception {

		Mockito.when(offerServiceImpl.createOfferBuyOneGetOneFree(Mockito.anyString()))
				.thenThrow(new RuntimeException());

		final MvcResult mvcResult = this.mockMvc.perform(get("/offers/createOffer/buyOneGetOneFree/Apple").contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().is5xxServerError()).andReturn();

		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), mvcResult.getResponse().getStatus());

	}

	@Test
	public void createOfferCountPriceOffer_thenReturn200() throws Exception {

		final String expected = "{\"offerProductName\":\"Apple\",\"offerSummary\":\"buy one get one free on Apple\",\"offerId\":1}";

		final String product = "Apple";
		final OfferSummary offerSummary = OfferUtility.getOfferSummary(product);

		Mockito.when(offerServiceImpl.createOfferCountPriceOffer(Mockito.anyString(), Mockito.anyLong(), Mockito.anyLong()))
				.thenReturn(offerSummary);

		final MvcResult mvcResult = this.mockMvc
				.perform(get("/offers/createOffer/countPriceOffer/Apple").contentType(MediaType.APPLICATION_JSON)
						.queryParam("profitCount", "5").queryParam("actualCount", "3"))
				.andDo(print()).andExpect(status().isOk()).andReturn();

		Assert.assertEquals(expected, mvcResult.getResponse().getContentAsString());

	}

	@Test
	public void createOfferCountPriceOffer_thenReturn500() throws Exception {

		Mockito.when(offerServiceImpl.createOfferCountPriceOffer(Mockito.anyString(), Mockito.anyLong(), Mockito.anyLong()))
				.thenThrow(new RuntimeException());

		final MvcResult mvcResult = this.mockMvc
				.perform(get("/offers/createOffer/countPriceOffer/Apple").contentType(MediaType.APPLICATION_JSON).queryParam("profitCount", "5")
						.queryParam("actualCount", "3"))
				.andDo(print()).andExpect(status().is5xxServerError()).andReturn();
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), mvcResult.getResponse().getStatus());

	}

}
