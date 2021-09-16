package com.epam.solution.ecommerce.service;

import java.util.List;

import com.epam.solution.ecommerce.model.OrderSummary;
import com.epam.solution.ecommerce.model.Product;

public interface OrderService {
	
	OrderSummary processOrder(List<Product> products);
	
	OrderSummary fetchOrder(Long orderId);
	
	List<OrderSummary> fetchAllOrders();

}
