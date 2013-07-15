package com.example.parkifry;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class FindActivity extends FragmentActivity implements LocationListener,LocationSource {

	double[] location;
	
	GoogleMap mp;
	
	LocationManager lm;
	OnLocationChangedListener listener;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle bundle = getIntent().getExtras();
		location = bundle.getDoubleArray("location");
		setContentView(R.layout.activity_find);
		
		lm = (LocationManager)this.getSystemService(LOCATION_SERVICE);
		if(lm != null){
			lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
			
		}
		mapSetup();
	}

	private void mapSetup() {
		if(mp == null){
			mp = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.the_map)).getMap();
			if(mp != null){
				mp.setMyLocationEnabled(true);
				mp.setLocationSource(this);
			}
			addMarker();
		}
	}
	
	public void onPause(){
		if(lm != null){
			lm.removeUpdates((android.location.LocationListener) this);
		}
		super.onPause();
	}
	
	public void onResume(){
		super.onResume();
		mapSetup();
		if(lm != null){
			mp.setMyLocationEnabled(true);
		}
	}

	@Override
	public void activate(OnLocationChangedListener listener) {
		this.listener = listener;
		
	}

	@Override
	public void deactivate() {
		listener = null;
	}

	@Override
	public void onLocationChanged(Location location) {
		if(listener != null){
			listener.onLocationChanged(location);
			mp.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(),location.getLongitude())));
		}
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}
	public void addMarker(){
		if(mp != null){
			MarkerOptions options = new MarkerOptions();
			options.position(new LatLng(location[0],location[1]));
			options.title("You're parked here!");
			options.icon(BitmapDescriptorFactory.fromResource(R.drawable.car_icon));
			mp.addMarker(options);
		}
	}

}
