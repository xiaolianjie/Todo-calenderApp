package com.example.orthonspineapp;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
Button orthoButton, spineButton;
ImageButton ortho, spine;
TextView tv;
String body;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);
        ortho= (ImageButton) findViewById(R.id.imageButtonOrtho);
        spine=(ImageButton) findViewById(R.id.imageButtonSpine);
        tv=(TextView) findViewById(R.id.textViewSelect);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),
        	      "fonts/AlexBrushRegular.ttf");
        tv.setTypeface(custom_font);
      
        ortho.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				Intent myIntent = new Intent(getApplicationContext(), MyCalendarActivity.class);
				myIntent.putExtra("body", "Orthopedic");
				startActivity(myIntent);
			}
		});
        
        
        spine.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myIntents = new Intent(getApplicationContext(), MyCalendarActivity.class);
				myIntents.putExtra("body", "Spine");
				startActivity(myIntents);
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
