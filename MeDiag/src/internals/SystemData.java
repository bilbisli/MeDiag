package internals;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OptionalDataException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;


public class SystemData {
	final static int OLDEST_PERSON = 200;
	private static HashMap<String, Symptom> symptoms = new HashMap<String, Symptom>();
	static HashMap<String, Diagnostic> diagnostics = new HashMap<String, Diagnostic>();
	private static HashMap<String, Authentication> users = new HashMap<String, Authentication>();

	public SystemData() {
		createSystems();
	}
	
	
	public void createSystems() {
		addUsersData("Data/Users.dat");
		addDiagnostics();
		addSymptoms();
	}
	
	private void addUsersData(String file_name) {
		ObjectInputStream usersIn = null;
		try {
			FileInputStream theFile = new FileInputStream(file_name);
			usersIn = new ObjectInputStream(theFile);
			Doctor doc = null;
			while ((doc = (Doctor)(usersIn.readObject())) != null) {
				getUsers().put(doc.getId(), doc);
			}
			usersIn.close();
		}
		catch (OptionalDataException r) {}
		catch(IOException e){
			if(usersIn == null)
			{
				Doctor d1 = new Doctor("ofir golan", "123456780", LocalDate.of(1996, 2, 26), Gender.MALE, "Dr", "ofirg12", "medic123$");
				Doctor d2 = new Doctor("israel avihail", "123456789", LocalDate.of(1992, 7, 13), Gender.MALE, "Dr", "bilbisli", "medic12#");
				getUsers().put(d1.getId(), d1);
				getUsers().put(d2.getId(), d2);
			}
		}
		catch (ClassNotFoundException e) {}
		
		
		

		

		
	}
	
