package com.example.hospital;

import android.annotation.SuppressLint;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Lyon Lay
 *
 */

public class Maker implements Serializable{
	
	/** The serial version UID. */
	private static final long serialVersionUID = 7391796850568814788L;
	
	/** The map in which all Patients are stored. */
	private Map<Integer, Patient> patients;

	/** Creates a new, empty map. */
	@SuppressLint("UseSparseArrays")
	public Maker(){
		this.patients = new HashMap<Integer ,Patient>();
	}
	
	/** Adds the given Patient to the patients map. 
	 * @param Patient, the Patient to be added to patients map*/
	public void addPatient(Patient patient){
		this.patients.put(patient.getHealthCardNumber(), patient); 	
	}
	
	/** Returns the Patient associated with the given health card number.
	 * @param Integer, the key value associated with the desired Patient 
	 */
	public Patient getPatient(Integer cardNum){
		return patients.get(cardNum);
	}
	
	/** Returns the map of Patients.
	 * @return the patients map */
	public Map<Integer, Patient> getAllPatients(){
		return this.patients;
	}
	
}