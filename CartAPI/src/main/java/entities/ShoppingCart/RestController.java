package entities.ShoppingCart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RestController {
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	ProductRepository productRepo;
	
	/**
	 * 1.-------------------- Login ---------------------
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> validateLogin(@RequestBody LoginCredentials test) {
		System.out.println("Login authentication checking...");

		String email = test.getEmail();
		String password = test.getPassword();

		if (!email.isEmpty()
				&& !password.isEmpty()) {
			if (email.length() > 11 && email.substring(email.length() - 11).equals("@beehyv.com")) {
				System.out.println("{“result”:”Success”}  200 Ok");
				return new ResponseEntity<String>("{'result':'Success'}  200 Ok",HttpStatus.OK);
			}

			System.out.println("{“result”:”failure”}     401");
			// System.out.println(HttpStatus.UNAUTHORIZED);
			return new ResponseEntity<String>("{'result':'failure'}  401", HttpStatus.UNAUTHORIZED);
		}

		System.out.println("{“result”:”failure”}     401");
		return new ResponseEntity<String>("{'result':'failure'}  401", HttpStatus.UNAUTHORIZED);
	}

	/**
	 * 2.-------------------- SignUp ---------------------
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ResponseEntity<Void> validateSignup(@RequestBody SignUpCredentials test) {
		System.out.println("SignUp details checking...");

		String name = test.getName();
		String email = test.getEmail();
		String password = test.getPassword();

		if (!name.isEmpty()
				&& !email.isEmpty()
				&& !password.isEmpty()) {
			if (email.length() > 11 && email.substring(email.length() - 11).equals("@beehyv.com")) {
				System.out.println("{“result”:”Success”}  200 Ok");
				return new ResponseEntity<Void>(HttpStatus.OK);
			}

			System.out.println("{“result”:”failure”}     401");
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}

		System.out.println("{“result”:”failure”}     401");
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	
	/**
	 * 3.-------------------- LogOut ---------------------
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ResponseEntity<Void> logout(@RequestBody LogoutCredentials test) {
		System.out.println("Log out processed..." + test.getUserID());

		if (!test.getUserID().isEmpty()) {
			System.out.println("{“result”:”Success”}  200 Ok");
			return new ResponseEntity<Void>(HttpStatus.OK);
		}

		System.out.println("{“result”:”failure”}     401");
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	/**
	 * 4.-------------------- Get Profile ---------------------
	 */
	@RequestMapping(value = "/getprofile/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getProfileById(@PathVariable("userId") int userId) {
		System.out.println("Fetching user with id " + userId + "...");
		User user = userRepo.getUserById(userId);
		
		if(user == null) {
			System.out.println("User with id " + userId + " not found.");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	/**
	 * 5.-------------------- Update Profile ---------------------
	 */
	@RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
	public ResponseEntity<Void> updateProfile(@RequestBody User user) {
		System.out.println("Update profile of User with ID " + user.getUserID());

		if (!userRepo.isUserPresent(user.getUserID())) {
			System.out.println("{“result”:”failure”}     401");
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
		else {
			userRepo.updateProfile(user);
			System.out.println("{“result”:”Success”}  200 Ok");
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	
	/**
	 * 6.-------------------- Add Product ---------------------
	 */
	@RequestMapping(value = "/products/addProduct", method = RequestMethod.POST)
	public ResponseEntity<ProductWithId> addProduct(@RequestBody Product product) {
		System.out.println("Adding product " + product.getName());
		ProductWithId p = productRepo.addProduct(product);

		return new ResponseEntity<ProductWithId>(p, HttpStatus.OK);
	}
	
	/**
	 * 7.-------------------- Modify Product ---------------------
	 */
	@RequestMapping(value = "/products/update", method = RequestMethod.POST)
	public ResponseEntity<ProductWithId> updateProduct(@RequestBody ProductWithId product) {
		System.out.println("Update product of Product with ID " + product.getProductId());
		productRepo.modifyProduct(product);
		
		return new ResponseEntity<ProductWithId>(product, HttpStatus.OK);
	}
	
	/**
	 * 8.-------------------- Get Product ---------------------
	 */
	@RequestMapping(value = "/products/getById/{productId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductWithId> getProductById(@PathVariable("productId") int productId) {
		System.out.println("Fetching product with id " + productId + "...");
		ProductWithId product = productRepo.getProductById(productId);
		
		return new ResponseEntity<ProductWithId>(product, HttpStatus.OK);
	}
	
	/**
	 * 9.-------------------- Get Products by category ---------------------
	 */
	@RequestMapping(value = "/products/{category}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductWithId>> getProductByCategory(@PathVariable("category") String category) {
		System.out.println("Fetching all products whose category is " + category);
		List<ProductWithId> productList = productRepo.allProductsByCategory(category);
		return new ResponseEntity<List<ProductWithId>>(productList, HttpStatus.OK);
	}
	
	/**
	 * 10.-------------------- Get Products by Search String ---------------------
	 */
	@RequestMapping(value = "/products/search/{searchString}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductWithId>> searchByString(@PathVariable("searchString") String string) {
		System.out.println("Fetching all products that contains string " + string);
		List<ProductWithId> productList = productRepo.getProductBySearching(string);
		return new ResponseEntity<List<ProductWithId>>(productList, HttpStatus.OK);
	}
	
	/**
	 * 11.-------------------- Get Filtered Product by category ---------------------
	 */
	@RequestMapping(value = "/products/{category}/getFilteredProducts", method = RequestMethod.POST)
	public ResponseEntity<List<ProductWithId>> updateFilteredProduct(@RequestBody ProductCategoryFilter filter, @PathVariable("category") String category) {
		System.out.println("Fetching filtered product, Category = " + category);
		List<ProductWithId> products = productRepo.getFilteredProduct(category, filter.getMinPrice(), filter.getMaxPrice());
		
		return new ResponseEntity<List<ProductWithId>>(products, HttpStatus.OK);
	}
	
	/**
	 * 12.-------------------- Get cart ---------------------
	 */
	@RequestMapping(value = "/cart/{userId}/getCart", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cart> getUserCart(@PathVariable("userId") int userId) {
		System.out.println("Fetching cart of user, userID = " + userId);
		Cart cart = userRepo.getCart(userId);
		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	}
	
	/**
	 * 13.-------------------- Get cart item ---------------------
	 */
	@RequestMapping(value = "/cart/{userId}/getCartItem/{cartitemId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CartItem> getCartItem(@PathVariable("userId") int userId, @PathVariable("cartitemId") int itemId) {
		System.out.println("Fetching cart item of user, userID = " + userId + " and cartItemID = " + itemId);
		CartItem cartItem = userRepo.getCartItem(userId, itemId);
		return new ResponseEntity<CartItem>(cartItem, HttpStatus.OK);
	}
	
	/**
	 * 14.-------------------- Add to cart ---------------------
	 */
	@RequestMapping(value = "/cart/{userId}/add/{productId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CartItem> addItemToCart(@PathVariable("userId") int userId, @PathVariable("productId") int pid) {
		System.out.println("Adding item to a user cart, userID = " + userId);
		CartItem cartItem = userRepo.addCartItem(userId, pid);
		
		return new ResponseEntity<CartItem>(cartItem, HttpStatus.OK);
	}
	
	/**
	 * 15.-------------------- Remove from cart ---------------------
	 */
	@RequestMapping(value = "/cart/{userId}/remove/{productId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> removeItem(@PathVariable("userId") int userId, @PathVariable("productId") int pid) {
		System.out.println("Removing item from cart, userID = " + userId);
		String name = userRepo.removeItemFromCart(userId, pid);
		
		return new ResponseEntity<String>(name + " removed from cart", HttpStatus.OK);
	}
	
	/**
	 * 16.-------------------- Change Quantity of product ---------------------
	 */
	@RequestMapping(value = "/cart/{userId}/changeQuantity/{productId}", method = RequestMethod.POST)
	public ResponseEntity<CartItem> changeQuantity(@PathVariable("userId") int uid, @PathVariable("productId") int pid, @RequestBody ProductQuantity pq) {
		System.out.println("Changing quantity of product " + pid);

		CartItem cartItem = userRepo.changeQuantity(uid, pid, pq.getQuantity());
		return new ResponseEntity<CartItem>(cartItem, HttpStatus.OK);
	}
	
	/**
	 * 17.-------------------- Get order history ---------------------
	 */
	@RequestMapping(value = "/order/{userId}/getOrders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Order>> getOrders(@PathVariable("userId") int uid) {
		System.out.println("Fetching order history of user, userID = " + uid);
		List<Order> orders = userRepo.getOrderHistory(uid);
		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
	}
}
