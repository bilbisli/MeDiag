package internals;
import java.io.Serializable;
import java.util.ArrayList;

public class Diagnostic implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String optionalTreatments = "";
	private ArrayList<String> questions = null;
	
	
	public Diagnostic(String name) {
		this.setName(name);
	}
	
	
	public Diagnostic(String name, String optionalTreatment) {
		this.setName(name);
		this.setOptionalTreatments(optionalTreatment);
	}
	
	
	public Diagnostic(String name, String optionalTreatment, ArrayList<String> questions) {
		this.setName(name);
		this.setOptionalTreatments(optionalTreatment);
		this.setQuestions(questions);
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getOptionalTreatments() {
		return optionalTreatments;
	}


	public void setOptionalTreatments(String optionalTreatment) {
		this.optionalTreatments = optionalTreatment;
	}


	public ArrayList<String> getQuestions() {
		return questions;
	}


	public void setQuestions(ArrayList<String> questions) {
		this.questions = questions;
	}


	@Override
	public String toString() {
		return "Diagnostic [name=" + name + ", optionalTreatments=" + optionalTreatments + ", questions=" + questions
				+ "]";
	}
	
}
