package application;
	
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import internals.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class Main extends Application {
	public final String appName = "MedDiag";
	private static Person connectedPerson;
	private static Patient currentPatient;
	private static ClinicVisit currentVisit;
	private static ClinicVisit diagQuestionsList;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
			Scene scene = new Scene(root);
			Image appIcon = new Image("/static/appIcon.png");
			new SystemData();
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.setTitle(appName);
			primaryStage.getIcons().add(appIcon);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static void switchScene(ActionEvent event, URL url) {
		Parent root;
		try {
			root = FXMLLoader.load(url);
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	public static void setConnectedProfile(Profile p) {
		setConnectedPerson((Person)p);
	}

	public static ClinicVisit getDiagQuestionsList() {
		return diagQuestionsList;
	}

	public static void setDiagQuestionsList(ClinicVisit diagQuestionsList) {
		Main.diagQuestionsList = diagQuestionsList;
	}

	public static Person getConnectedPerson() {
		return connectedPerson;
	}

	public static void setConnectedPerson(Person connectedPerson) {
		Main.connectedPerson = connectedPerson;
	}

	public static ClinicVisit getCurrentVisit() {
		return currentVisit;
	}

	public static void setCurrentVisit(ClinicVisit currentVisit) {
		Main.currentVisit = currentVisit;
	}

	public static Patient getCurrentPatient() {
		return currentPatient;
	}

	public static void setCurrentPatient(Patient currentPatient) {
		Main.currentPatient = currentPatient;
	}
	
	public static void saveData() {
		BufferedWriter visitOut;
		String subPath = "Data/DiagnosticsData/Doctor_" + Main.getConnectedPerson().getId() + "/Patient_" + Main.getCurrentPatient().getId() + "/";
		String file_name = subPath + "Patient_" + Main.getCurrentPatient().getId() + "_" + Main.getCurrentVisit().getVisitTime().toString().replace(":", "-") + ".txt";
		
		try {
			Path path = Paths.get(subPath);
			Files.createDirectories(path);
			FileWriter theFile = new FileWriter(file_name);
			visitOut = new BufferedWriter(theFile);
			visitOut.write(Main.getCurrentVisit().toString());
			visitOut.close();
			FileOutputStream usersData = new FileOutputStream("Data/Users.dat");
			ObjectOutputStream doctorOut = new ObjectOutputStream(usersData);
			for(String docId : SystemData.getUsers().keySet()) {
				doctorOut.writeObject(SystemData.getUsers().get(docId));
			}
			doctorOut.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
