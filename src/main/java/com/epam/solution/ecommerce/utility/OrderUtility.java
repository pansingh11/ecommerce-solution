package com.epam.solution.ecommerce.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.epam.solution.ecommerce.model.OrderSummary;
import com.epam.solution.ecommerce.model.Product;

public class OrderUtility {

	public static List<Product> getProducts() {
		
		final List<Product> products = new ArrayList<>();
		final Product apple = getProduct("Apple", 23);
		final Product orange = getProduct("Orange", 27);
		final Product banana = getProduct("Banana", 23);
		final Product pear = getProduct("Pear", 53);
		final Product coconut = getProduct("Coconut", 63);
		final Product mango = getProduct("Mango", 63);
		products.add(pear);
		products.add(orange);
		products.add(apple);
		products.add(banana);
		products.add(coconut);
		products.add(mango);
		return products;
	}

	public static Product getProduct(final String name, final double price) {
		final Product apple = new Product();
		apple.setName(name);
		apple.setPrice(price);
		return apple;
	}

	public static List<OrderSummary> getOrderSummaries() {
		
		final List<OrderSummary> orderSummaries = new ArrayList<>();
		
		final OrderSummary orderSummaryFruit = new OrderSummary();
		final List<Product> products = OrderUtility.getProducts();
		final double totalPrice = products.parallelStream().filter(Objects::nonNull)
				.collect(Collectors.summingDouble(Product::getPrice));
		orderSummaryFruit.setProducts(products);
		orderSummaryFruit.setTotalPrice(totalPrice);
		orderSummaryFruit.setTotalCountOfItem(products.size());
		orderSummaryFruit.setOrderId(1L);
		
		final OrderSummary orderSummaryVegetables = new OrderSummary();
		final List<Product> vegetables = OrderUtility.getProducts();
		final double totalVegatablesPrice = vegetables.parallelStream().filter(Objects::nonNull)
				.collect(Collectors.summingDouble(Product::getPrice));
		orderSummaryVegetables.setProducts(vegetables);
		orderSummaryVegetables.setTotalPrice(totalVegatablesPrice);
		orderSummaryVegetables.setTotalCountOfItem(products.size());
		orderSummaryVegetables.setOrderId(2L);
		
		orderSummaries.add(orderSummaryVegetables);
		orderSummaries.add(orderSummaryFruit);
		return orderSummaries;		
	}

	public static OrderSummary getOrderSummary() {
		
		final OrderSummary orderSummary = new OrderSummary();
		final List<Product> products = OrderUtility.getProducts();
		final double totalPrice = products.parallelStream().filter(Objects::nonNull)
				.collect(Collectors.summingDouble(Product::getPrice));
		orderSummary.setProducts(products);
		orderSummary.setTotalPrice(totalPrice);
		orderSummary.setTotalCountOfItem(products.size());
		orderSummary.setOrderId(1L); 
		return orderSummary;
	}

}
