package entities.ShoppingCart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
	List<User> usersList;
	List<UserCart> userCartList;
	Map<Integer, List<OrderItem>> userItemListMap;
	Map<Integer, List<Order>> userOrderListMap;
	
	public UserRepository() {
		usersList = new ArrayList<User>();
		userCartList = new ArrayList<UserCart>();
		userItemListMap = new HashMap<Integer, List<OrderItem>>();
		userOrderListMap = new HashMap<Integer, List<Order>>();
		
		// User 1
		Address a1 = new Address();
		a1.setStreet("Botanical Garden Road");
		a1.setCity("Hyderabad");
		a1.setState("Telangana");
		a1.setPincode("102302");
		
		User u1 = new User();
		u1.setUserID(10);
		u1.setName("Rahul Kumar");
		u1.setEmail("rahulkum123@beehyv.com");
		u1.setPhone("654126");
		u1.setAddress(a1);
		usersList.add(u1);
		
		Cart cart1 = new Cart();
		cart1.setCartId(10);
		cart1.setProducts(new ArrayList<CartItem>());
		
		UserCart userCart1 = new UserCart();
		userCart1.setUserId(10);
		userCart1.setCart(cart1);
		
		userItemListMap.put(10, new ArrayList<OrderItem>());
		userOrderListMap.put(10, new ArrayList<Order>());
		
		//User 2
		Address a2 = new Address();
		a2.setStreet("Millat Colony, Bhadauni");
		a2.setCity("Nawada");
		a2.setState("Bihar");
		a2.setPincode("805110");
		
		User u2 = new User();
		u2.setUserID(388);
		u2.setName("Talha Yaseen");
		u2.setEmail("yaseen.k@beehyv.com");
		u2.setPhone("5698412");
		u2.setAddress(a2);
		usersList.add(u2);
		
		Cart cart2 = new Cart();
		cart2.setCartId(388);
		cart2.setProducts(new ArrayList<CartItem>());
		
		UserCart userCart2 = new UserCart();
		userCart2.setUserId(388);
		userCart2.setCart(cart2);
		
		userItemListMap.put(388, new ArrayList<OrderItem>());
		userOrderListMap.put(388, new ArrayList<Order>());
		
		userCartList.add(userCart1);
		userCartList.add(userCart2);
	}

	public boolean isUserPresent(int userId) {
		for (User u : usersList) {
			if (u.getUserID() == userId) {
				return true;
			}
		}
		
		return false;
	}
	
	public User getUserById(int userId) {
		for (User u : usersList) {
			if (u.getUserID() == userId) {
				return u;
			}
		}

		return null;
	}

	public void updateProfile(User user) {
		for (User u : usersList) {
			if (u.getUserID() == user.getUserID()) {
				u.setName(user.getName());
				//u.setEmail(user.getEmail());
				u.setPhone(user.getPhone());

				// updating address
				Address updatedAdd = user.getAddress();
				Address toUpdateAdd = u.getAddress();

				toUpdateAdd.setStreet(updatedAdd.getStreet());
				toUpdateAdd.setCity(updatedAdd.getCity());
				toUpdateAdd.setState(updatedAdd.getState());
				toUpdateAdd.setPincode(updatedAdd.getPincode());
			}
		}
	}
	
	public Cart getCart(int userId) {
		for(UserCart userCart : userCartList) {
			if(userCart.getUserId() == userId) {
				return userCart.getCart();
			}
		}
		
		return null;
	}
	
	public CartItem getCartItem(int userId, int cartitemId) {
		for(UserCart userCart : userCartList) {
			if(userCart.getUserId() == userId) {
				for(CartItem cartItem : userCart.getCart().getProducts()) {
					if(cartItem.getCartItemId() == cartitemId) {
						return cartItem;
					}
				}
			}
		}
		
		return null;
	}
	
	public CartItem addCartItem(int userId, int productId) {
		for(UserCart userCart : userCartList) {
			if(userCart.getUserId() == userId) {
				for(CartItem cartItem : userCart.getCart().getProducts()) {
					if(cartItem.getProduct().getProductId() == productId) {
						cartItem.setQuantity(cartItem.getQuantity()+1);
						return cartItem;
					}
				}
				
				CartItem cartItem = new CartItem();
				cartItem.setCartItemId(userCart.getCart().getProducts().size()+1);
				
				ProductRepository productRepo = new ProductRepository();
				ProductWithId product = productRepo.getProductById(productId);
				cartItem.setProduct(product);
				cartItem.setQuantity(1);
				
				userCart.getCart().getProducts().add(cartItem);
				
				return cartItem;
			}
		}
		
		return null;
	}
	
	public String removeItemFromCart(int userId, int productId) {
		for(UserCart userCart : userCartList) {
			if(userCart.getUserId() == userId) {
				for(CartItem cartItem : userCart.getCart().getProducts()) {
					if(cartItem.getProduct().getProductId() == productId) {
						String name = cartItem.getProduct().getName();
						userCart.getCart().getProducts().remove(cartItem);
						return name;
					}
				}
			}
		}
		
		return null;
	}
	
	public CartItem changeQuantity(int userId, int productId, int quantity) {
		for(UserCart userCart : userCartList) {
			if(userCart.getUserId() == userId) {
				for(CartItem cartItem : userCart.getCart().getProducts()) {
					if(cartItem.getProduct().getProductId() == productId) {
							cartItem.setQuantity(quantity);
							return cartItem;
						}
					}
				}
			}

		return null;
	}
	
	public List<Order> getOrderHistory (int userId) {
		return userOrderListMap.get(userId);
	}
}