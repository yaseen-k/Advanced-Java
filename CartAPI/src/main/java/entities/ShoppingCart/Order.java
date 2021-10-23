package entities.ShoppingCart;

import java.util.List;

public class Order {
	private int orderId;
	private List<OrderItem> products;
	private String orderStatus;
	
	public int getOrderId() {
		return orderId;
	}
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public List<OrderItem> getProducts() {
		return products;
	}
	
	public void setProducts(List<OrderItem> products) {
		this.products = products;
	}
	
	public String getOrderStatus() {
		return orderStatus;
	}
	
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
}
