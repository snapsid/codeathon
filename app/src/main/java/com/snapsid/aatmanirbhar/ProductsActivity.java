package com.snapsid.aatmanirbhar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ProductsActivity extends AppCompatActivity {

    Button buy1, buy2;

    public static String item="";

    public static String locF="";


    LocationManager locationManager;
    LocationListener locationListener;
    private final long MIN_TIME = 1000;
    private final long MIN_DIST = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        buy1=findViewById(R.id.buyid1);
        buy2=findViewById(R.id.buyid2);


        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                try {
                    // latLng = new LatLng(location.getLatitude(), location.getLongitude());

                    String lat=String.valueOf(location.getLatitude());
                    String lon=String.valueOf(location.getLongitude());

                    // mMap.addMarker(new MarkerOptions().position(latLng).title("My Position"));

                    //mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

                    String phoneNumber = "7999416853";
                    String myLatidude = String.valueOf(location.getAltitude());
                    String myLongitude = String.valueOf(location.getLongitude());

//                    String locString="https://www.google.com/maps/search/?api=1&query="+myLatidude+","+myLongitude;
//
//                    String message = "Latitude = " + myLatidude + " Longitude = " + myLongitude;
//                    SmsManager smsManager = SmsManager.getDefault();
//                    smsManager.sendTextMessage(phoneNumber, null, locString, null, null);

                    Log.d("location1", lat+"  "+lon);

                    String locString="https://www.google.com/maps/search/?api=1&query="+lat+","+lon;

                    locF=locString;


                    Log.d("url1",locString);

                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("keyurl",locString).apply();
//                    SharedPreferences.Editor editor = sharedpreferences.edit();
//                    editor.putString("keyurl",locString);
//                    editor.apply();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        try {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME, MIN_DIST, locationListener);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME, MIN_DIST, locationListener);
        } catch (SecurityException e) {
            e.printStackTrace();
        }



        buy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item="item1";
                Intent intent=new Intent(getApplicationContext(), Address.class);
                startActivity(intent);
            }
        });

        buy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item="item2";
                Intent intent=new Intent(getApplicationContext(), Address.class);
                startActivity(intent);
            }
        });

    }
}
