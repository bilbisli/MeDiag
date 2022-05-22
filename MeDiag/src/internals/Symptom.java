package internals;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


public class Symptom implements Serializable {

	private static final long serialVersionUID = 1L;
	public String name;
	public String description;
	private ArrayList<Profile> profiles;
	private HashMap<Level, ArrayList<Diagnostic>> optionalDiagnostics = null;
	
	
	public Symptom(String name, String description, ArrayList<Profile> profiles) {
		this.name = name;
		this.description = description;
		this.setProfiles(profiles);
	}
	
	public Symptom(String name, String description, ArrayList<Profile> profiles, HashMap<Level, ArrayList<Diagnostic>> optionalDiagnostics) {
		this.name = name;
		this.description = description;
		this.setProfiles(profiles);
		this.setOptionalDiagnostics(optionalDiagnostics);
	}
	
	
	public Symptom(String name, String description, ArrayList<Profile> profiles, ArrayList<Diagnostic> lowDiagnostics, ArrayList<Diagnostic> highDiagnostics) {
		this.name = name;
		this.description = description;
		this.setProfiles(profiles);
		addDiagnostics(lowDiagnostics, highDiagnostics);
	}
	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	

	public ArrayList<Profile> getProfiles() {
		return profiles;
	}
	
	
	public void setProfiles(ArrayList<Profile> profiles) {
		this.profiles = profiles;
	}


	@Override
	public String toString() {
		return "Symptom [name=" + name + ", description=" + description + ", profiles=" + profiles
				+ ", optionalDiagnostics=" + optionalDiagnostics + "]";
	}

	public HashMap<Level, ArrayList<Diagnostic>> getOptionalDiagnostics() {
		return optionalDiagnostics;
	}

	public void setOptionalDiagnostics(HashMap<Level, ArrayList<Diagnostic>> optionalDiagnostics) {
		this.optionalDiagnostics = optionalDiagnostics;
	}
	
	
	public Profile getMatchingProfile(Profile profile) {
		for(Profile mp : getProfiles()) {
			if(mp.equals(profile)) {
				return mp;
			}
		}
		return null;
	}
	
	public Level getLevel(Range range, float value) {	
		if (range.isInRange(value)) {
			return Level.NORMAL;
		}
		else if(range.getDeviation(value) > 0) {
			return Level.HIGH;
		}
		return Level.LOW;
	}
	
	public Level getLevel(Profile profile, float value) {
			if(profile == null)
				return null;
			return getLevel(((MedicalProfile)profile).getValueRange(), value);
	}
	
	public ArrayList<Diagnostic> matchDiagnostics(Profile profile, float value) {
		return optionalDiagnostics.get(getLevel(getMatchingProfile(profile), value));
	}
	
	public void addDiagnostics(ArrayList<Diagnostic> lowDiagnostics, ArrayList<Diagnostic> highDiagnostics) {
		HashMap<Level, ArrayList<Diagnostic>> diagnostics = new HashMap<Level, ArrayList<Diagnostic>>();
		diagnostics.put(Level.LOW, lowDiagnostics);
		diagnostics.put(Level.HIGH, highDiagnostics);
		this.setOptionalDiagnostics(diagnostics);
	}
	
}
