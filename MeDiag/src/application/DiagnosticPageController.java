package application;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Stack;

import internals.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Tooltip;


public class DiagnosticPageController implements Initializable {

	@FXML
    private TextField wbcField;

    @FXML
    private Label wbcLabel;

    @FXML
    private Tooltip wbcTT;

    @FXML
    private Label rbcLabel;

    @FXML
    private Tooltip rbcTT;

    @FXML
    private TextField rbcField;

    @FXML
    private Label ureaLabel;

    @FXML
    private Tooltip ureaTT;

    @FXML
    private TextField ureaField;

    @FXML
    private Label hbLabel;

    @FXML
    private Tooltip hbTT;

    @FXML
    private TextField hbField;

    @FXML
    private Label crLabel;

    @FXML
    private Tooltip crTT;

    @FXML
    private TextField crField;

    @FXML
    private Label irLabel;

    @FXML
    private Tooltip irTT;

    @FXML
    private TextField irField;

    @FXML
    private Label hdlLabel;

    @FXML
    private Tooltip hdlTT;

    @FXML
    private TextField hdlField;

    @FXML
    private Label aphLabel;

    @FXML
    private Tooltip aphTT;

    @FXML
    private TextField aphField;

    @FXML
    private Slider neutField;

    @FXML
    private Tooltip neutTT;

    @FXML
    private Label neutLabel;

    @FXML
    private Label neutPLabel;

    @FXML
    private Slider lymphField;

    @FXML
    private Label lymphLabel;

    @FXML
    private Tooltip lymphTT;

    @FXML
    private Label lymphPLabel;

    @FXML
    private Slider hctField;

    @FXML
    private Label hctLabel;

    @FXML
    private Tooltip hctTT;

    @FXML
    private Label hctPLabel;

    @FXML
    private TextField nameField;

    @FXML
    private TextField idField;

    @FXML
    private ChoiceBox<Gender> genderField;

    @FXML
    private DatePicker bdField;

    @FXML
    private ChoiceBox<Region> orField;

    @FXML
    private Label errLabel;

    @FXML
    private Button homeButton;

    @FXML
    private Button diagnoseButton;

    @FXML
    private void loadHomePage(ActionEvent event) {
    	URL url = getClass().getResource("HomePage.fxml");
		Main.switchScene(event, url);
    }
    
    @FXML
    private void diagnose(ActionEvent event) {
    	
		Stack<String> errors = new Stack<String>();
    	Main.setCurrentPatient(checkPatient(errors));
		Main.setDiagQuestionsList(new ClinicVisit());
		ClinicVisit visit = checkVisit(Main.getCurrentPatient(), errors, Main.getDiagQuestionsList());

		if (errors.empty()) {
			visit.setDoctor((Doctor)Main.getConnectedPerson());
			Main.getCurrentPatient().addVisit(visit);
			Main.setCurrentVisit(visit);
			if(Main.getDiagQuestionsList().getSymptoms().isEmpty())
				displayDiagnostic(event);
			else
				displayQuestions(event);
		}
		else {
			String err = "";
			for(String error : errors)
				err += error + "\n";
			errLabel.setText(err);
		}
    }
    
    public ClinicVisit checkVisit(Patient p, Stack<String> errors, ClinicVisit diagQuestionsList) {
    	ClinicVisit visit = new ClinicVisit();
		Set<String> symptoms = SystemData.getSymptoms().keySet();
		Stack<String> tempErrors = new Stack<String>();
		
		if(p == null)
			return null;
		
		HashMap<String, String> fields = new  HashMap<String, String>();
		fields.put("WBC", wbcField.getText());
		fields.put("RBC", rbcField.getText());
		fields.put("UREA", ureaField.getText());
		fields.put("HB", hbField.getText());
		fields.put("CR", crField.getText());
		fields.put("IR", irField.getText());
		fields.put("HDL", hdlField.getText());
		fields.put("APH", aphField.getText());
		fields.put("NEUT", "" + neutField.getValue());
		fields.put("LYMPH",  "" +  lymphField.getValue());
		fields.put("HCT", "" + hctField.getValue());
		
		for(String str : symptoms) {
			addSymptom(p, visit, str, fields.get(str), tempErrors, diagQuestionsList);
		}
		
		if(!tempErrors.isEmpty()) {
			String fieldErrors = "";
			tempErrors.add(".");
			for (String str : tempErrors)
				fieldErrors += str;
			errors.add(fieldErrors);
			System.out.println(tempErrors);
		}
		return visit;
	}

