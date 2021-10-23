package entities.ShoppingCart;

public class LogoutCredentials {
	private String userID;

	public String getUserID() {
		if(userID != null)
			return userID;
		return "";
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
}
