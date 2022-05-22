package application;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;

import internals.Diagnostic;
import internals.Symptom;
import internals.Doctor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DiagnosticResultController implements Initializable {
	

    @FXML
    private TableView<Diagnostic> table;

    @FXML
    private TableColumn<Diagnostic, String> diagColumn;

    @FXML
    private TableColumn<Diagnostic, String> treatColumn;

    @FXML
    private Label nameLabel;

    @FXML
    private Label idLabel;

    @FXML
    private Label genderLabel;

    @FXML
    private Label bdLabel;

    @FXML
    private Label orLabel;

    @FXML
    private Button homeButton;

    @FXML
    void loadHomePage(ActionEvent event) {
    	URL url = getClass().getResource("HomePage.fxml");
		Main.switchScene(event, url);
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		((Doctor)Main.getConnectedPerson()).addPatient(Main.getCurrentPatient());
		Main.saveData();
		
		nameLabel.setText(Main.getCurrentPatient().getName());
		idLabel.setText(Main.getCurrentPatient().getId());
		genderLabel.setText(Main.getCurrentPatient().getGender().toString());
		bdLabel.setText(Main.getCurrentPatient().getBirthDate().toString());
		orLabel.setText(Main.getCurrentPatient().getOriginCountry().toString());
		diagColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		treatColumn.setCellValueFactory(new PropertyValueFactory<>("optionalTreatments"));
		table.setItems(getDiagnostics());
	}
	
	public ObservableList<Diagnostic> getDiagnostics() {
		ObservableList<Diagnostic> diagnostics = FXCollections.observableArrayList();
		HashSet<Diagnostic> tempDiagnostics = new HashSet<Diagnostic>();
		for (Symptom symp : Main.getCurrentVisit().getSymptoms().keySet()) {
			float value = (float) Main.getCurrentVisit().getSymptoms().get(symp).keySet().toArray()[0];
			for (Diagnostic diag : Main.getCurrentVisit().getSymptoms().get(symp).get(value))
				tempDiagnostics.add(diag);
		}
		diagnostics.addAll(tempDiagnostics);
		return diagnostics;
	}
}
