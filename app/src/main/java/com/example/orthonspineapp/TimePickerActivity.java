package com.example.orthonspineapp;
 
import java.util.Calendar;
 
import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
 
public class TimePickerActivity extends Activity {
    /** Private members of the class */
    private TextView displayTime;
    private Button pickTime,setTime;
    StringBuilder sb = new StringBuilder();
    String date="", timeAndDate="";
    private int pHour;
    private int pMinute;
    /** This integer will uniquely define the dialog to be used for displaying time picker.*/
    static final int TIME_DIALOG_ID = 0;
     
    /** Callback received when the user "picks" a time in the dialog */
    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
        new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                pHour = hourOfDay;
                pMinute = minute;
                updateDisplay();
                displayToast();
            }
        };
     
    /** Updates the time in the TextView */
    private void updateDisplay() {
        displayTime.setText(
            new StringBuilder()
                    .append(pad(pHour)).append(":")
                    .append(pad(pMinute)).append(" "));
        
       
        

        
    }
     
    /** Displays a notification when the time is updated */
    private void displayToast() {
       // Toast.makeText(this, new StringBuilder().append("Time choosen is ").append(displayTime.getText()),   Toast.LENGTH_SHORT).show();
             
    }
     
    /** Add padding to numbers less than ten */
    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }
     
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timelayout);
 
        /** Capture our View elements */
        displayTime = (TextView) findViewById(R.id.timeDisplay);
        pickTime = (Button) findViewById(R.id.pickTime);
        setTime = (Button) findViewById(R.id.setTime);
        setTime.setEnabled(false);
        date = getIntent().getExtras().getString("time");
 
        /** Listener for click event of the button */
        pickTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pickTime.setEnabled(false);
                setTime.setEnabled(true);

                showDialog(TIME_DIALOG_ID);
            }
        });
        
        
        setTime.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String finaltime = displayTime.getText().toString();
			        sb.append(date + " "+ " \n TIME: " + finaltime);
			        timeAndDate=sb.toString();
			        Toast.makeText(TimePickerActivity.this,timeAndDate,   Toast.LENGTH_SHORT).show();

			        
				Intent okIntent = new Intent(TimePickerActivity.this, Email.class);
				okIntent.putExtra("timeNdate",timeAndDate );
				startActivity(okIntent);
				
			}
		} );
 
        /** Get the current time */
        final Calendar cal = Calendar.getInstance();
        pHour = cal.get(Calendar.HOUR_OF_DAY);
        pMinute = cal.get(Calendar.MINUTE);
 
        /** Display the current time in the TextView */
        updateDisplay();
    }
     
    /** Create a new dialog for time picker */
     
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
        case TIME_DIALOG_ID:
            return new TimePickerDialog(this,
                    mTimeSetListener, pHour, pMinute, false);
        }
        return null;
    }
}