package internals;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Patient extends Person implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<ClinicVisit> visits = new ArrayList<ClinicVisit>();

	public Patient(String name, String id, LocalDate birthDate, Gender gender, String title, Region originCountry) {
		super(name, id, birthDate, gender, title, originCountry);
		setVisits(visits);
	}

	public Patient(String name, String id, LocalDate birthDate, Gender gender, String title) {
		super(name, id, birthDate, gender, title);
		setVisits(visits);
	}

	public Patient(String name, String id, LocalDate birthDate, Gender gender) {
		super(name, id, birthDate, gender);
		setVisits(visits);
	}
	
	public Patient(String name, String id, LocalDate birthDate, Gender gender, Region originCountry) {
		super(name, id, birthDate, gender, originCountry);
		setVisits(visits);
	}
	
	public Patient(String name, String id, LocalDate birthDate, Gender gender, String title, Region originCountry, ArrayList<ClinicVisit> visits) {
		super(name, id, birthDate, gender, title, originCountry);
		setVisits(visits);
	}

	public Patient(String name, String id, LocalDate birthDate, Gender gender, String title, ArrayList<ClinicVisit> visits) {
		super(name, id, birthDate, gender, title);
		setVisits(visits);
	}

	public Patient(String name, String id, LocalDate birthDate, Gender gender, ArrayList<ClinicVisit> visits) {
		super(name, id, birthDate, gender);
		setVisits(visits);
	}

	public ArrayList<ClinicVisit> getVisits() {
		return visits;
	}

	public void setVisits(ArrayList<ClinicVisit> visits) {
		this.visits = visits;
	}
	
	public void addVisit(ClinicVisit visit) {
		visit.setPatient(this);
		visits.add(visit);
	}
	
}
