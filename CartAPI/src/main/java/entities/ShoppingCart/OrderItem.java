package entities.ShoppingCart;

public class OrderItem {
	private int orderItemId;
	private ProductWithId product;
	private int quantity;

	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public ProductWithId getProduct() {
		return product;
	}

	public void setProduct(ProductWithId product) {
		this.product = product;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
