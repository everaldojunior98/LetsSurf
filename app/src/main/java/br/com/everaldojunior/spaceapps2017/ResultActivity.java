package br.com.everaldojunior.spaceapps2017;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import br.com.everaldojunior.spaceapps2017.CustomList.CustomListAdapter;
import br.com.everaldojunior.spaceapps2017.CustomList.InfoItem;

public class ResultActivity extends AppCompatActivity{

    //UV
    String precautions[] = {"A UV Index reading of 0 to 2 means low danger from the sun's UV rays for the average person.\n⚫ Wear sunglasses on bright days.\n⚫ If you burn easily, cover up and use broad spectrum SPF 30+ sunscreen.\n⚫ Watch out for bright surfaces, like sand, water and snow, which reflect UV and increase exposure.",
    "A UV Index reading of 3 to 5 means moderate risk of harm from unprotected sun exposure.\n⚫ Stay in shade near midday when the sun is strongest.\n⚫ If outdoors, wear protective clothing, a wide-brimmed hat, and UV-blocking sunglasses.\n⚫ Generously apply broad spectrum SPF 30+ sunscreen every 2 hours, even on cloudy days, and after swimming or sweating.\n⚫ Watch out for bright surfaces, like sand, water and snow, which reflect UV and increase exposure.",
    "A UV Index reading of 6 to 7 means high risk of harm from unprotected sun exposure. Protection against skin and eye damage is needed.\n⚫ Reduce time in the sun between 10 a.m. and 4 p.m.\n⚫ If outdoors, seek shade and wear protective clothing, a wide-brimmed hat, and UV-blocking sunglasses.\n⚫ Generously apply broad spectrum SPF 30+ sunscreen every 2 hours, even on cloudy days, and after swimming or sweating.\n⚫ Watch out for bright surfaces, like sand, water and snow, which reflect UV and increase exposure.",
    "A UV Index reading of 8 to 10 means very high risk of harm from unprotected sun exposure. Take extra precautions because unprotected skin and eyes will be damaged and can burn quickly.\n⚫ Minimize sun exposure between 10 a.m. and 4 p.m.\n⚫ If outdoors, seek shade and wear protective clothing, a wide-brimmed hat, and UV-blocking sunglasses.\n⚫ Generously apply broad spectrum SPF 30+ sunscreen every 2 hours, even on cloudy days, and after swimming or sweating.\n⚫ Watch out for bright surfaces, like sand, water and snow, which reflect UV and increase exposure.",
    "A UV Index reading of 11 or more means extreme risk of harm from unprotected sun exposure. Take all precautions because unprotected skin and eyes can burn in minutes.\n⚫ Try to avoid sun exposure between 10 a.m. and 4 p.m.\n⚫ If outdoors, seek shade and wear protective clothing, a wide-brimmed hat, and UV-blocking sunglasses.\n⚫ Generously apply broad spectrum SPF 30+ sunscreen every 2 hours, even on cloudy days, and after swimming or sweating.\n⚫ Watch out for bright surfaces, like sand, water and snow, which reflect UV and increase exposure."};
    String titles[] = {"UV: 0 to 2 (LOW)", "UV: 3 to 5 (MODERATE)", "UV: 6 to 7 (HIGH)", "UV: 8 to 10 (VERY HIGH)", "UV: 11 or more (EXTREME)"};

    ArrayList<InfoItem> data = new ArrayList<>();//climate inf.
    ArrayList<InfoItem> data2 = new ArrayList<>();//water inf.
    String latLon;
    String key = "";//API WORLD WEATHER ONLINE
    private Document htmlDocument;
    private String htmlPageUrlWeather;
    private String htmlPageUrlMarine;
    private String htmlPageUrlAddress;
    private String htmlContentInStringFormat;
    private String address = "Ocean";
    ListView list;
    ListView list2;
    ProgressDialog dialog;
    int UVIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        list = (ListView) findViewById(R.id.list);
        list2 = (ListView) findViewById(R.id.list2);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();

        Bundle bundle = getIntent().getExtras();
        latLon = bundle.getString("LatLon");

        htmlPageUrlWeather = "http://api.worldweatheronline.com/premium/v1/weather.ashx?key="+key+"&q="+latLon+"&num_of_days=1";
        htmlPageUrlMarine = "http://api.worldweatheronline.com/premium/v1/marine.ashx?key="+key+"&q="+latLon+"&num_of_days=1";
        htmlPageUrlAddress = "http://maps.googleapis.com/maps/api/geocode/xml?latlng="+latLon+"&sensor=true";

