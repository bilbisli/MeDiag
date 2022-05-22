package internals;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Stack;


public class Person extends Profile implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private String id;
	private String title;


	public Person(String name, String id, LocalDate birthDate, Gender gender) {
		super();
		this.name = name;
		this.id = id;
		this.birthDate = birthDate;
		this.setGender(gender);
	}
	
	public Person(String name, String id, LocalDate birthDate, Gender gender, String title) {
		super();
		this.name = name;
		this.id = id;
		this.birthDate = birthDate;
		this.title = title;
		this.setGender(gender);
		this.setBirthDate(birthDate);
	}

	public Person(String name, String id, LocalDate birthDate, Gender gender, String title, Region originCountry) {
		super();
		this.name = name;
		this.id = id;
		this.birthDate = birthDate;
		this.title = title;
		this.setGender(gender);
		this.setOriginCountry(originCountry);
	}

		public Person(String name, String id, LocalDate birthDate, Gender gender, Region originCountry) {
		super();
		this.name = name;
		this.id = id;
		this.birthDate = birthDate;
		this.setGender(gender);
		this.setOriginCountry(originCountry);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "name=" + name + ", id=" + id + ", title=" + title + ", gender=" + gender + ", origin region="
				+ originCountry + ", age=" + getAge();
	}
	
	public Stack<String> invalidIdErrors() {
		return User.invalidIdErrors(id, new Stack<String>());
	}

}
