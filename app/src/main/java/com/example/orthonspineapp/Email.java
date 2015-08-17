package com.example.orthonspineapp;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Email extends Activity implements  OnItemSelectedListener {
	
	String timendate,finalResult,item;
	Spinner spinner;
	StringBuilder sb;
	Button sendmail;
	TelephonyManager tm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.email);
		sendmail = (Button) findViewById(R.id.buttonSendMail);
		sb =new StringBuilder("");
		 tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		
		sendmail.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder alertDialog = new AlertDialog.Builder
				(Email.this);
				
				sb.append(timendate + "\n " + " BODY PART : "+ item + "\n ");
				finalResult= sb.toString();
				//Toast.makeText(Email.this, finalResult, Toast.LENGTH_LONG).show();
				Log.e("final", finalResult);
				
		alertDialog.setTitle("Appointment Details");
		alertDialog.setMessage("Confirm the details n click SEND \n" + finalResult );
		alertDialog.setIcon(R.drawable.ic_launcher);
		alertDialog.setCancelable(true);
		alertDialog.setPositiveButton("SEND", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				 String phonenumber = "Appointmnent from mobile -- "+ tm.getLine1Number();
				Toast.makeText(Email.this, phonenumber, Toast.LENGTH_LONG).show();

				String[]myemail = {"makansari@gmail.com"};
				Intent emailIntent = new Intent(Intent.ACTION_SEND);				
				emailIntent.setType("message/rfc822");
				emailIntent.putExtra(Intent.EXTRA_EMAIL, myemail);
				emailIntent.putExtra(Intent.EXTRA_SUBJECT,   phonenumber) ;
				emailIntent.putExtra(Intent.EXTRA_TEXT, finalResult);
				//emailIntent.putExtra(Intent.EXTRA_BCC, value)
				
				startActivity(Intent.createChooser(emailIntent, "choose the client ..."));
				
			}
		});
		
		alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				finish();
				
			}
		});
		
		alertDialog.show();			
			}
		});
		 
		
		timendate = getIntent().getExtras().getString("timeNdate");
		
		Toast.makeText(Email.this, timendate, Toast.LENGTH_LONG).show();
	
		
		spinner = (Spinner) findViewById(R.id.spinner);
		spinner.setOnItemSelectedListener(this);
		

		ArrayList<String> categories= new ArrayList<String>();
		categories.add(" ");
		categories.add("Knee");
		categories.add("Hip");
		categories.add("Shoulder");
		categories.add("Elbow");
		
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
        (Email.this,android.R.layout.simple_spinner_item,categories);
		
		spinner.setAdapter(arrayAdapter);
		
		

	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View arg1, int position,
			long arg3) {
		// TODO Auto-generated method stub
		 item = parent.getItemAtPosition(position).toString();
		if(item.equals("Hip")){
			Toast.makeText(Email.this, item + " is selected", Toast.LENGTH_SHORT).show();
		}
		if(item.equals("Knee")){
			Toast.makeText(Email.this, item + " is selected", Toast.LENGTH_SHORT).show();
		}
		if(item.equals("Shoulder")){
			Toast.makeText(Email.this, item + " is selected", Toast.LENGTH_SHORT).show();
		}
		if(item.equals("Elbow")){
			Toast.makeText(Email.this, item + " is selected", Toast.LENGTH_SHORT).show();
		}
		
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

	
}
