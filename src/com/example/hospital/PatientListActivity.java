package com.example.hospital;


import java.util.Map;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

/**
 * 
 * @author Nour Khattab
 *
 */

public class PatientListActivity extends Activity{

	public Maker master;
	public int inactivity;
	public static int id = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient_list);
		// Show the Up button in the action bar.
		setupActionBar();
		Intent i = getIntent();
		master = (Maker)i.getSerializableExtra("master");
		for (Map.Entry<Integer, Patient> entry : master.getAllPatients().entrySet()) { 
	    	addButton(entry.getValue());
			}
		EditText searchText = (EditText) findViewById(R.id.search);
		
		searchText.addTextChangedListener(new TextWatcher(){
			@Override
			public void afterTextChanged(Editable s) {
				int j = 0;
				while (j < id){
					Button search = (Button) findViewById(j);
					try{
						search.setVisibility(View.VISIBLE);
						if (s.length() != 0){
							Patient subject = (Patient) search.getTag();
							Integer big = subject.getHealthCardNumber();
							String sub = (String) s.toString();

							if (! contains(sub, big)){
								search.setVisibility(View.GONE);
							}
						}
					}
					catch(Exception e){
						
					}
					
					j++;
					
				}
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				
				
			}
	    }); 
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
		getMenuInflater().inflate(R.menu.patient_list, menu);
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
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void sendMessage(View view) {

	}

	public void addNewPatient(View view){
		Intent i = new Intent(this, NewPatientActivity.class);
		i.putExtra("master", master);
		startActivityForResult(i,1);
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		  if (requestCode == 1) {

		     if(resultCode == RESULT_OK){  
		         addButton((Patient)data.getSerializableExtra("result"));
		         
		     }
		     if(resultCode == RESULT_FIRST_USER){ 
		    	 addButton((Patient)data.getSerializableExtra("result"));
		    	 Button remove = (Button)findViewById(inactivity);
		         ViewGroup layout = (ViewGroup) remove.getParent();
		         if(null!=layout) //for safety only  as you are doing onClick
		           layout.removeView(remove);
		         
		     }
		     if (resultCode == RESULT_CANCELED) {    
		         //Null
		     }
		  }
		}

	public void addButton(final Patient patient){
		Button myButton = new Button(this);
        myButton.setText(patient.getName());
        myButton.setTag(patient);
        myButton.setId(id);
        id++;
        myButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            	Intent i = new Intent(view.getContext(), PatientActivity.class);
        		i.putExtra("Patient", patient);
        		i.putExtra("master", master);
        		inactivity = view.getId();
        		startActivityForResult(i,1);
            }
        });
        LinearLayout ll = (LinearLayout)findViewById(R.id.patientlist);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        ll.addView(myButton, lp);
	}
	
	public boolean contains(String sub, Integer big){
		String sBig;
		sBig = "" + big;
		if (sub.length() > sBig.length()){
			return false;
		}
		else{
			return sBig.substring(0, sub.length()).equals(sub);
		}
		
		
	}
}
