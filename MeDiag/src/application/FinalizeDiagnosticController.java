package application;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import internals.Diagnostic;
import internals.Symptom;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.OverrunStyle;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class FinalizeDiagnosticController implements Initializable {
	private HashMap<String, CheckBox> questions;
	

    @FXML
    private VBox questionPane;

    @FXML
    private Button homeButton;

    @FXML
    private Button diagnoseButton;

    @FXML
    void diagnose(ActionEvent event) {
		for (Symptom symp : Main.getDiagQuestionsList().getSymptoms().keySet()) {
			float value = (float) Main.getDiagQuestionsList().getSymptoms().get(symp).keySet().toArray()[0];
			for (Diagnostic diag : Main.getDiagQuestionsList().getSymptoms().get(symp).get(value))
				for (String q : diag.getQuestions())
					if(questions.get(q).isSelected())
						Main.getCurrentVisit().addSymptom(symp, value, diag);
		}
		displayDiagnostic(event);
    }
    
	private void displayDiagnostic(ActionEvent event) {
		URL url = getClass().getResource("DiagnosticResult.fxml");
		Main.switchScene(event, url);
	}

    @FXML
    void loadHomePage(ActionEvent event) {
    	URL url = getClass().getResource("HomePage.fxml");
		Main.switchScene(event, url);
    }

	public void displayQuestion(CheckBox question) {
		question.setPrefHeight(25);
		question.setPrefWidth(835);
		question.setTextFill(Paint.valueOf("#cae8ff"));
		question.setTextOverrun(OverrunStyle.CENTER_WORD_ELLIPSIS);
		question.setFont(new Font(18));
		questionPane.getChildren().add(question);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		questions = new HashMap<String, CheckBox>();
		
		for (Symptom symp : Main.getDiagQuestionsList().getSymptoms().keySet()) {
			float value = (float) Main.getDiagQuestionsList().getSymptoms().get(symp).keySet().toArray()[0];
			for (Diagnostic diag : Main.getDiagQuestionsList().getSymptoms().get(symp).get(value))
				for (String q : diag.getQuestions())
					questions.put(q, new CheckBox(q));
		}
		for (String question : questions.keySet())
			displayQuestion(questions.get(question));
	}
}
