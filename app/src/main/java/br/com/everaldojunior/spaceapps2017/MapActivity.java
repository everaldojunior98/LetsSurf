package br.com.everaldojunior.spaceapps2017;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity {

    GoogleMap googleMap;
    MapView mapView;

    @Override
    protected void onResume(){
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mapView = (MapView) findViewById(R.id.mapView2);
        mapView.onCreate(savedInstanceState);

        googleMap = mapView.getMap();

        mapView.getMap().setOnMapClickListener(new OnMapClickListener() {
            @Override
            public void onMapClick(final LatLng arg0){
                mapView.getMap().addMarker(new MarkerOptions().position(arg0).title(""));

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MapActivity.this);
                alertDialogBuilder.setTitle("Do you want to use this location?");

                alertDialogBuilder
                        .setMessage("")
                        .setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                Intent intent = new Intent(MapActivity.this, ResultActivity.class);
                                intent.putExtra("LatLon", arg0.latitude+","+arg0.longitude);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                                mapView.getMap().clear();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });
    }
}
