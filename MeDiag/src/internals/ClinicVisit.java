package internals;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;


public class ClinicVisit implements Serializable {
	private static final long serialVersionUID = 1L;
	LocalDateTime visitTime;
	Patient patient;
	Doctor doctor;
	HashMap<Symptom, HashMap<Float, ArrayList<Diagnostic>>> symptoms =  
			new HashMap<Symptom, HashMap<Float, ArrayList<Diagnostic>>>();
	
	public ClinicVisit() {
		super();
		setTime();
	}
	
	public ClinicVisit(Patient patient) {
		super();
		setTime();
		this.patient = patient;
	}
	
	
	public ClinicVisit(LocalDateTime visitTime) {
		super();
		this.visitTime = visitTime;
	}
	
	
	public ClinicVisit(LocalDateTime visitTime, Patient patient) {
		super();
		this.visitTime = visitTime;
		this.patient = patient;
	}


	public ClinicVisit(HashMap<Symptom, HashMap<Float, ArrayList<Diagnostic>>> symptoms) {
		super();
		setTime();
		this.symptoms = symptoms;
	}
	
	
	public ClinicVisit(Patient patient, HashMap<Symptom, HashMap<Float, ArrayList<Diagnostic>>> symptoms) {
		super();
		setTime();
		this.symptoms = symptoms;
		this.patient = patient;
	}


	public ClinicVisit(LocalDateTime visitTime, HashMap<Symptom, HashMap<Float, ArrayList<Diagnostic>>> symptoms) {
		super();
		this.visitTime = visitTime;
		this.symptoms = symptoms;
	}
	
	
	public ClinicVisit(LocalDateTime visitTime, Patient patient, HashMap<Symptom, HashMap<Float, ArrayList<Diagnostic>>> symptoms) {
		super();
		this.visitTime = visitTime;
		this.symptoms = symptoms;
	}


	public LocalDateTime getVisitTime() {
		return visitTime;
	}


	public void setVisitTime(LocalDateTime visitTime) {
		this.visitTime = visitTime;
	}


	private void setTime() {
		visitTime = LocalDateTime.now();
	}

	public HashMap<Symptom, HashMap<Float, ArrayList<Diagnostic>>> getSymptoms() {
		return symptoms;
	}


	public void setSymptoms(HashMap<Symptom, HashMap<Float, ArrayList<Diagnostic>>> symptoms) {
		this.symptoms = symptoms;
	}
	
	public void addSymptom(Symptom symptom, HashMap<Float, ArrayList<Diagnostic>> diagnostic) {
		symptoms.put(symptom, diagnostic);
	}
	
	public void addSymptom(Symptom symptom, float value) {
		HashMap<Float, ArrayList<Diagnostic>> diagnostic = new HashMap<Float, ArrayList<Diagnostic>>();
		diagnostic.put(value, null);
		symptoms.put(symptom, diagnostic);
	}
	
	public void addSymptom(Symptom symptom, float value, Diagnostic diagnostic) {
		if(!symptoms.containsKey(symptom))
			symptoms.put(symptom,  new HashMap<Float, ArrayList<Diagnostic>>());
		if(!symptoms.get(symptom).containsKey(value))
			symptoms.get(symptom).put(value, new ArrayList<Diagnostic>());
		symptoms.get(symptom).get(value).add(diagnostic);
	}
	
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	@Override
	public String toString() {
		String str = "Patient: " + patient + "\nVisit Time: " + "date - " + visitTime.toLocalDate() + " time - " + visitTime.toLocalTime() + "\nDoctor: " + doctor.getName() + "\nSymptoms:\n";
		for (Symptom symp : getSymptoms().keySet()) {
			float value = (float)getSymptoms().get(symp).keySet().toArray()[0];
			str += "\t" + symp.getName() + ": " + value + "\n\t\tOptional Diagnostics:\n";
			int i = 1;
			for(Diagnostic diag : getSymptoms().get(symp).get(value))
				str += "\t\t\t" + i++ + "." + diag.getName() + "\n\t\t\t" + "Treatment: " + diag.getOptionalTreatments() + "\n";
		}
		return str;
	}
}
