package entities.ShoppingCart;

public class LoginCredentials {
	private String email;
	private String password;
	
	public String getEmail() {
		if(email != null)
			return email;
		return "";
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		if(password != null)
			return password;
		return "";
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