	private void addSymptom(Patient p, ClinicVisit visit, String str, String valueString, Stack<String> tempErrors,
			ClinicVisit diagQuestionsList) {
		float value;

		if(!valueString.isBlank()) {
			try {
				value = Float.parseFloat(valueString);
				Symptom symp = SystemData.getSymptoms().get(str);
				ArrayList<Diagnostic> arr = symp.matchDiagnostics(p, value);
				if (!(arr == null || arr.isEmpty())) {
					for (Diagnostic d : arr) {
						if (d.getName().equals("Pregnant") && p.getGender().equals(Gender.MALE))
							break;
						if (d.getQuestions() == null || d.getQuestions().isEmpty())
							visit.addSymptom(symp, value, d);
						else
							diagQuestionsList.addSymptom(symp, value, d);
					}
				}
			} catch (NumberFormatException e) {
				if(!tempErrors.isEmpty()) {
					tempErrors.add(", ");
				}
				else
					tempErrors.add("• Numbers only in fields:\n");
				tempErrors.add(str);
			} catch (NullPointerException er) { System.out.println("empty"); }
		}
	}
	

	private Patient checkPatient(Stack<String> errors) {
    	Gender gender;
		Region region;
		String name, id;
		LocalDate birthday;
		name = nameField.getText();
		if(name.isBlank())
			errors.add("• Patient name is missing.");
		
		id = idField.getText();
		if(id.isBlank())
			errors.add("• Patient ID is missing.");
		else
			User.invalidIdErrors(id, errors);
		
		gender = genderField.getValue();
		
		birthday = null;
		if(bdField != null && bdField.getValue() != null) {
			birthday = bdField.getValue();
			if (birthday.compareTo(LocalDate.now()) > 0) {
				errors.add("• Birth date must not be a future date.");
			}
		}
		else {
			errors.add("• Patient birth date is missing.");
		}
		
		region = orField.getValue();
		if (errors.empty())
			return new Patient(name, id, birthday, gender, region);
		return null;
	}

	private TextFormatter<?> onlyNumbers() {
		DecimalFormat format = new DecimalFormat("#.0");
		return (new TextFormatter<>(c -> {
		    if (c.getControlNewText().isEmpty()) {
		        return c;
		    }
	
		    ParsePosition parsePosition = new ParsePosition(0);
		    Object object = format.parse(c.getControlNewText(), parsePosition);
	
		    if (object == null || parsePosition.getIndex() < c.getControlNewText().length()) {
		        return null;
		    }
		    else {
		        return c;
		    }
		}));
	}
    
    @FXML
    private void neutSliderToolTip() {
    	neutPLabel.setText(String.format("%.0f", neutField.getValue()) + "%");
    }
    
    @FXML
    private void lymphSliderToolTip() {
    	lymphPLabel.setText(String.format("%.0f", lymphField.getValue()) + "%");
    }
    
    @FXML
    private void hctSliderToolTip() {
    	hctPLabel.setText(String.format("%.0f", hctField.getValue()) + "%");
    }
  
	private void displayQuestions(ActionEvent event) {
		URL url = getClass().getResource("FinalizeDiagnostic.fxml");
		Main.switchScene(event, url);
	}

	private void displayDiagnostic(ActionEvent event) {
		URL url = getClass().getResource("DiagnosticResult.fxml");
		Main.switchScene(event, url);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		bdField.setEditable(false);
		bdField.setDayCellFactory(picker -> new DateCell() {
	        public void updateItem(LocalDate date, boolean empty) {
	            super.updateItem(date, empty);
	            LocalDate today = LocalDate.now();

	            setDisable(empty || date.compareTo(today) > 0 );
	        }
	    });
		
		genderField.setValue(Gender.FEMALE);
		genderField.getItems().addAll(Gender.values());
		orField.setValue(Region.ISRAEL);
		orField.getItems().addAll(Region.values());
		
		wbcField.setTextFormatter(onlyNumbers());
		rbcField.setTextFormatter(onlyNumbers());
		ureaField.setTextFormatter(onlyNumbers());
		hbField.setTextFormatter(onlyNumbers());
		crField.setTextFormatter(onlyNumbers());
		irField.setTextFormatter(onlyNumbers());
		hdlField.setTextFormatter(onlyNumbers());
		aphField.setTextFormatter(onlyNumbers());
		
		wbcTT.setText(wbcLabel.getText());
		rbcTT.setText(rbcLabel.getText());
		ureaTT.setText(ureaLabel.getText());
		hbTT.setText(hbLabel.getText());
		crTT.setText(crLabel.getText());
		irTT.setText(irLabel.getText());
		hdlTT.setText(hdlLabel.getText());
		aphTT.setText(aphLabel.getText());
		neutTT.setText(neutLabel.getText());
		lymphTT.setText(lymphLabel.getText());
		hctTT.setText(hctLabel.getText());
		
	}

}
