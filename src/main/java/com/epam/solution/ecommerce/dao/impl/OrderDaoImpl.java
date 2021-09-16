package com.epam.solution.ecommerce.dao.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epam.solution.ecommerce.dao.OrderDao;
import com.epam.solution.ecommerce.model.OrderSummary;
import com.epam.solution.ecommerce.model.Product;
import com.epam.solution.ecommerce.utility.OrderUtility;

@Repository
public class OrderDaoImpl implements OrderDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderDaoImpl.class);

	@Override
	public Long persistOrder(final OrderSummary orderSummary) {
		
		final Long orderId = 1L;
		LOGGER.debug("Successfully order processed for : {}", orderSummary);
		return orderId;
	}

	@Override
	public OrderSummary fetchOrder(final Long orderId) {
		
		final OrderSummary orderSummary = new OrderSummary();
		final List<Product> products = OrderUtility.getProducts();
		final double totalPrice = products.parallelStream().filter(Objects::nonNull)
				.collect(Collectors.summingDouble(Product::getPrice));
		orderSummary.setProducts(products);
		orderSummary.setTotalPrice(totalPrice);
		orderSummary.setTotalCountOfItem(products.size());
		orderSummary.setOrderId(orderId);
		LOGGER.debug("Successfully order fetched for : {}", orderId);
		return orderSummary;
	}

	@Override
	public List<OrderSummary> fetchAllOrders() {
		
		final List<OrderSummary> orderSummaries = OrderUtility.getOrderSummaries();
		LOGGER.debug("Successfully all orders fetched");
		return orderSummaries;
	}

}
