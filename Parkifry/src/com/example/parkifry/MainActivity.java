package com.example.parkifry;

/*import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}*/


import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button parkButton;
	Button findButton;
	
	LocationManager lm;
	LocationListener listener;
	Location currentSpot;
	ParkingSpot spot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        
        parkButton = (Button)findViewById(R.id.imageButton1);
        findButton = (Button)findViewById(R.id.imageButton2);
        
        parkButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
        
        findButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
        
        lm = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        listener = new LocationListener(){

			@Override
			public void onLocationChanged(Location location) {
				currentSpot = location;
				
			}

			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStatusChanged(String provider, int status,
					Bundle extras) {
				// TODO Auto-generated method stub
				
			}
        	
        };
    }
    public void handlePark(){
    	if(spot == null)
    		spot = new ParkingSpot(new double[]{ currentSpot.getLatitude(),currentSpot.getLongitude()}
    		,"spot");
    	else
    		spot.setLocation(currentSpot);
    	Toast.makeText(this, "You just parked!", Toast.LENGTH_LONG).show();
    	
    }
    public void handleFind(){
    	
    }
}