        JsoupAsyncTaskAddress jsoupAsyncTaskAddress = new JsoupAsyncTaskAddress();
        jsoupAsyncTaskAddress.execute();

        JsoupAsyncTaskWeather jsoupAsyncTaskWeather = new JsoupAsyncTaskWeather();
        jsoupAsyncTaskWeather.execute();


        final String wavesPre[] = {"⚫ Always swim or surf at places patrolled by surf lifesavers or lifeguards." ,
                "⚫ Swim between the red and yellow flags. They mark the safest area to swim." ,
                "⚫ Always swim under supervision or with a friend." ,
                "⚫ Read and obey the signs." ,
                "⚫ Don't swim directly after a meal." ,
                "⚫ Don't swim under the influence of drugs or alcohol." ,
                "⚫ If you are unsure of surf conditions, ask a lifesaver or lifeguard." ,
                "⚫ Never run and dive in the water. Even if you have checked before, conditions can change." ,
                "⚫ If you get into trouble in the water, don't panic. Raise your arm for help, float and wait for assistance." ,
                "⚫ Float with a current or undertow. Stay calm. Don't try to swim against it. Signal for help and wait for assistance."};

        Button btnPreWave = (Button) findViewById(R.id.btnPreWave);
        btnPreWave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ResultActivity.this);
                alertDialogBuilder.setTitle("Precautions (Waves)");

                alertDialogBuilder
                        .setMessage(wavesPre[new Random().nextInt(wavesPre.length)])
                        .setCancelable(false)
                        .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        Button btnPre = (Button) findViewById(R.id.btnPre);
        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = 0;
                if(UVIndex >= 0 && UVIndex <= 2){
                    index = 0;
                }else if(UVIndex >= 3 && UVIndex <= 5){
                    index = 1;
                }else if(UVIndex >= 6 && UVIndex <= 7){
                    index = 2;
                }else if(UVIndex >= 8 && UVIndex <= 10){
                    index = 3;
                }else if(UVIndex >= 11){
                    index = 4;
                }

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ResultActivity.this);
                alertDialogBuilder.setTitle(titles[index]);

                alertDialogBuilder
                        .setMessage(precautions[index])
                        .setCancelable(false)
                        .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

    }

    //get location adress name
    private class JsoupAsyncTaskAddress extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                htmlDocument = Jsoup.connect(htmlPageUrlAddress).get();
                htmlContentInStringFormat = htmlDocument.html();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            setInfoAddress(htmlContentInStringFormat);
        }
    }

    //download xml
    private class JsoupAsyncTaskWeather extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                htmlDocument = Jsoup.connect(htmlPageUrlWeather).get();
                htmlContentInStringFormat = htmlDocument.html();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            setInfoWeather(htmlContentInStringFormat);
            JsoupAsyncTaskMarine jsoupAsyncTaskMarine = new JsoupAsyncTaskMarine();
            jsoupAsyncTaskMarine.execute();
        }
    }

    //download xml
    private class JsoupAsyncTaskMarine extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                htmlDocument = Jsoup.connect(htmlPageUrlMarine).get();
                htmlContentInStringFormat = htmlDocument.html();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            setInfoMarine(htmlContentInStringFormat);
        }
    }

    void setCodIcon(String code, String time){
        Drawable img = null;
        if(code.equals("315") || code.equals("392") || code.equals("350") || code.equals("338") || code.equals("335") || code.equals("332") || code.equals("329") || code.equals("326") || code.equals("323") || code.equals("320") || code.equals("317") || code.equals("314") || code.equals("311") || code.equals("227") || code.equals("179")){
            img = getResources().getDrawable(R.drawable.ic_snow);
        }
        if(code.equals("389") || code.equals("386") ||code.equals("377") || code.equals("374") || code.equals("371") || code.equals("368") || code.equals("365") || code.equals("362") || code.equals("359") || code.equals("260") || code.equals("200")){
            img = getResources().getDrawable(R.drawable.ic_thunder);
        }
        if(code.equals("356") || code.equals("353") || code.equals("308") || code.equals("305") || code.equals("302") || code.equals("299") || code.equals("296") || code.equals("293") || code.equals("284") || code.equals("281") || code.equals("266") || code.equals("263") || code.equals("266") || code.equals("176")){
            img = getResources().getDrawable(R.drawable.ic_rain);
        }
        if(code.equals("248") || code.equals("230") || code.equals("185") || code.equals("182") || code.equals("230") || code.equals("143") || code.equals("122")){
            img = getResources().getDrawable(R.drawable.ic_fog);
        }
        if(code.equals("119") || code.equals("116")){
            img = getResources().getDrawable(R.drawable.ic_cloud);
        }
        if(code.equals("113")){
            if(returnValue(time, "weatherIconUrl").contains("night")) {
                img = getResources().getDrawable(R.drawable.ic_moon);
            }else{
                img = getResources().getDrawable(R.drawable.sun);
            }
        }

        ImageView icCod = (ImageView) findViewById(R.id.imgStatus);
        icCod.setImageDrawable(img);

    }

    void setInfoAddress(String xml){
        Geocoder geocoder = new Geocoder(ResultActivity.this, Locale.getDefault());

        double latitude = Double.parseDouble(latLon.split(",")[0]);
        double longitude = Double.parseDouble(latLon.split(",")[1]);

        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(latitude,longitude, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }


        String city = "";
        String state = "";
        String country = "";

        if(addresses != null){
            if(addresses.size() > 0) {
                city = addresses.get(0).getLocality();
                state = addresses.get(0).getAdminArea();
                country = addresses.get(0).getCountryName();

                if(city != null && state != null){
                    address = city+" - "+state;
                }

                if(city == null){
                    address = state;
                }

                if(state == null){
                    address = country;
                }
            }
        }
    }

    void setInfoMarine(String xml){
        //List 2
        Drawable img1 = getResources().getDrawable(R.drawable.wave);
        Drawable img2 = getResources().getDrawable(R.drawable.temperature);
        Drawable img3 = getResources().getDrawable(R.drawable.swell);
        Drawable img4 = getResources().getDrawable(R.drawable.visibility);

        //Sig. Height
        InfoItem item = new InfoItem();
        item.setname("Sig. Height");

        if(returnValue(xml, "sigHeight_m").equals("-999.0")){
            item.setinfo("0 m");
        }else {
            item.setinfo(returnValue(xml, "sigHeight_m") + " m");
        }
        item.seticon(img1);
        data2.add(item);

        //Water Temp
        item = new InfoItem();
        item.setname("Temp water");
        item.setinfo(returnValue(xml, "waterTemp_C")+" ºC");
        item.seticon(img2);
        data2.add(item);

        //Swell Height
        item = new InfoItem();
        item.setname("Swell Height");
        if(returnValue(xml, "sigHeight_m").equals("-999.0")) {
            item.setinfo("0 m");
        }else {
            item.setinfo(returnValue(xml, "swellHeight_m") + " m");
        }
        item.seticon(img3);
        data2.add(item);

        //visibility
        item = new InfoItem();
        item.setname("Visibility");
        item.setinfo(returnValue(xml, "visibility")+" Km");
        item.seticon(img4);
        data2.add(item);

        //set adapter list 2
        list2.setAdapter(new CustomListAdapter(ResultActivity.this, data2));
    }

    void setInfoWeather(String xml){
        //icon
        setCodIcon(returnValue(xml, "weatherCode"), xml);
        //list1
        Drawable img5 = getResources().getDrawable(R.drawable.wind);
        Drawable img6 = getResources().getDrawable(R.drawable.compass);
        Drawable img7 = getResources().getDrawable(R.drawable.humidity);
        Drawable img8 = getResources().getDrawable(R.drawable.sun);
        //Wind Speed
        InfoItem item = new InfoItem();
        item.setname("Wind speed");
        item.setinfo(returnValue(xml, "windspeedKmph") + " Km/h");
        item.seticon(img5);
        data.add(item);

        //wind direction
        item = new InfoItem();
        item.setname("Wind dir.");
        item.setinfo(returnValue(xml, "winddir16Point"));
        item.seticon(img6);
        data.add(item);

        //humidity
        item = new InfoItem();
        item.setname("Humidity");
        item.setinfo(returnValue(xml, "humidity")+"%");
        item.seticon(img7);
        data.add(item);

        //uvIndex
        item = new InfoItem();
        item.setname("UV Index");
        item.setinfo(returnValue(xml, "uvIndex"));
        //set UV index
        UVIndex = Integer.parseInt(returnValue(xml, "uvIndex"));
        item.seticon(img8);
        data.add(item);

        //temperature
        TextView txt_city = (TextView)findViewById(R.id.txtCity);
        txt_city.setText(address+", " + returnValue(xml, "temp_C")+" ºC");

        //set adapter list2
        list.setAdapter(new CustomListAdapter(ResultActivity.this, data));

        //remove dialog
        dialog.dismiss();
    }

    //return value from xml
    public String returnValue(String xml, String key){
        return xml.split("<"+key+">")[1].split("</"+key+">")[0].trim();
    }

}
