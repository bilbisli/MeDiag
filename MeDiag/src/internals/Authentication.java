package internals;
import java.util.Stack;

public interface Authentication {
	public String getUserName();
	public void setUserName(String userName);
	public void setPassword(String password);
	public Stack<String> invalidPasswordErrors();	
	public boolean isValidPassword() ;
	public Stack<String> invalidUsernameErrors();
	public boolean isValidUsername();
	public Stack<String> checkUserValidity();
	public boolean isUserValid();
	public boolean validatePassword(String password);
}
