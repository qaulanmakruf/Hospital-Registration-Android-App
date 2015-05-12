package com.example.hospital;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 *
 * @author Lyon Lay
 *
 */

public class LoginActivity extends Activity {
	
    /** The filename in which to write records. */ 
    public static final String USERSFILE = "users.txt";
    
    /** Declares the master file. */
	public Maker master;
	
	/** Creates login activity layout. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}
	
	/** Creates login activity actions. */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login_activity_actions, menu);
		return true;
	}

	/** */
	@SuppressWarnings("deprecation")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_nurse:
			AlertDialog alertDialog = new AlertDialog.Builder(this).create();
			LinearLayout lila1= new LinearLayout(this);
			lila1.setOrientation(1); //1 is for vertical orientation
			alertDialog.setTitle("New Nurse");
			alertDialog.setMessage("Create New Nurse");
			final EditText user = new EditText(this);
			final EditText pass = new EditText(this);
			final EditText pass2 = new EditText(this);
			user.setHint("Username");
			pass.setHint("Password");
			pass2.setHint("Confirm Password");
			pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
			pass2.setTransformationMethod(PasswordTransformationMethod.getInstance());
			lila1.addView(user);
			lila1.addView(pass);
			lila1.addView(pass2);
			alertDialog.setView(lila1);
			alertDialog.setButton("Create", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					if (user.getText().toString().equals("") || pass.getText().toString().equals("")){
						Context context = getApplicationContext();
						Toast toast = Toast.makeText(context, "Invalid Username or Password", Toast.LENGTH_LONG);
						toast.show();
					}
					else{
						if (pass.getText().toString().equals(pass2.getText().toString())){
							write(user.getText().toString() + "," + pass.getText().toString());
							dialog.cancel();
						}
						else{
							Context context = getApplicationContext();
							Toast toast = Toast.makeText(context, "Passwords Do Not Match", Toast.LENGTH_LONG);
							toast.show();
						}

					}

				}
			});
			alertDialog.show();


			return true;

		}
		return super.onOptionsItemSelected(item);
	}
	
	/** Login Button: Moves user to next activity if login information is correct. */
	public void loginButton(View view) {
		try{
			EditText user = (EditText)findViewById(R.id.user);
			EditText password = (EditText)findViewById(R.id.password);
			String userInputUsername = user.getText().toString();
			String userInputPassword = password.getText().toString();
			//Read users file
			HashMap<String, String> usersMap = readFromFile();

			// Check for invalid characters
			if((userInputUsername.contains(",")) | (userInputUsername.contains("\n"))
					| (userInputPassword.contains(",")) | (userInputPassword.contains("\n"))){
				Context context = getApplicationContext();
				CharSequence text = "Invalid character: ',' or '\n'";
				int duration = Toast.LENGTH_SHORT;
				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
			}
			// Check input against file
			else if(usersMap.containsKey(userInputUsername) && (usersMap.get(userInputUsername).equals(userInputPassword))){
				//pass patient map to next activity
				master = (Maker)Global.read(this);
				Intent i = new Intent(this, PatientListActivity.class);
				i.putExtra("master", master);
				startActivity(i);
			}
			// Report login fail
			else{
				Context context = getApplicationContext();
				CharSequence text = "Invalid Username/Password";
				int duration = Toast.LENGTH_SHORT;
				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
			}
		}
		catch(Exception e){
			Toast toast = Toast.makeText(getApplicationContext(),"Create A New Database", Toast.LENGTH_LONG);
			toast.show();
		}
	}

	/** Reads from USERSFILE and creates a HashMap of health card numbers oa keys and Patients as values. */
	private HashMap<String, String> readFromFile() {
		
		HashMap<String, String> users = new HashMap<String, String>();

		try {
			InputStream inputStream = openFileInput(USERSFILE);
			if ( inputStream != null ) {
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

				String line=null; 

				// Write USERSFILE output to users map.
				while (( line = bufferedReader.readLine()) != null) { //line is in format "username,password"
					String[] l = line.split(",");
					users.put(l[0], l[1]);
				}
			}
		}
		catch (FileNotFoundException e) {
			Log.e("login activity", "File not found: " + e.toString());
		}
		catch (IOException e) {
			Log.e("login activity", "Can not read file: " + e.toString());
		}
		return users;
	}
	
	/** Button, on click wipes current Patient database, creates new empty database. */
	public void newButton(View view) {
		master = new Maker();
		try{
			Global.write(this, master);
		}
		catch(Exception e){
			Toast toast = Toast.makeText(getApplicationContext(),"ERROR CONTACT DEVELOPERS", Toast.LENGTH_LONG);
			toast.show();
		}
		write("");
		Toast toast = Toast.makeText(getApplicationContext(),"Created New Database", Toast.LENGTH_LONG);
		Toast toast2 = Toast.makeText(getApplicationContext(),"Wiped All Previous Data", Toast.LENGTH_LONG);

		toast2.show();
		toast.show();


	}
	/** Writes a String block to USERSFILE. */
	public void write(String string){
		try {
			// Open myfilename.txt for writing
			OutputStreamWriter out=new OutputStreamWriter(openFileOutput(USERSFILE, MODE_PRIVATE));
			// Write the contents to the file

			out.write(string);  

			// Close the file
			out.close();

		}
		catch (java.io.IOException e) {

			//do something if an IOException occurs.
		}
	}


}
