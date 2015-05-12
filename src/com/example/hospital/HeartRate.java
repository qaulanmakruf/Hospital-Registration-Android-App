package com.example.hospital;

import java.util.Calendar;

/**
 * 
 * @author Mihir Jain
 *
 */


public class HeartRate extends PatientCondition{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7251837274699651802L;

	/**
	 * Constructs HeartRate.
	 * @param heartrate This HeartRate heartRate.
	 */
	public HeartRate(Calendar cal, Integer heartRate) {
		super(cal, heartRate);
	}

}