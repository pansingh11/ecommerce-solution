package com.epam.solution.ecommerce.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.solution.ecommerce.model.OrderSummary;
import com.epam.solution.ecommerce.model.Product;
import com.epam.solution.ecommerce.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;

	@GetMapping(value = "/{orderId}")
	public OrderSummary getOrder(final HttpServletRequest httpServletRequest,
			final HttpServletResponse httpServletResponse, @PathVariable final Long orderId) {
		LOGGER.info("Fetching order for: {}", orderId);
		return orderService.fetchOrder(orderId);
	}
	
	@GetMapping(value = "/allOrders")
	public List<OrderSummary> getAllOrders(final HttpServletRequest httpServletRequest,
			final HttpServletResponse httpServletResponse) {
		LOGGER.info("Fetching all orders");
		return orderService.fetchAllOrders();
	}

	@PostMapping(value = "/submit")
	public OrderSummary postOrder(final HttpServletRequest httpServletRequest,
			final HttpServletResponse httpServletResponse, @RequestBody List<Product> products) {
		LOGGER.info("Processing order for: {}", products);
		return orderService.processOrder(products);
	}

}