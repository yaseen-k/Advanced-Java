package entities.ShoppingCart;

public class SignUpCredentials {
	private String name;
	private String email;
	private String password;
	
	public String getName() {
		if(name != null)
			return name;
		return "";
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
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