	private static void addDiagnostics() {
		String name;
		String question;
		String optionalTreatments;
		ArrayList<String> questions;
		Diagnostic diagnostic;
		
		// add Anemia data (treatment + confirmation questions)
		name = "Anemia";
		optionalTreatments = "two pills of B12 a day for a month";
		diagnostic = new Diagnostic(name, optionalTreatments);
		diagnostics.put(name, diagnostic);
		
		// add Diet data (treatment + confirmation questions)
		name = "Diet";
		optionalTreatments = "schedule an appointment with a nutritionist";
		diagnostic = new Diagnostic(name, optionalTreatments);
		diagnostics.put(name, diagnostic);
		
		// add Bleeding data (treatment + confirmation questions)
		questions = new ArrayList<String>();
		name = "Bleeding";
		optionalTreatments = "evacuate to hospital ASAP";
		question = "do you suffer from bleeding";
		questions.add(question);
		diagnostic = new Diagnostic(name, optionalTreatments, questions);
		diagnostics.put(name, diagnostic);
		
		// add Hyperlipidemia data (treatment + confirmation questions)
		name = "Hyperlipidemia";
		optionalTreatments = "schedule an appointment with a nutritionist + 5 mg pill of Simobil daily for a week";
		diagnostic = new Diagnostic(name, optionalTreatments);
		diagnostics.put(name, diagnostic);
		
		// add Blood cell formation data (treatment + confirmation questions)
		name = "Blood cell formation disorder";
		optionalTreatments = "10 mg pill of B12 a day for a month + 5 mg pill of folic acid a day for a month";
		diagnostic = new Diagnostic(name, optionalTreatments);
		diagnostics.put(name, diagnostic);
		
		// add Hematological disorder data (treatment + confirmation questions)
		name = "Hematological disorder";
		optionalTreatments = "prescribe injection of hormone to encourage red blood cell production";
		diagnostic = new Diagnostic(name, optionalTreatments);
		diagnostics.put(name, diagnostic);
		
		// add Iron poisoning disorder data (treatment + confirmation questions)
		name = "Iron poisoning";
		optionalTreatments = "evacuate to hospital ASAP";
		diagnostic = new Diagnostic(name, optionalTreatments);
		diagnostics.put(name, diagnostic);
		
		// add Dehydration data (treatment + confirmation questions)
		name = "Dehydration";
		optionalTreatments = "complete rest while lying down + returning fluids while drinking";
		diagnostic = new Diagnostic(name, optionalTreatments);
		diagnostics.put(name, diagnostic);
		
		// add Infection data (treatment + confirmation questions)
		name = "Infection";
		optionalTreatments = "prescribe appropriate antibiotics";
		diagnostic = new Diagnostic(name, optionalTreatments);
		diagnostics.put(name, diagnostic);
		
		// add Vitamin deficiency data (treatment + confirmation questions)
		name = "Vitamin deficiency";
		optionalTreatments = "referral to a blood test to identify the missing vitamins";
		diagnostic = new Diagnostic(name, optionalTreatments);
		diagnostics.put(name, diagnostic);
		
		// add Viral disease data (treatment + confirmation questions)
		name = "Viral disease";
		optionalTreatments = "rest at home";
		diagnostic = new Diagnostic(name, optionalTreatments);
		diagnostics.put(name, diagnostic);
		
		// add Diseases of the biliary tract data (treatment + confirmation questions)
		name = "Diseases of the biliary tract";
		optionalTreatments = "referral to surgical treatment";
		diagnostic = new Diagnostic(name, optionalTreatments);
		diagnostics.put(name, diagnostic);
		
		// add Heart diseases data (treatment + confirmation questions)
		name = "Heart diseases";
		optionalTreatments = "schedule an appointment with a nutritionist";
		diagnostic = new Diagnostic(name, optionalTreatments);
		diagnostics.put(name, diagnostic);
		
		// add Blood disease data (treatment + confirmation questions)
		name = "Blood disease";
		optionalTreatments = "a combination of cyclophosphamide and corticosteroids";
		diagnostic = new Diagnostic(name, optionalTreatments);
		diagnostics.put(name, diagnostic);
		
		// add Liver disease data (treatment + confirmation questions)
		name = "Liver disease";
		optionalTreatments = "referral to a specific diagnosis for the purpose of determining treatment";
		diagnostic = new Diagnostic(name, optionalTreatments);
		diagnostics.put(name, diagnostic);
		
		// add Kidney disease data (treatment + confirmation questions)
		name = "Kidney disease";
		optionalTreatments = "balance blood sugar levels";
		diagnostic = new Diagnostic(name, optionalTreatments);
		diagnostics.put(name, diagnostic);
		
		// add Iron deficiency data (treatment + confirmation questions)
		name = "Iron deficiency";
		optionalTreatments = "two 10 mg pills of B12 a day for a month";
		diagnostic = new Diagnostic(name, optionalTreatments);
		diagnostics.put(name, diagnostic);
		
		// add Muscle diseases data (treatment + confirmation questions)
		name = "Muscle diseases";
		optionalTreatments = "two 5 mg pills of Altman c3 turmeric a day for a month";
		diagnostic = new Diagnostic(name, optionalTreatments);
		diagnostics.put(name, diagnostic);
		
		// add Smoker data (treatment + confirmation questions)
		questions = new ArrayList<String>();
		name = "Smoker";
		optionalTreatments = "don't smoke";
		question = "do you smoke";
		questions.add(question);
		diagnostic = new Diagnostic(name, optionalTreatments, questions);
		diagnostics.put(name, diagnostic);
		
		// add Lung disease data (treatment + confirmation questions)
		name = "Lung disease";
		optionalTreatments = "don't smoke + stay away from smokers + referral to X-ray of the lungs";
		diagnostic = new Diagnostic(name, optionalTreatments);
		diagnostics.put(name, diagnostic);
		
		// add Overactive thyroid gland data (treatment + confirmation questions)
		name = "Overactive thyroid gland";
		optionalTreatments = "propylthiouracil to reduce thyroid activity";
		diagnostic = new Diagnostic(name, optionalTreatments);
		diagnostics.put(name, diagnostic);
		
		// add Adult diabetes gland data (treatment + confirmation questions)
		name = "Adult diabetes";
		optionalTreatments = "insulin adjustment for the patient";
		diagnostic = new Diagnostic(name, optionalTreatments);
		diagnostics.put(name, diagnostic);
		
		// add Cancer data (treatment + confirmation questions)
		name = "Cancer";
		optionalTreatments = "entrectinib";
		diagnostic = new Diagnostic(name, optionalTreatments);
		diagnostics.put(name, diagnostic);
		
		// add Carnivore data (treatment + confirmation questions)
		questions = new ArrayList<String>();
		name = "Carnivore";
		optionalTreatments = "schedule an appointment with a nutritionist";
		question = "do you eat a lot of meat regularly";
		questions.add(question);
		diagnostic = new Diagnostic(name, optionalTreatments, questions);
		diagnostics.put(name, diagnostic);
		
		// add Use of various medications data (treatment + confirmation questions)
		questions = new ArrayList<String>();
		name = "Use of various medications";
		optionalTreatments = "referral to a family doctor for a match between medications";
		question = "do you take various different medications";
		questions.add(question);
		diagnostic = new Diagnostic(name, optionalTreatments, questions);
		diagnostics.put(name, diagnostic);
		
		// add Malnutrition data (treatment + confirmation questions)
		name = "Malnutrition";
		optionalTreatments = "referral to a family doctor for a match between medications";
		diagnostic = new Diagnostic(name, optionalTreatments);
		diagnostics.put(name, diagnostic);
		
		
		// add Pregnant data (treatment + confirmation questions)
		questions = new ArrayList<String>();
		name = "Pregnant";
		optionalTreatments = "pregnancy test";
		question = "have you not been tested for pregnancy";
		questions.add(question);
		diagnostic = new Diagnostic(name, optionalTreatments, questions);
		diagnostics.put(name, diagnostic);

	}

