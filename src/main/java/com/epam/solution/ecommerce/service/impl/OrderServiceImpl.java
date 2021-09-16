package com.epam.solution.ecommerce.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.epam.solution.ecommerce.dao.OrderDao;
import com.epam.solution.ecommerce.exception.OrderException;
import com.epam.solution.ecommerce.model.OrderSummary;
import com.epam.solution.ecommerce.model.Product;
import com.epam.solution.ecommerce.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired
	private OrderDao orderDao;

	@Override
	public OrderSummary processOrder(final List<Product> products) {

		final OrderSummary orderSummary = new OrderSummary();
		final double totalPrice = products.parallelStream().filter(Objects::nonNull)
				.collect(Collectors.summingDouble(Product::getPrice));
		orderSummary.setProducts(products);
		orderSummary.setTotalPrice(totalPrice);
		orderSummary.setTotalCountOfItem(products.size());
		final Long orderid = orderDao.persistOrder(orderSummary);
		if(products == null || products.isEmpty()) {
			throw new OrderException("No product is found in order", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		orderSummary.setOrderId(orderid);
		LOGGER.info("Successfully order processed for : {}", products);
		return orderSummary;
	}

	@Override
	public OrderSummary fetchOrder(final Long orderId) {
		
		final OrderSummary orderSummary = orderDao.fetchOrder(orderId);
		if(orderSummary == null ) {
			throw new OrderException("Order Not Found for this orderId "+orderId, HttpStatus.NO_CONTENT);
		}
		LOGGER.info("Successfully order fetched for : {}", orderId);
		return orderSummary;
	}

	@Override
	public List<OrderSummary> fetchAllOrders() {

		final List<OrderSummary> orderSummaries = orderDao.fetchAllOrders();
		if (orderSummaries == null || orderSummaries.isEmpty()) {
			throw new OrderException("No order found", HttpStatus.NO_CONTENT);
		}
		LOGGER.info("Successfully fetched all orders");
		return orderSummaries;
	}

}
