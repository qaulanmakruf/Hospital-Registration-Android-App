package com.example.hospital;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;

/**
 * 
 * @author Mihir Jain
 *
 */

@SuppressWarnings("serial")
public abstract class PatientCondition implements Serializable{
	
	/**
	 * This PatientCondition condition.
	 */
	private Map<Calendar, Object> condition;
	
	/**
	 * This PatientCondition newest time.
	 */
	public static Calendar newest;
	
	/**
	 * Constructs PatientCondition.
	 * @param cal This PatientCondition time cal.
	 * @param obj This PatientCondition condition obj.
	 */
	public PatientCondition(Calendar cal, Object obj) {
		this.condition = new HashMap<Calendar, Object>();
		this.condition.put(cal, obj);

	}

	/**
	 * Sets this PatientCondition condition.
	 * @param cal This PatientCondition condition recording time cal.
	 * @param obj This PatientCondition condition object obj mapped to condition.
	 */
	public void setCondition(Calendar cal, Object obj) {
		condition.put(cal, obj);
	}
	
	/**
	 * Gets this PatientCondition condition.
	 * @return This PatientCondition latest condition.
	 */
	public Object getCondition() {
		Calendar newest = Calendar.getInstance();
		newest.set(2000, 01, 01);
		for (Map.Entry<Calendar, Object> entry : condition.entrySet()) { 
			    	if (entry.getKey().after(newest)){
					newest = entry.getKey();
			    	}
		}
		return condition.get(newest);
	}
	
	/**
	 * Returns a copy of the map of PatientCondition condition.
	 * @return A copy of the map of PatientCondition condition.
	 */
	public Map<Calendar, Object> getAllConditions() {
		Map<Calendar, Object> conditionCopy = new HashMap<Calendar, Object>();
		for (Map.Entry<Calendar, Object> entry : condition.entrySet()) {
		    conditionCopy.put(entry.getKey(), entry.getValue());
		}
		return conditionCopy;
	}

}