	private static void addSymptoms() {
		ArrayList<Profile> profiles;
		ArrayList<Diagnostic> lowDiagnostics;
		ArrayList<Diagnostic> highDiagnostics;
		Profile profile;
		Symptom symp;
		String name;
		double factor;
		
		// add data of white Blood Cells (WBC)
		profiles = new ArrayList<Profile>();
		profile = new MedicalProfile(new Range(18, OLDEST_PERSON), new Range(4500, 11000)); // adult (ages 18+) profile + value ranges
		profiles.add(profile);
		profile = new MedicalProfile(new Range(4, 17), new Range(5500, 15500));			 	// children (ages 4-17) profile + value ranges
		profiles.add(profile);
		profile = new MedicalProfile(new Range(3), new Range(6000, 17500));			 	 	// toddler's (ages 0-3) profile + value ranges
		profiles.add(profile);
		name = "WBC";																		// white blood cells short name
		symp = new Symptom(name, "white Blood Cells", profiles);
		lowDiagnostics = new ArrayList<Diagnostic>();
		highDiagnostics = new ArrayList<Diagnostic>();
		lowDiagnostics.add(diagnostics.get("Viral disease"));
		lowDiagnostics.add(diagnostics.get("Cancer"));
		highDiagnostics.add(diagnostics.get("Infection"));
		highDiagnostics.add(diagnostics.get("Blood disease"));
		highDiagnostics.add(diagnostics.get("Cancer"));
		symp.addDiagnostics(lowDiagnostics, highDiagnostics);
		getSymptoms().put(name, symp);
		
		
		// add data of Neutrophil (Neut)
		profiles = new ArrayList<Profile>();
		profile = new MedicalProfile(new Range(28, 54)); 								 	 // general population profile + value ranges
		profiles.add(profile);
		name = "NEUT";
		symp = new Symptom(name, "Neutrophil", profiles);
		lowDiagnostics = new ArrayList<Diagnostic>();
		highDiagnostics = new ArrayList<Diagnostic>();
		lowDiagnostics.add(diagnostics.get("Blood cell formation disorder"));
		lowDiagnostics.add(diagnostics.get("Viral disease"));
		lowDiagnostics.add(diagnostics.get("Cancer"));
		highDiagnostics.add(diagnostics.get("Viral disease"));
		symp.addDiagnostics(lowDiagnostics, highDiagnostics);
		getSymptoms().put(name, symp);
		
		// add data of Lymphocytes (Lymph)
		profiles = new ArrayList<Profile>();
		profile = new MedicalProfile(new Range(36, 52)); 								 	 // general population profile + value ranges
		profiles.add(profile);
		name = "LYMPH";
		symp = new Symptom(name, "Lymphocytes", profiles);
		lowDiagnostics = new ArrayList<Diagnostic>();
		highDiagnostics = new ArrayList<Diagnostic>();
		lowDiagnostics.add(diagnostics.get("Blood cell formation disorder"));
		highDiagnostics.add(diagnostics.get("Cancer"));
		highDiagnostics.add(diagnostics.get("Viral disease"));
		symp.addDiagnostics(lowDiagnostics, highDiagnostics);
		getSymptoms().put(name, symp);
		
		// add data of Red Blood Cells (RBC)
		profiles = new ArrayList<Profile>();
		profile = new MedicalProfile(new Range(4.5, 6)); 								 	 // general population profile + value ranges
		profiles.add(profile);
		name = "RBC";
		symp = new Symptom(name, "Red Blood Cells", profiles);
		lowDiagnostics = new ArrayList<Diagnostic>();
		highDiagnostics = new ArrayList<Diagnostic>();
		lowDiagnostics.add(diagnostics.get("Bleeding"));
		lowDiagnostics.add(diagnostics.get("Anemia"));
		highDiagnostics.add(diagnostics.get("Blood cell formation disorder"));
		highDiagnostics.add(diagnostics.get("Smoker"));
		highDiagnostics.add(diagnostics.get("Lung disease"));
		symp.addDiagnostics(lowDiagnostics, highDiagnostics);
		getSymptoms().put(name, symp);
		
		// add data of Hematocrit ranges and Chart Test (HCT)
		profiles = new ArrayList<Profile>();
		profile = new MedicalProfile(Gender.MALE, new Range(37, 54)); 						 // men's profile + value ranges
		profiles.add(profile);
		profile = new MedicalProfile(Gender.FEMALE, new Range(33, 47)); 					 // women's profile + value ranges
		profiles.add(profile);
		name = "HCT";
		symp = new Symptom(name, "Hematocrit ranges and Chart Test", profiles);
		lowDiagnostics = new ArrayList<Diagnostic>();
		highDiagnostics = new ArrayList<Diagnostic>();
		lowDiagnostics.add(diagnostics.get("Bleeding"));
		lowDiagnostics.add(diagnostics.get("Anemia"));
		highDiagnostics.add(diagnostics.get("Smoker"));
		symp.addDiagnostics(lowDiagnostics, highDiagnostics);
		getSymptoms().put(name, symp);
	
		// add data of Urea
		factor = 1 + 0.1;																 		// North African's factor
		profiles = new ArrayList<Profile>();
		profile = new MedicalProfile(Region.NORTH_AFRICA, new Range(17 * factor, 43 * factor)); // North African's profile + value ranges
		profiles.add(profile);
		profile = new MedicalProfile(new Range(17, 43)); 								 	 	// general population profile + value ranges
		profiles.add(profile);
		name = "UREA";
		symp = new Symptom(name, "Urine in blood", profiles);
		lowDiagnostics = new ArrayList<Diagnostic>();
		highDiagnostics = new ArrayList<Diagnostic>();
		lowDiagnostics.add(diagnostics.get("Malnutrition"));
		lowDiagnostics.add(diagnostics.get("Diet"));
		lowDiagnostics.add(diagnostics.get("Liver disease"));
		lowDiagnostics.add(diagnostics.get("Pregnant"));
		highDiagnostics.add(diagnostics.get("Kidney disease"));
		highDiagnostics.add(diagnostics.get("Dehydration"));
		highDiagnostics.add(diagnostics.get("Diet"));
		symp.addDiagnostics(lowDiagnostics, highDiagnostics);
		getSymptoms().put(name, symp);
		
		// add data of Hemoglobin (Hb)
		profiles = new ArrayList<Profile>();
		profile = new MedicalProfile(new Range(0, 17), new Range(11.5, 15.5));			 	 // children (ages 0-17) profile + value ranges
		profiles.add(profile);
		profile = new MedicalProfile(Gender.MALE, new Range(12, 18)); 						 // men's profile + value ranges
		profiles.add(profile);
		profile = new MedicalProfile(Gender.FEMALE, new Range(12, 16));				 	 	 // women's (ages 0-3) profile + value ranges
		profiles.add(profile);
		name = "HB";
		symp = new Symptom(name, "Hemoglobin", profiles);
		lowDiagnostics = new ArrayList<Diagnostic>();
		highDiagnostics = null;
		lowDiagnostics.add(diagnostics.get("Anemia"));
		lowDiagnostics.add(diagnostics.get("Hematological disorder"));
		lowDiagnostics.add(diagnostics.get("Iron deficiency"));
		lowDiagnostics.add(diagnostics.get("Bleeding"));
		symp.addDiagnostics(lowDiagnostics, highDiagnostics);
		getSymptoms().put(name, symp);
		
		// add data of Creatinine (Cr)
		profiles = new ArrayList<Profile>();
		profile = new MedicalProfile(new Range(2), new Range(0.2, 0.5));			 	 	 // toddler's (ages 0-2) profile + value ranges
		profiles.add(profile);
		profile = new MedicalProfile(new Range(3, 17), new Range(0.5, 1));			 		 // children (ages 3-17) profile + value ranges
		profiles.add(profile);
		profile = new MedicalProfile(new Range(18, 59), new Range(0.6, 1)); 			 	 // adult (ages 18-59) profile + value ranges
		profiles.add(profile);
		profile = new MedicalProfile(new Range(60, OLDEST_PERSON), new Range(0.6, 1.2)); 	 // elderly (ages 60+) profile + value ranges
		profiles.add(profile);
		name = "CR";																 		 // white blood cells short name
		symp = new Symptom(name, "Creatinine", profiles);
		lowDiagnostics = new ArrayList<Diagnostic>();
		highDiagnostics = new ArrayList<Diagnostic>();
		lowDiagnostics.add(diagnostics.get("Muscle diseases"));
		lowDiagnostics.add(diagnostics.get("Malnutrition"));
		highDiagnostics.add(diagnostics.get("Kidney disease"));
		highDiagnostics.add(diagnostics.get("Muscle diseases"));
		highDiagnostics.add(diagnostics.get("Carnivore"));
		symp.addDiagnostics(lowDiagnostics, highDiagnostics);
		getSymptoms().put(name, symp);
		
		// add data of Iron
		factor = 1 - 0.2;															 		 // women's factor
		profiles = new ArrayList<Profile>();
		profile = new MedicalProfile(Gender.FEMALE, new Range(60 * factor, 160 * factor));   // women's profile + value ranges
		profiles.add(profile);
		profile = new MedicalProfile(new Range(60, 160)); 									 // general population profile + value ranges
		profiles.add(profile);
		name = "IR";
		symp = new Symptom(name, "Iron", profiles);
		lowDiagnostics = new ArrayList<Diagnostic>();
		highDiagnostics = new ArrayList<Diagnostic>();
		lowDiagnostics.add(diagnostics.get("Iron deficiency"));
		lowDiagnostics.add(diagnostics.get("Malnutrition"));
		lowDiagnostics.add(diagnostics.get("Bleeding"));
		highDiagnostics.add(diagnostics.get("Iron poisoning"));
		symp.addDiagnostics(lowDiagnostics, highDiagnostics);
		getSymptoms().put(name, symp);
		
		// add data of High Density Lipoprotein (HDL)
		factor = 1 + 0.2;															 		 // Ethiopian's factor
		profiles = new ArrayList<Profile>();
		profile = new MedicalProfile(Gender.FEMALE, Region.ETHIOPIA, new Range(34 * factor, 82 * factor)); // Ethiopian women profile + value ranges
		profiles.add(profile);
		profile = new MedicalProfile(Gender.MALE, Region.ETHIOPIA, new Range(29 * factor, 62 * factor));   // Ethiopian men profile + value ranges
		profiles.add(profile);
		profile = new MedicalProfile(Gender.FEMALE, new Range(34, 82));    				 	 // women's profile + value ranges
		profiles.add(profile);
		profile = new MedicalProfile(Gender.MALE, new Range(29, 62));   					 // men's african's profile + value ranges
		profiles.add(profile);
		name = "HDL";
		symp = new Symptom(name, "High Density Lipoprotein", profiles);
		lowDiagnostics = new ArrayList<Diagnostic>();
		highDiagnostics = null;
		lowDiagnostics.add(diagnostics.get("Heart diseases"));
		lowDiagnostics.add(diagnostics.get("Hyperlipidemia"));
		lowDiagnostics.add(diagnostics.get("Adult diabetes"));
		symp.addDiagnostics(lowDiagnostics, highDiagnostics);
		getSymptoms().put(name, symp);		
		
		// add data of Alkaline Phosphatase
		profiles = new ArrayList<Profile>();
		profile = new MedicalProfile(Region.NORTH_AFRICA, new Range(60, 120));  			 // North African's profile + value ranges
		profiles.add(profile);
		profile = new MedicalProfile(new Range(30, 90)); 									 // general population profile + value ranges
		profiles.add(profile);
		name = "APH";
		symp = new Symptom(name, "Alkaline Phosphatase", profiles);
		lowDiagnostics = new ArrayList<Diagnostic>();
		highDiagnostics = new ArrayList<Diagnostic>();
		lowDiagnostics.add(diagnostics.get("Diet"));
		lowDiagnostics.add(diagnostics.get("Vitamin deficiency"));
		highDiagnostics.add(diagnostics.get("Liver disease"));
		highDiagnostics.add(diagnostics.get("Diseases of the biliary tract"));
		highDiagnostics.add(diagnostics.get("Overactive thyroid gland"));
		highDiagnostics.add(diagnostics.get("Use of various medications"));
		highDiagnostics.add(diagnostics.get("Pregnant"));
		symp.addDiagnostics(lowDiagnostics, highDiagnostics);
		getSymptoms().put(name, symp);
		
	}
	
