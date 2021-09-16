package com.epam.solution.ecommerce.service.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.epam.solution.ecommerce.dao.impl.OrderDaoImpl;
import com.epam.solution.ecommerce.model.OrderSummary;
import com.epam.solution.ecommerce.model.Product;
import com.epam.solution.ecommerce.utility.OrderUtility;

@ExtendWith(SpringExtension.class)
public class OrderServiceImplTest {
	
	@InjectMocks
	private OrderServiceImpl orderServiceImpl;
	
	@Mock
	private OrderDaoImpl orderDaoImpl;
	
	@Test
	public void processOrder() throws Exception {
		
		final OrderSummary orderSummary = OrderUtility.getOrderSummary();
		final List<Product> products = OrderUtility.getProducts();
		
		
		Mockito.when(orderDaoImpl.persistOrder(Mockito.any())).thenReturn(1L);
		final OrderSummary actualOrderSummary = orderServiceImpl.processOrder(products);
		
		Assert.assertEquals(orderSummary, actualOrderSummary);

	}
	
	@Test
	public void fetchAllOrders() throws Exception {
		
		final List<OrderSummary> orderSummaries = OrderUtility.getOrderSummaries();
		
		Mockito.when(orderDaoImpl.fetchAllOrders()).thenReturn(orderSummaries);
		final List<OrderSummary> actualOrderSummaries = orderServiceImpl.fetchAllOrders();
		
		Assert.assertEquals(orderSummaries, actualOrderSummaries);

	}
	
	@Test
	public void fetchOrder() throws Exception {
		
		final OrderSummary orderSummary = OrderUtility.getOrderSummary();
		
		Mockito.when(orderDaoImpl.fetchOrder(Mockito.anyLong())).thenReturn(orderSummary);
		final OrderSummary actualOrderSummary = orderServiceImpl.fetchOrder(1L);
		
		Assert.assertEquals(orderSummary, actualOrderSummary);

	}

}
