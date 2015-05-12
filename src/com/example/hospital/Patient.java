package com.example.hospital;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Manke Luo
 *
 */

public class Patient implements Serializable{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1862735291152948866L;

	/**
	 * This Patient's name
	 */
    private String name;
    
	/**
	 * This Patient's healthCardNumber
	 */
    private Integer healthCardNumber;
    
    /**
     * This Patient's birthday and arrivalTime
     */
    private Calendar birthday, arrivalTime;
    
    /**
     * This Patient's heartRate values
     */
    private HeartRate heartRate;
    
    /**
     * This Patient's temperature values
     */
    private Temperature temperature;
    
    /**
     * This Patient's blood pressure values
     */
    private BloodPressure bloodPressure;
    
    /**
     * This Patient's symptoms
     */
    private Symptoms symptoms;
    
    /**
     * Constructs a patient with the parameters provided below. 
     * @param name The name of the patient
     * @param birthday The date of birth of the patient
     * @param healthCardNumber The health card number of the patient
     * @param temperature The current temperature of the patient
     * @param bloodPressure The current blood pressure of the patient
     * @param heartRate The current heart rate of the patient 
     * @param Symptoms The current symptoms of the patient 
     * @param arrivalTime Arrival time of the patient
     */
    public Patient(String name, Calendar birthday, Integer healthCardNumber, 
            Double temperature, Integer systolic, Integer diastolic, Integer heartRate, 
            String symptoms, Calendar arrivalTime){
        this.name = name;
        this.birthday = birthday;
        this.healthCardNumber = healthCardNumber;
        this.temperature = new Temperature(arrivalTime, temperature);
        List<Integer> BpList = new ArrayList<Integer>();
        BpList.add(systolic); 
        BpList.add(diastolic);
        this.bloodPressure = new BloodPressure(arrivalTime, BpList); 
        this.heartRate = new HeartRate(arrivalTime, heartRate);
        this.symptoms = new Symptoms(arrivalTime, symptoms);
        this.arrivalTime = arrivalTime;
    }

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the healthCardNumber
	 */
	public Integer getHealthCardNumber() {
		return healthCardNumber;
	}

	/**
	 * @param healthCardNumber the healthCardNumber to set
	 */
	public void setHealthCardNumber(Integer healthCardNumber) {
		this.healthCardNumber = healthCardNumber;
	}

	/**
	 * @return the birthday
	 */
	public Calendar getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(Calendar birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the arrivalTime
	 */
	public Calendar getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * @param arrivalTime the arrivalTime to set
	 */
	public void setArrivalTime(Calendar arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	/**
	 * @return the heartRate
	 */
	public Integer getHeartRate() {
		return (Integer) heartRate.getCondition();
	}

	/**
	 * @param heartRate the heartRate to set
	 */
	public void setHeartRate(Integer heartRate) {
		Calendar current = Calendar.getInstance();
		this.heartRate.setCondition(current, heartRate);
	}

	/**
	 * @return the temperature
	 */
	public Double getTemperature() {
		return (Double) temperature.getCondition();
	}

	/**
	 * @param temperature the temperature to set
	 */
	public void setTemperature(Double temperature) {
		Calendar current = Calendar.getInstance();
		this.temperature.setCondition(current, temperature);
	}

	/**
	 * @return the bloodPressure
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getBloodPressure() {
		return (List<Integer>) bloodPressure.getCondition();
	}

	/**
	 * @param bloodPressure the bloodPressure to set
	 */
	public void setBloodPressure(List<Integer> bloodPressure) {
		Calendar current = Calendar.getInstance();
		this.bloodPressure.setCondition(current, bloodPressure);
	}

	/**
	 * @return the Symptoms
	 */
	public String getSymptoms() {
		return (String) symptoms.getCondition();
	}

	/**
	 * @param Symptoms the Symptoms to set
	 */
	public void setSymptoms(String symptoms) {
		Calendar current = Calendar.getInstance();
		this.symptoms.setCondition(current, symptoms);
	}
	
	/**
	 * @return current and old temperatures of the patient.
	 */
	public Map<Calendar, Double> getAllTemperature(){
		Map<Calendar, Double> copy = new HashMap<Calendar, Double>();
		for (Map.Entry<Calendar, Object> entry : temperature.getAllConditions().entrySet()) {
			copy.put(entry.getKey(), (Double) entry.getValue());
			}
		return copy;
 	}
	
	/**
	 * @return current and old heart rates of the patient.
	 */
	public Map<Calendar, Integer> getAllHeartRate(){
		Map<Calendar, Integer> copy = new HashMap<Calendar, Integer>();
		for (Map.Entry<Calendar, Object> entry : heartRate.getAllConditions().entrySet()) {
			copy.put(entry.getKey(), (Integer) entry.getValue());
			}
		return copy;
 	}
	
	/**
	 * @return current and old blood pressures of the patient.
	 */
	@SuppressWarnings("unchecked")
	public Map<Calendar, List<Integer>> getAllBloodPressure(){
		Map<Calendar, List<Integer>> copy = new HashMap<Calendar, List<Integer>>();
		for (Map.Entry<Calendar, Object> entry : bloodPressure.getAllConditions().entrySet()) {
			copy.put(entry.getKey(), (List<Integer>) entry.getValue());
			}
		return copy;
 	}
	
	/**
	 * @return current and old symptoms of the patient. 
	 */
	public Map<Calendar, String> getAllSymptoms(){
		Map<Calendar, String> copy = new HashMap<Calendar, String>();
		for (Map.Entry<Calendar, Object> entry : symptoms.getAllConditions().entrySet()) {
			copy.put(entry.getKey(), (String) entry.getValue());
			}
		return copy;
 	}
}

