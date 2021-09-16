package com.epam.solution.ecommerce.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.epam.solution.ecommerce.model.OrderSummary;
import com.epam.solution.ecommerce.utility.OrderUtility;

@ExtendWith(SpringExtension.class)
public class OrderDaoImplTest {
	
	@InjectMocks
	private OrderDaoImpl orderDaoImpl;
	
	
	@Test
	public void persistOrder() throws Exception {
		
		final OrderSummary orderSummary = OrderUtility.getOrderSummary();
		
		final Long orderId = orderDaoImpl.persistOrder(orderSummary);
		
		Assert.assertEquals(BigInteger.ONE.intValue(), orderId.intValue());

	}
	
	@Test
	public void fetchAllOrders() throws Exception {
		
		final List<OrderSummary> orderSummaries = OrderUtility.getOrderSummaries();
		
		final List<OrderSummary> actualOrderSummaries = orderDaoImpl.fetchAllOrders();
		
		Assert.assertEquals(orderSummaries, actualOrderSummaries);

	}
	
	@Test
	public void fetchOrder() throws Exception {
		
		final OrderSummary orderSummary = OrderUtility.getOrderSummary();
		
		final OrderSummary actualOrderSummary = orderDaoImpl.fetchOrder(1L);
		
		Assert.assertEquals(orderSummary, actualOrderSummary);

	}

}
