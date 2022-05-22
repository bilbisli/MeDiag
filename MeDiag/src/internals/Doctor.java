package internals;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Stack;

public class Doctor extends Person implements Authentication, Serializable  {

	private static final long serialVersionUID = 1L;
	private int numberOfDiagnostics = 0;
	private HashMap<String, Patient> patients = new HashMap<String, Patient>();
	private User user;
	
	
	
	public Doctor(String name, String id, LocalDate birthDate, Gender gender, String title, Region originCountry,  String username, String password) {
		super(name, id, birthDate, gender, title, originCountry);
		user = new User(username, password);
	}


	public Doctor(String name, String id, LocalDate birthDate, Gender gender, String title,  String username, String password) {
		super(name, id, birthDate, gender, title);
		user = new User(username, password);
	}


	public Doctor(String name, String id, LocalDate birthDate, Gender gender, String username, String password) {
		super(name, id, birthDate, gender);
		user = new User(username, password);
	}


	public String getUserName() {
		return user.getUserName();
	}


	public void setUserName(String userName) {
		this.user.setUserName(userName);
	}


	public HashMap<String, Patient> getPatients() {
		return patients;
	}


	public void setPatients(HashMap<String, Patient> patients) {
		this.patients = patients;
	}


	public void setNumberOfDiagnostics(int numberOfDiagnostics) {
		this.numberOfDiagnostics = numberOfDiagnostics;
	}


	public int getNumberOfDiagnostics() {
		return numberOfDiagnostics;
	}


	public void diagnosticMade(Patient p) {
		++numberOfDiagnostics;
	}


	public String authPassword(String password) {
		return password;
	}

	
	public void setPassword(String password) {
		this.user.setPassword(password);
	}


	@Override
	public Stack<String> invalidPasswordErrors() {
		return this.user.invalidPasswordErrors();
	}


	@Override
	public boolean isValidPassword() {
		return this.user.isValidPassword();
	}


	@Override
	public Stack<String> invalidUsernameErrors() {
		return this.user.invalidUsernameErrors();
	}


	@Override
	public boolean isValidUsername() {
		return this.user.isValidUsername();
	}


	@Override
	public Stack<String> checkUserValidity() {
		return this.user.checkUserValidity();
	}


	@Override
	public boolean isUserValid() {
		return this.user.isUserValid();
	}


	@Override
	public boolean validatePassword(String password) {
		return user.validatePassword(password);
	}
	
	public boolean isRevisit(String patientID) {
		return patients.containsKey(patientID);
	}
	
	public boolean isRevisit(Patient p) {
		return isRevisit(p.getId());
	}
	
	public void addPatient(Patient p) {
		if (isRevisit(p))
			patients.get(p.getId()).getVisits().addAll(p.getVisits());
		else
			patients.put(p.getId(), p);
		++numberOfDiagnostics;
	}
}
