package com.epam.solution.ecommerce.model;

import java.util.List;
import java.util.Objects;

public class OrderSummary {
	
	private List<Product> products;
	
	private int totalCountOfItem;
	
	private double totalPrice;
	
	private Long orderId;

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public int getTotalCountOfItem() {
		return totalCountOfItem;
	}

	public void setTotalCountOfItem(int totalCountOfItem) {
		this.totalCountOfItem = totalCountOfItem;
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderId, products, totalCountOfItem, totalPrice);
	}

	@Override
	public boolean equals(final Object object) {
		if (this == object)
			return true;
		if (!(object instanceof OrderSummary))
			return false;
		final OrderSummary other = (OrderSummary) object;
		return Objects.equals(orderId, other.orderId) && Objects.equals(products, other.products)
				&& totalCountOfItem == other.totalCountOfItem
				&& Double.doubleToLongBits(totalPrice) == Double.doubleToLongBits(other.totalPrice);
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("OrderSummary [products=");
		builder.append(products);
		builder.append(", totalCountOfItem=");
		builder.append(totalCountOfItem);
		builder.append(", totalPrice=");
		builder.append(totalPrice);
		builder.append(", orderId=");
		builder.append(orderId);
		builder.append("]");
		return builder.toString();
	}

}
