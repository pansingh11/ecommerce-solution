package com.epam.solution.ecommerce.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.epam.solution.ecommerce.model.OrderSummary;
import com.epam.solution.ecommerce.service.impl.OrderServiceImpl;
import com.epam.solution.ecommerce.utility.OrderUtility;

import org.junit.Assert;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private OrderServiceImpl orderServiceImpl;

	@Test
	public void getOrder_thenReturn200() throws Exception {
		
		final String expected = "{\"products\":[{\"name\":\"Pear\",\"price\":53.0},{\"name\":\"Orange\",\"price\":27.0},{\"name\":\"Apple\",\"price\":23.0},{\"name\":\"Banana\",\"price\":23.0},{\"name\":\"Coconut\",\"price\":63.0},{\"name\":\"Mango\",\"price\":63.0}],\"totalCountOfItem\":6,\"totalPrice\":252.0,\"orderId\":1}";
		
		final OrderSummary orderSummary = OrderUtility.getOrderSummary();
		
		Mockito.when(orderServiceImpl.fetchOrder(Mockito.anyLong())).thenReturn(orderSummary);

		final MvcResult mvcResult = this.mockMvc.perform(get("/orders/5").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andReturn();
		Assert.assertEquals(expected, mvcResult.getResponse().getContentAsString());

	}
	
	@Test
	public void getOrder_thenReturn500() throws Exception {
		
		Mockito.when(orderServiceImpl.fetchOrder(Mockito.anyLong())).thenThrow(new RuntimeException());

		final MvcResult mvcResult = this.mockMvc.perform(get("/orders/5").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().is5xxServerError()).andReturn();
		
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), mvcResult.getResponse().getStatus());

	}
	
	@Test
	public void getAllOrders_thenReturn200() throws Exception {
		
		final List<OrderSummary> orderSummaries = OrderUtility.getOrderSummaries();
		
		Mockito.when(orderServiceImpl.fetchAllOrders()).thenReturn(orderSummaries);

		this.mockMvc.perform(get("/orders/allOrders").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andReturn();

	}
	
	@Test
	public void getAllOrders_thenReturn500() throws Exception {
		
		Mockito.when(orderServiceImpl.fetchAllOrders()).thenThrow(new RuntimeException());

		final MvcResult mvcResult = this.mockMvc.perform(get("/orders/allOrders").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().is5xxServerError()).andReturn();
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), mvcResult.getResponse().getStatus());

	}
	
	@Test
	public void postOrder_thenReturn200() throws Exception {
		
		final String jsonBody = "[ { 	\"name\" : \"Apple\", 	\"price\" : 50 }, { 	\"name\" : \"Mango\", 	\"price\" : 780 }, { 	\"name\" : \"Straberry\", 	\"price\" : 560 }  ]";
		
		final OrderSummary orderSummary = OrderUtility.getOrderSummary();
		
		Mockito.when(orderServiceImpl.processOrder(Mockito.anyList())).thenReturn(orderSummary);

		this.mockMvc.perform(post("/orders/submit").contentType(MediaType.APPLICATION_JSON).content(jsonBody)).andDo(print())
				.andExpect(status().isOk()).andReturn();

	}
	
	@Test
	public void postOrder_thenReturn500() throws Exception {
		
		final String jsonBody = "[\r\n"
				+ "{\r\n"
				+ "	\"name\" : \"Apple\",\r\n"
				+ "	\"price\" : 50\r\n"
				+ "},\r\n"
				+ "{\r\n"
				+ "	\"name\" : \"Mango\",\r\n"
				+ "	\"price\" : 780\r\n"
				+ "},\r\n"
				+ "{\r\n"
				+ "	\"name\" : \"Straberry\",\r\n"
				+ "	\"price\" : 560\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "]";
		
		Mockito.when(orderServiceImpl.processOrder(Mockito.anyList())).thenThrow(new RuntimeException());

		final MvcResult mvcResult = this.mockMvc.perform(post("/orders/submit").contentType(MediaType.APPLICATION_JSON).content(jsonBody)).andDo(print())
				.andExpect(status().is5xxServerError()).andReturn();
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), mvcResult.getResponse().getStatus());

	}

}
