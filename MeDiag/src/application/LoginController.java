package application;


import java.net.URL;
import java.util.Map.Entry;
import java.util.Stack;

import internals.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class LoginController {

    @FXML
    private Label errorLabel;

    @FXML
    private TextField userField;

    @FXML
    private TextField idField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;
    
    
    private boolean fieldsFull() {
    	String errors = "";
    	if(userField.getText().isEmpty())
    		errors += "• Username field is empty.\n";
		if(idField.getText().isEmpty())
			errors += "• ID field is empty.\n";
		if(passwordField.getText().isEmpty())
			errors += "• Password field is empty.\n";
		errorLabel.setText(errors);
		return errors.isEmpty();
	}
    
    
    @FXML
	private void isValidLogin(ActionEvent event) {

		if(!fieldsFull())
			return;
		
		boolean idFlag = true;
		String errors = "";
		Profile profile = null;
		String username = userField.getText();
		String password = passwordField.getText();
		String id = idField.getText();
		
		Stack<String> lst = User.checkUserValidity(username, password, id);
		if(!lst.isEmpty()) {
			for(String err : lst)
				errors += err + "\n";
		}
		else {
		
			for (Entry<String, Authentication> user : SystemData.getUsers().entrySet()) {
				if (user.getValue().getUserName().equals(username)) {
					profile = (Profile) user.getValue();
					break;
				}
			}
			
			if (!SystemData.getUsers().containsKey(id)) {
				errors += "• Wrong ID.\n";
				idFlag = false;
			}
			
			if (profile == null) {
				errors += "• Wrong username.\n";
			} 
			else if (idFlag && !((Person) profile).getId().equals(id)) {
				errors += "• Username and ID don't match.\n";
			} 
			else if (errors.equals("") && ((Authentication) profile).validatePassword(password)) {
				login(profile, event);
			} 
			else if (idFlag)
				errors += "• Wrong password.\n";
		}
		
		errorLabel.setText(errors);
	}


	private void login(Profile p, ActionEvent event) {
		Main.setConnectedProfile(p);
		URL url = getClass().getResource("HomePage.fxml");
		Main.switchScene(event, url);
	}
}
