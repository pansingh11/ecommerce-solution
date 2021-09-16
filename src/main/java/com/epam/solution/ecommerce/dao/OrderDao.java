package com.epam.solution.ecommerce.dao;

import java.util.List;

import com.epam.solution.ecommerce.model.OrderSummary;

public interface OrderDao {
	
	Long persistOrder(OrderSummary orderSummary);
	
	OrderSummary fetchOrder(Long orderId);
	
	List<OrderSummary> fetchAllOrders();

}
