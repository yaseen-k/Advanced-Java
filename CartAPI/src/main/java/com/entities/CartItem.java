package entities.ShoppingCart;

public class CartItem {
	private int cartItemId;
	private ProductWithId product;
	private int quantity;
	
	public int getCartItemId() {
		return cartItemId;
	}
	
	public void setCartItemId(int cartItemId) {
		this.cartItemId = cartItemId;
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
