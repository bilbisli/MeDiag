package application;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.ResourceBundle;

import internals.ClinicVisit;
import internals.Doctor;
import internals.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class VisitHistoryController implements Initializable {

    @FXML
    private TableView<ClinicVisit> table;

    @FXML
    private TableColumn<ClinicVisit, LocalDateTime> timeColumn;

    @FXML
    private TableColumn<ClinicVisit, Patient> patientColumn;

    @FXML
    private Button homeButton;

    @FXML
    void loadHomePage(ActionEvent event) {
    	URL url = getClass().getResource("HomePage.fxml");
		Main.switchScene(event, url);
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		timeColumn.setCellValueFactory(new PropertyValueFactory<>("visitTime"));
		patientColumn.setCellValueFactory(new PropertyValueFactory<>("patient"));
		table.setItems(getVisits());
	}
	
	public ObservableList<ClinicVisit> getVisits() {
		ObservableList<ClinicVisit> visits = FXCollections.observableArrayList();
		HashSet<ClinicVisit> doctorsVisits = new HashSet<ClinicVisit>();
		for (String patientId : ((Doctor)Main.getConnectedPerson()).getPatients().keySet()) {
			for(ClinicVisit visit : ((Doctor)Main.getConnectedPerson()).getPatients().get(patientId).getVisits()) {
				doctorsVisits.add(visit);
			}
		}
		visits.addAll(doctorsVisits);
		return visits;
	}
}
