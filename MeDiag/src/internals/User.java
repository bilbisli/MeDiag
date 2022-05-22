package internals;
import java.io.Serializable;
import java.util.Stack;


public class User implements Authentication, Serializable {

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public static boolean containsLetter(String password) {
		for (char c : password.toCharArray()) {
			if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) return true;
		}
		return false;
	}
	
	public static boolean containsSign(String password) {
		String signs = "<>?~!@#$%^&*";
		for (char c : password.toCharArray()) {
			if (signs.contains("" + c)) return true;
		}
		return false;
	}
	
	public static boolean containsNumber(String password) {
		for (char c : password.toCharArray()) {
			if (c <= '9' && c >= '0') return true;
		}
		return false;
	}
	
	public static boolean containsOnlyNumbers(String str) {
		for (char c : str.toCharArray()) {
			if (!(c <= '9' && c >= '0')) return false;
		}
		return true;
	}
	
	public static Stack<String> invalidPasswordErrors(String password, Stack<String> errors) {
    	if (!containsLetter(password)) {
        	errors.add("• Password needs to contain atleast one letter.");
        }
        if (!containsSign(password)) {
        	errors.add("• Password must contain one special character (<>?~!@#$%^&*).");
        }
        if (!containsNumber(password)) {
        	errors.add("• Password needs to contain atleast one number(0-9).");
        }
        int length = password.length();
        if (length > 10 || length < 8) {
        	errors.add("• Password length needs to be between 8-10 characters.");
        }

        return errors;
    }
	
	public static Stack<String> invalidPasswordErrors(String password) {
        return invalidPasswordErrors(password, new Stack<String>());
    }
	
	public Stack<String> invalidPasswordErrors() {
        return invalidPasswordErrors(password);
    }
	
	 
	public static boolean isValidPassword(String password) {
		return invalidPasswordErrors(password).isEmpty();
	}
	
	public boolean isValidPassword() {
		return isValidPassword(password);
	}
	
	public static Stack<String> invalidUsernameErrors(String username, Stack<String> errors) {
		int length = username.length();
		if (length > 8 || length < 6) {
			errors.add("• Username length must be between 6-8 characters.");
		}
		int digitCount = 0;
		boolean digitFlag = false, letterFlag = false;
		char[] charArr = username.toCharArray();
		for(int i = 0; i < charArr.length; ++i) {
			char c = charArr[i];
			if(c <= '9' && c >= '0')
				++digitCount;
			else if (!letterFlag && !((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
				errors.add("• Username must include numbers and letters only.");
				letterFlag = true;
			}
				
			if(!digitFlag && digitCount > 2) {
				errors.add("• Username cannot have more than two numbers.");
				digitFlag = true;
			}
			if(digitFlag && letterFlag)
				break;
		}
		
		return errors;		
	}
	
	public static Stack<String> invalidUsernameErrors(String username) {
		return invalidUsernameErrors(username, new Stack<String>());	
	}
	
	public Stack<String> invalidUsernameErrors() {
		return invalidUsernameErrors(username, new Stack<String>());	
	}
	
	public static boolean isValidUsername(String username) {
		return invalidUsernameErrors(username).isEmpty();
	}
	
	public boolean isValidUsername() {
		return isValidUsername(username);
	}
	
	public static Stack<String> invalidIdErrors(String id, Stack<String> errors) {
		if(!containsOnlyNumbers(id)) {
			errors.add("• ID must include only numbers.");
		}
		if(id.length() != 9) {
			errors.add("• ID must be of length 9.");
		}
		return errors;
	}
	
	public static Stack<String> invalidIdErrors(String id) {
		return invalidIdErrors(id, new Stack<String>());
	}
	
	public static Stack<String> checkUserValidity(String username, String password, Stack<String> errors) {
		invalidPasswordErrors(password, errors);
		invalidUsernameErrors(username, errors);
		return errors;
	}
	
	public static Stack<String> checkUserValidity(String username, String password) {
		return checkUserValidity(username, password, new Stack<String>());
	}
	
	public Stack<String> checkUserValidity() {
		return checkUserValidity(username, password, new Stack<String>());
	}
	
	public static Stack<String> checkUserValidity(String username, String password, String id, Stack<String> errors) {
		invalidPasswordErrors(password, errors);
		invalidUsernameErrors(username, errors);
		invalidIdErrors(id, errors);
		return errors;
	}
	
	public static Stack<String> checkUserValidity(String username, String password, String id) {
		return checkUserValidity(username, password, id, new Stack<String>());
	}
	
	
	public static boolean isUserValid(String username, String password) {
		return checkUserValidity(username, password).isEmpty();
	}
	
	public boolean isUserValid() {
		return isUserValid(username, password);
	}
	
	public boolean validatePassword(String password) {
		return this.password.equals(password);
	}
}
