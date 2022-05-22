package application;

import java.net.URL;
import java.util.ResourceBundle;

import internals.Doctor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HomepageController implements Initializable {

	@FXML
    private Label welcomeLabel;

    @FXML
    private Label totalDiagnosticsLabel;

    @FXML
    private Button diagHistButton;

    @FXML
    private Button newDiagButton;

    @FXML
    private Button logoutButton;

    
    @FXML
    private void loadDiagnsticPage(ActionEvent event) {
		URL url = getClass().getResource("DiagnosticPage.fxml");
		Main.switchScene(event, url);
    }
    
    @FXML 
    private void loadPatientsSummary(ActionEvent event) {
		URL url = getClass().getResource("VisitHistoryPage.fxml");
		Main.switchScene(event, url);
    }
    
    @FXML
    private void logout(ActionEvent event) {
    	Main.setConnectedPerson(null);
    	Main.setCurrentPatient(null);;
    	Main.setCurrentVisit(null);;
    	Main.setDiagQuestionsList(null);
    	
    	URL url = getClass().getResource("LoginScene.fxml");
		Main.switchScene(event, url);
    }
 

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		welcomeLabel.setText("Welcome Dr " + Main.getConnectedPerson().getName());
		totalDiagnosticsLabel.setText(((Doctor)Main.getConnectedPerson()).getNumberOfDiagnostics() + "");
	}
}