	public static void symptomsProfilesDisplay(ArrayList<Person> people) {
		ArrayList<Person> notFound = new ArrayList<Person>();
		for(Entry<String, Symptom> symptom : getSymptoms().entrySet()) {
			System.out.println("Symptom: " + symptom.getKey() + " (" + symptom.getValue().getDescription() + ")");
			notFound.addAll(people);
			for(Profile mp : symptom.getValue().getProfiles()) {
				System.out.println("\tMedical profile:\n\t\t" + mp + "\n\tMatching profiles:");
				for (int i = 0; i < notFound.size(); ++i) {
					Person p = notFound.get(i);
					if(mp.equals(p)) {
						System.out.println("\t\t" + p);
						notFound.remove(p);
						--i;
					}
				}
				System.out.println();
			}
			System.out.println();
		}
	}
	
	public static void symptomesDisplay() {
		getSymptoms().forEach((k, v) -> System.out.println("Symptom: " + k + "\n" + v));
	}


	public static HashMap<String, Authentication> getUsers() {
		return users;
	}


	public static void setUsers(HashMap<String, Authentication> users) {
		SystemData.users = users;
	}


	public static HashMap<String, Symptom> getSymptoms() {
		return symptoms;
	}


	public static void setSymptoms(HashMap<String, Symptom> symptoms) {
		SystemData.symptoms = symptoms;
	}

}
