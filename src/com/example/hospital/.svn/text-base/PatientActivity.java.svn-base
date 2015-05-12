package com.example.hospital;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;


/**
 * 
 * @author Nour Khattab
 *
 */

public class PatientActivity extends Activity{

	public Patient patient;
	public Maker master;
	public Calendar arrivalDate, birthDate;
	public MenuItem edit;
	public int width;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient);
		((RelativeLayout)findViewById(R.id.edit)).setVisibility(View.GONE);
		Intent intent = getIntent();
		patient = (Patient)intent.getSerializableExtra("Patient");
		master = (Maker)intent.getSerializableExtra("master");
		
		arrivalDate = patient.getArrivalTime();
		birthDate = patient.getBirthday();
		
		String patientDate = patient.getBirthday().get(Calendar.DAY_OF_MONTH) 
        		+ "/" + patient.getBirthday().get(Calendar.MONTH) + "/" + 
        		patient.getBirthday().get(Calendar.YEAR);
		String amPm = "AM";
		if ((patient.getArrivalTime().get(Calendar.AM_PM)) == 1){
			amPm = "PM";
		}
		String patientArrivalTime = patient.getArrivalTime().get(Calendar.HOUR) +
				":" + patient.getArrivalTime().get(Calendar.MINUTE) + " "
				+ amPm + " - " + 
				patient.getArrivalTime().get(Calendar.DAY_OF_MONTH) + "/" 
				+ patient.getArrivalTime().get(Calendar.MONTH) + "/" + 
        		patient.getArrivalTime().get(Calendar.YEAR);
        ((TextView)findViewById(R.id.name)).setText(patient.getName());
        ((TextView)findViewById(R.id.birth_date)).setText(patientDate);
        ((TextView)findViewById(R.id.hcn)).setText(patient.getHealthCardNumber() + "");
        ((TextView)findViewById(R.id.arrival_time)).setText(patientArrivalTime);
        ((TextView)findViewById(R.id.temperature)).setText(patient.getTemperature() + "");
        ((TextView)findViewById(R.id.blood_pressure)).setText(patient.getBloodPressure() + "");
        ((TextView)findViewById(R.id.heart_rate)).setText(patient.getHeartRate() + "");
        ((TextView)findViewById(R.id.symptoms)).setText(patient.getSymptoms());
        if (Integer.valueOf(android.os.Build.VERSION.SDK) > 10){
        	Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            width = size.x;
            TextView symp = (TextView) findViewById(R.id.symptoms);
    		symp.setWidth((int) (width * 0.5));
		}
        else{
        	width = 200;
        }
        TextView symp = (TextView) findViewById(R.id.symptoms);
		symp.setWidth((int) (width * 0.5));
        
        
     // Show the Up button in the action bar. 
        setupActionBar();


		
		
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity_actions, menu);
			edit = menu.findItem(R.id.action_edit);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			finish();
			return true;
			
		case R.id.action_edit:
			((RelativeLayout)findViewById(R.id.edit)).setVisibility(View.VISIBLE);
			((EditText)findViewById(R.id.edit_name)).setText(((TextView)findViewById(R.id.name)).getText().toString());
			((Button)findViewById(R.id.edit_birth_date)).setText(((TextView)findViewById(R.id.birth_date)).getText().toString());
			((EditText)findViewById(R.id.edit_hcn)).setText(((TextView)findViewById(R.id.hcn)).getText().toString());
			((Button)findViewById(R.id.edit_arrival_time)).setText(((TextView)findViewById(R.id.arrival_time)).getText().toString());
			((EditText)findViewById(R.id.edit_temperature)).setText(((TextView)findViewById(R.id.temperature)).getText().toString());
			String[] bloodPressure =((TextView)findViewById(R.id.blood_pressure)).getText().toString().replace("[", "").replace("]", "").replace(" ", "").split(",");
			((EditText)findViewById(R.id.edit_systolic)).setText(bloodPressure[0]);
			((EditText)findViewById(R.id.edit_diastolic)).setText(bloodPressure[1]);
			((EditText)findViewById(R.id.edit_heart_rate)).setText(((TextView)findViewById(R.id.heart_rate)).getText().toString());
			((EditText)findViewById(R.id.edit_symptoms)).setText(((TextView)findViewById(R.id.symptoms)).getText().toString());
			((RelativeLayout)findViewById(R.id.view)).setVisibility(View.GONE);
			EditText symp = (EditText) findViewById(R.id.edit_symptoms);
			symp.setWidth((int) (width * 0.5));
				edit.setVisible(false);
			
			
			
			return true;
			
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	@SuppressWarnings("deprecation")
	public void temperatureHistory(View view){
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		LinearLayout lila1= new LinearLayout(this);
		lila1.setOrientation(1); //1 is for vertical orientation
		alertDialog.setTitle("Temperature History");
		for (Map.Entry<Calendar, Double> entry : patient.getAllTemperature().entrySet()) {
			final TextView historyTime = new TextView(this);
			final TextView historyValue = new TextView(this);
			LayoutParams layoutParams=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			historyValue.setLayoutParams(layoutParams);
			Calendar time = entry.getKey();
			String amPm = "AM";
			if ((time.get(Calendar.AM_PM)) == 1){
				amPm = "PM";
			}
			historyTime.setText(time.get(Calendar.HOUR) +
					":" + time.get(Calendar.MINUTE) + " "
					+ amPm + " - " + 
					time.get(Calendar.DAY_OF_MONTH) + "/" 
					+ time.get(Calendar.MONTH) + "/" + 
	        		time.get(Calendar.YEAR));
			historyValue.setText(entry.getValue() + "");
			RelativeLayout rela1= new RelativeLayout(this);
			historyValue.setWidth((int) (width * 0.45));
			rela1.addView(historyTime);
			rela1.addView(historyValue);
			lila1.addView(rela1);
			}
		alertDialog.setView(lila1);
		alertDialog.setButton("Exit", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			dialog.cancel();
		}
		});
		alertDialog.show();
	}
	
	@SuppressWarnings("deprecation")
	public void bpHistory(View view){
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		LinearLayout lila1= new LinearLayout(this);
		lila1.setOrientation(1); //1 is for vertical orientation
		alertDialog.setTitle("Blood Pressure History");
		for (Map.Entry<Calendar, List<Integer>> entry : patient.getAllBloodPressure().entrySet()) {
			final TextView historyTime = new TextView(this);
			final TextView historyValue = new TextView(this);
			LayoutParams layoutParams=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			historyValue.setLayoutParams(layoutParams);
			Calendar time = entry.getKey();
			String amPm = "AM";
			if ((time.get(Calendar.AM_PM)) == 1){
				amPm = "PM";
			}
			historyTime.setText(time.get(Calendar.HOUR) +
					":" + time.get(Calendar.MINUTE) + " "
					+ amPm + " - " + 
					time.get(Calendar.DAY_OF_MONTH) + "/" 
					+ time.get(Calendar.MONTH) + "/" + 
	        		time.get(Calendar.YEAR));
			historyValue.setText(entry.getValue() + "");
			RelativeLayout rela1= new RelativeLayout(this);
			historyValue.setWidth((int) (width * 0.45));
			rela1.addView(historyTime);
			rela1.addView(historyValue);
			lila1.addView(rela1);
			}
		alertDialog.setView(lila1);
		alertDialog.setButton("Exit", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			dialog.cancel();
		}
		});
		alertDialog.show();
	}
	
	@SuppressWarnings("deprecation")
	public void hrHistory(View view){
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		LinearLayout lila1= new LinearLayout(this);
		lila1.setOrientation(1); //1 is for vertical orientation
		alertDialog.setTitle("Heart Rate History");
		for (Map.Entry<Calendar, Integer> entry : patient.getAllHeartRate().entrySet()) {
			final TextView historyTime = new TextView(this);
			final TextView historyValue = new TextView(this);
			LayoutParams layoutParams=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			historyValue.setLayoutParams(layoutParams);
			Calendar time = entry.getKey();
			String amPm = "AM";
			if ((time.get(Calendar.AM_PM)) == 1){
				amPm = "PM";
			}
			historyTime.setText(time.get(Calendar.HOUR) +
					":" + time.get(Calendar.MINUTE) + " "
					+ amPm + " - " + 
					time.get(Calendar.DAY_OF_MONTH) + "/" 
					+ time.get(Calendar.MONTH) + "/" + 
	        		time.get(Calendar.YEAR));
			historyValue.setText(entry.getValue() + "");
			RelativeLayout rela1= new RelativeLayout(this);
			historyValue.setWidth((int) (width * 0.45));
			rela1.addView(historyTime);
			rela1.addView(historyValue);
			lila1.addView(rela1);
			}
		alertDialog.setView(lila1);
		alertDialog.setButton("Exit", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			dialog.cancel();
		}
		});
		alertDialog.show();
	}
	
	@SuppressWarnings("deprecation")
	public void symptomsHistory(View view){
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		ScrollView scvi1 = new ScrollView(this);
		LinearLayout lila1= new LinearLayout(this);
		lila1.setOrientation(1); //1 is for vertical orientation
		alertDialog.setTitle("Symptoms History");
		for (Map.Entry<Calendar, String> entry : patient.getAllSymptoms().entrySet()) {
			final TextView historyTime = new TextView(this);
			final TextView historyValue = new TextView(this);
			LayoutParams layoutParams=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			historyValue.setLayoutParams(layoutParams);
			Calendar time = entry.getKey();
			String amPm = "AM";
			if ((time.get(Calendar.AM_PM)) == 1){
				amPm = "PM";
			}
			historyTime.setText(time.get(Calendar.HOUR) +
					":" + time.get(Calendar.MINUTE) + " "
					+ amPm + " - " + 
					time.get(Calendar.DAY_OF_MONTH) + "/" 
					+ time.get(Calendar.MONTH) + "/" + 
	        		time.get(Calendar.YEAR));
			historyValue.setText(entry.getValue() + "");
			RelativeLayout rela1= new RelativeLayout(this);
			historyValue.setWidth((int) (width * 0.45));
			rela1.addView(historyTime);
			rela1.addView(historyValue);
			lila1.addView(rela1);
			}
		scvi1.addView(lila1);
		alertDialog.setView(scvi1);
		alertDialog.setButton("Exit", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			dialog.cancel();
		}
		});
		alertDialog.show();
	}
	
	@SuppressLint("NewApi") // did this because I have a if statement for the SDK version
	@SuppressWarnings("deprecation")
	public void birthDate(View view){
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Birthday");
		alertDialog.setMessage("Edit Birthday");
		final DatePicker input = new DatePicker(this);
		input.updateDate(patient.getBirthday().get(Calendar.YEAR), patient.getBirthday().get(Calendar.MONTH), patient.getBirthday().get(Calendar.DAY_OF_MONTH));
		alertDialog.setView(input);
		if (Integer.valueOf(android.os.Build.VERSION.SDK) > 10){
			input.setCalendarViewShown(false);
		}
		alertDialog.setButton("Update", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			birthDate = Calendar.getInstance();
			birthDate.set(input.getYear(), input.getMonth(), input.getDayOfMonth());
			String patientDate = birthDate.get(Calendar.DAY_OF_MONTH)
	        		+ "/" + birthDate.get(Calendar.MONTH) + "/" + 
	        		birthDate.get(Calendar.YEAR);
			
			((Button)findViewById(R.id.edit_birth_date)).setText(patientDate);
			dialog.cancel();
		
		}
		});
		alertDialog.show();
	}
	@SuppressLint("NewApi") // see birthdate
	@SuppressWarnings("deprecation")
	public void arrivalTime(View view){
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		LinearLayout lila1= new LinearLayout(this);
		lila1.setOrientation(1); //1 is for vertical orientation
		alertDialog.setTitle("Arrival Time");
		alertDialog.setMessage("Edit Arrival time");
		final DatePicker input = new DatePicker(this);
		final TimePicker input2 = new TimePicker(this);
		input2.setCurrentHour(patient.getArrivalTime().get(Calendar.HOUR_OF_DAY));
		input2.setCurrentMinute(patient.getArrivalTime().get(Calendar.MINUTE));
		input.updateDate(patient.getArrivalTime().get(Calendar.YEAR), patient.getArrivalTime().get(Calendar.MONTH), patient.getArrivalTime().get(Calendar.DAY_OF_MONTH));
		if (Integer.valueOf(android.os.Build.VERSION.SDK) > 10){
			input.setCalendarViewShown(false);
		}
		lila1.addView(input2);
		lila1.addView(input);
		alertDialog.setView(lila1);
		alertDialog.setButton("Update", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			arrivalDate = Calendar.getInstance();
			arrivalDate.set(input.getYear(), input.getMonth(), input.getDayOfMonth(),input2.getCurrentHour() , input2.getCurrentMinute());
			String amPm = "AM";
			if ((arrivalDate.get(Calendar.AM_PM)) == 1){
				amPm = "PM";
			}
			String patientArrivalTime = arrivalDate.get(Calendar.HOUR) +
					":" + arrivalDate.get(Calendar.MINUTE) + " "
					+ amPm + " - " + 
					arrivalDate.get(Calendar.DAY_OF_MONTH) + "/" 
					+ arrivalDate.get(Calendar.MONTH) + "/" + 
	        		arrivalDate.get(Calendar.YEAR);
			((Button)findViewById(R.id.edit_arrival_time)).setText(patientArrivalTime);
			dialog.cancel();
		
		}
		});
		alertDialog.show();
	}
	public void save(View view){
		try{
			String name = ((EditText)findViewById(R.id.edit_name)).getText().toString();
			Integer hcn = Integer.valueOf(((EditText)findViewById(R.id.edit_hcn)).getText().toString());
			Double temperature = Double.parseDouble(((EditText)findViewById(R.id.edit_temperature)).getText().toString());
			Integer systolic = Integer.valueOf(((EditText)findViewById(R.id.edit_systolic)).getText().toString());
			Integer diastolic = Integer.valueOf(((EditText)findViewById(R.id.edit_diastolic)).getText().toString());
			Integer heartRate = Integer.valueOf(((EditText)findViewById(R.id.edit_heart_rate)).getText().toString());
			String symptoms = ((EditText)findViewById(R.id.edit_symptoms)).getText().toString();
			patient.setName(name);
			patient.setBirthday(birthDate);
			patient.setHealthCardNumber(hcn);
			if (! temperature.equals(patient.getTemperature())){
				patient.setTemperature(temperature);
			}
			
			List<Integer> bp = new ArrayList<Integer>();
			bp.add(systolic);
			bp.add(diastolic);
			if (! bp.equals(patient.getBloodPressure())){
				patient.setBloodPressure(bp);
			}
			if(! heartRate.equals(patient.getHeartRate())){
				patient.setHeartRate(heartRate);
			}
			if(! symptoms.equals(patient.getSymptoms())){
				patient.setSymptoms(symptoms);
			}
			patient.setArrivalTime(arrivalDate);
			master.addPatient(patient);
			Global.write(view.getContext(), master);
			
			Intent returnIntent = new Intent();
			returnIntent.putExtra("result", patient);
			setResult(RESULT_FIRST_USER,returnIntent);
			((RelativeLayout)findViewById(R.id.view)).setVisibility(View.VISIBLE);
			((TextView)findViewById(R.id.name)).setText(((TextView)findViewById(R.id.edit_name)).getText().toString());
			((TextView)findViewById(R.id.birth_date)).setText(((TextView)findViewById(R.id.edit_birth_date)).getText().toString());
			((TextView)findViewById(R.id.hcn)).setText(((TextView)findViewById(R.id.edit_hcn)).getText().toString());
			((TextView)findViewById(R.id.arrival_time)).setText(((TextView)findViewById(R.id.edit_arrival_time)).getText().toString());
			((TextView)findViewById(R.id.temperature)).setText(((TextView)findViewById(R.id.edit_temperature)).getText().toString());
			((TextView)findViewById(R.id.heart_rate)).setText(((TextView)findViewById(R.id.edit_heart_rate)).getText().toString());
			((TextView)findViewById(R.id.symptoms)).setText(((TextView)findViewById(R.id.edit_symptoms)).getText().toString());
			String bloodPressure = "[" + ((EditText)findViewById(R.id.edit_systolic)).getText() + ", " + ((EditText)findViewById(R.id.edit_diastolic)).getText() + "]";
			((TextView)findViewById(R.id.blood_pressure)).setText(bloodPressure);
			TextView symp = (TextView) findViewById(R.id.symptoms);
			symp.setWidth((int) (width * 0.5));
			((RelativeLayout)findViewById(R.id.edit)).setVisibility(View.GONE);
			edit.setVisible(true);
		}
		catch (Exception e){
			int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(view.getContext(), "Invalid Number Above", duration);
            toast.show();
		}
		


		
	}

	


}
