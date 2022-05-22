package internals;

import java.io.Serializable;

public class MedicalProfile extends Profile implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Range ageRange = null;
	private Range valueRange = null;
	
	public MedicalProfile() {
		super();
	}

	
	public MedicalProfile(Range valueRange) {
		super();
		this.valueRange = valueRange;
	}


	public MedicalProfile(Gender gender, Range valueRange) {
		super(gender);
		this.valueRange = valueRange;
	}


	public MedicalProfile(Region originCountry, Range valueRange) {
		super(originCountry);
		this.valueRange = valueRange;
	}


	public MedicalProfile(Range ageRange, Range valueRange) {
		super();
		this.ageRange = ageRange;
		this.valueRange = valueRange;
	}


	public MedicalProfile(Gender gender, Range ageRange, Range valueRange) {
		super(gender);
		this.ageRange = ageRange;
		this.valueRange = valueRange;
	}


	public MedicalProfile(Gender gender, Region originCountry, Range valueRange) {
		super(gender, originCountry);
		this.valueRange = valueRange;
	}
	

	public MedicalProfile(Region originCountry, Range ageRange, Range valueRange) {
		super(originCountry);
		this.ageRange = ageRange;
		this.valueRange = valueRange;
	}
	

	public MedicalProfile(Gender gender, Region originCountry, Range ageRange, Range valueRange) {
		super(gender, originCountry);
		this.ageRange = ageRange;
		this.valueRange = valueRange;
	}
	
	public Range getAgeRange() {
		return ageRange;
	}

	public void setAgeRange(Range ageRange) {
		this.ageRange = ageRange;
	}
	
	public Range getValueRange() {
		return valueRange;
	}

	public void setValueRange(Range valueRange) {
		this.valueRange = valueRange;
	}

	@Override
	public boolean equals(Object obj) {
		if(getGender() != null && getGender() != ((Profile)obj).getGender()) {
			return false;
		}
		else if (getOriginCountry() != null && getOriginCountry() != ((Profile)obj).getOriginCountry()) {
			return false;
		}
		else if (getAgeRange() != null && !(getAgeRange().isInRange(((Profile)obj).getAge()))) {
			return false;
		}
		return true;
	}


	@Override
	public String toString() {
		return "MedicalProfile [ageRange=" + ageRange + ", valueRange=" + valueRange + ", gender=" + gender
				+ ", originCountry=" + originCountry + "]";
	}
	
	

}
