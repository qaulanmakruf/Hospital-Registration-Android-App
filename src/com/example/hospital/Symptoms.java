package com.example.hospital;

import java.io.Serializable;
import java.util.Calendar;

/**
 * 
 * @author Mihir Jain
 *
 */


public class Symptoms extends PatientCondition implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2500105530166245651L;

	/**
	 * Constructs Symptoms.
	 * @param symptoms This Symptoms symptoms.
	 */
	public Symptoms(Calendar cal, String symptoms) {
		super(cal, symptoms);
	}

}
