package internals;
import java.time.LocalDate;
import java.time.Period;


public class Profile {

	protected Gender gender = null;
	protected Region originCountry = null;
	protected LocalDate birthDate = null;
	// for medical diagnostic profile
//	private Range ageRange = null;
//	private Range valueRange = null;

	
	public Profile() {
		super();
	}

	
	public Profile(Gender gender) {
		super();
		this.gender = gender;
	}


	public Profile(Region originCountry) {
		super();
		this.originCountry = originCountry;
	}


	public Profile(Gender gender, Region originCountry) {
		super();
		this.gender = gender;
		this.originCountry = originCountry;
	}


	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Region getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(Region originCountry) {
		this.originCountry = originCountry;
	}

//	public Range getAgeRange() {
//		return ageRange;
//	}
//
//	public void setAgeRange(Range ageRange) {
//		this.ageRange = ageRange;
//	}
//	
//	public Range getValueRange() {
//		return valueRange;
//	}
//
//	public void setValueRange(Range valueRange) {
//		this.valueRange = valueRange;
//	}
	
	public LocalDate getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public double getAge() {
		if(birthDate == null)
			return 0;
		return Period.between(birthDate, LocalDate.now()).getYears();
	}
	

//	@Override
//	public boolean equals(Object obj) {
//
//		if(getGender() != null && getGender() != ((Profile)obj).getGender()) {
//			return false;
//		}
//		else if (getOriginCountry() != null && getOriginCountry() != ((Profile)obj).getOriginCountry()) {
//			return false;
//		}
//		else if (getAgeRange() != null && !(getAgeRange().isInRange(((Profile)obj).getAge()))) {
//			return false;
//		}
//		return true;
//	}

}
