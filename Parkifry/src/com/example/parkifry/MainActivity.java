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


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	LocationManager lm;
	LocationListener listener;
	Location currentSpot;
	ParkingSpot spot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        

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
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
    }
    public void onClick(View view){
    	switch(view.getId()){
    		case R.id.imagebutton1:
    			handlePark();
    			break;
    		case R.id.imagebutton2:
    			handleFind();
    			break;
    		
    	}
    	System.out.println(view.getId());
    }
    public void handlePark(){
    	if(spot == null){
    		if(currentSpot != null)
    			spot = new ParkingSpot(new double[]{ currentSpot.getLatitude(),currentSpot.getLongitude()}
    			,"spot");
    		else{
    			Toast.makeText(this, "Please wait for GPS fix", Toast.LENGTH_LONG);
    			return;
    		}
    	}else
    		spot.setLocation(currentSpot);
    	Toast.makeText(this, "You just parked!", Toast.LENGTH_LONG).show();
    	
    }
    public void handleFind(){
    	if(spot != null){
    		Intent i = new Intent(this,FindActivity.class);
    		Bundle bundle = new Bundle();
    		bundle.putDoubleArray("location", spot.getLocation());
    		i.putExtras(bundle);
    		startActivity(i);
    	}else {
    		Toast.makeText(this, "You need to park first!", Toast.LENGTH_LONG).show();
    	}
    }
}