package com.example.hospital;


import java.util.Calendar;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;

/**
 * 
 * @author Nour Khattab
 *
 */

public class NewPatientActivity extends Activity {
	
	public Maker master;
	public Calendar birthDate, arrivalDate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_patient);
		// Show the Up button in the action bar.
		setupActionBar();
		Intent i = getIntent();
		master = (Maker)i.getSerializableExtra("master");
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
		getMenuInflater().inflate(R.menu.new_patient, menu);
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
			//NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@SuppressLint("NewApi") // did this because I have a if statement for the SDK version
	@SuppressWarnings("deprecation")
	public void birthDate(View view){
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Set Birthday");
		alertDialog.setMessage("Set Birth Date");
		final DatePicker input = new DatePicker(this);
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
			
			((Button)findViewById(R.id.patient_birth_date)).setText(patientDate);
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
		alertDialog.setMessage("Set Arrival time");
		final DatePicker input = new DatePicker(this);
		final TimePicker input2 = new TimePicker(this);
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
			((Button)findViewById(R.id.patient_arrival_date)).setText(patientArrivalTime);
			dialog.cancel();
		
		}
		});
		alertDialog.show();
	}
	public void save(View view){
		try{
			String name = ((EditText)findViewById(R.id.patient_name)).getText().toString();
			Integer hcn = Integer.valueOf(((EditText)findViewById(R.id.patient_hcn)).getText().toString());
			Double temperature = (Double) Double.parseDouble(((EditText)findViewById(R.id.patient_temperature)).getText().toString());
			Integer systolic = Integer.valueOf(((EditText)findViewById(R.id.patient_systolic)).getText().toString());
			Integer diastolic = Integer.valueOf(((EditText)findViewById(R.id.patient_diastolic)).getText().toString());
			Integer heartRate = Integer.valueOf(((EditText)findViewById(R.id.patient_heart_rate)).getText().toString());
			String symptoms = ((EditText)findViewById(R.id.patient_symptoms)).getText().toString();

			if (symptoms.equals("")){
				int duration = Toast.LENGTH_LONG;
	            Toast toast = Toast.makeText(view.getContext(), "Invalid Number Above or Missing Field", duration);
	            toast.show();
			}
			else{
				Patient newPatient = new Patient(name, birthDate, hcn, temperature, systolic, diastolic, heartRate, symptoms, arrivalDate);
				master.addPatient(newPatient);
				Global.write(view.getContext(), master);
				
				Intent returnIntent = new Intent();
				returnIntent.putExtra("result",newPatient);
				setResult(RESULT_OK,returnIntent); 
				finish();
			}

		}
		catch (Exception e){
			int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(view.getContext(), "Invalid Number Above or Missing Field", duration);
            toast.show();
		}
		
	}
}
