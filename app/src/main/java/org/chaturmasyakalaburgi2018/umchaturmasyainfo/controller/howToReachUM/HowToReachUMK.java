package org.chaturmasyakalaburgi2018.umchaturmasyainfo.controller.howToReachUM;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.location.LocationListener;

import org.chaturmasyakalaburgi2018.umchaturmasyainfo.MapsActivity;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.R;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.activity.AboutUs;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.activity.CommonWebView;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.activity.MainActivity;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.common.CommonLocation;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.model.CityName;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class HowToReachUMK extends AppCompatActivity {
    Button fromRailwayStation;
    Button fromBusStand;
    Button fromCurrentLocation;
    Button trainDetails;
    Button busDetails;
    Button toWordsPravchana;
    Button toWordsUM;
    ArrayList<CityName> cityName = null;
    ArrayList<CityName> cityNamebus = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_reach_umk);

        fromRailwayStation = (Button)findViewById(R.id.from_railway_station);
        fromBusStand = (Button)findViewById(R.id.from_bus_stand);
        fromCurrentLocation = (Button)findViewById(R.id.from_current_location);
        trainDetails = (Button)findViewById(R.id.train_details);
        busDetails = (Button)findViewById(R.id.bus_details);
        toWordsPravchana = (Button)findViewById(R.id.towordspravachan);
        toWordsUM = (Button)findViewById(R.id.towordsUM);

        cityName = new ArrayList<>();
        cityNamebus = new ArrayList<>();
        fromRailwayStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HowToReachUMK.this,MapsActivity.class)
                        .putExtra("latitude",CommonLocation.railwayStationLat)
                        .putExtra("longitude",CommonLocation.railwayStationLong)
                        .putExtra("from", CommonLocation.railwayStation));
            }
        });
        fromBusStand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HowToReachUMK.this,MapsActivity.class)
                        .putExtra("latitude",CommonLocation.busStandLat)
                        .putExtra("longitude",CommonLocation.busStandLong)
                        .putExtra("from", CommonLocation.busStand));
            }
        });
        fromCurrentLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HowToReachUMK.this,MapsActivity.class)
                        .putExtra("latitude", MainActivity.currentLatitude)
                        .putExtra("longitude",MainActivity.currentLongitude)
                        .putExtra("from",CommonLocation.current));
            }
        });
        toWordsPravchana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HowToReachUMK.this,PravachanaLocation.class)
                        .putExtra("latitude",CommonLocation.umLat)
                        .putExtra("longitude",CommonLocation.umLong)
                        .putExtra("from", CommonLocation.um));
            }
        });
        toWordsUM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HowToReachUMK.this,MapsActivity.class)
                        .putExtra("latitude",CommonLocation.nvCollegeLat)
                        .putExtra("longitude",CommonLocation.nvCollegeLong)
                        .putExtra("from", CommonLocation.nvCollege));
            }
        });
        trainDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callTrain(view);
            }
        });
        busDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBus(view);
            }
        });

        setItems();
    }

    private void callTrain(final View view) {

        final View alertLayout = LayoutInflater.from(view.getContext())
                .inflate(R.layout.list_view,null);

        final ListView listView1 = alertLayout.findViewById(R.id.listview);

        ArrayAdapter<CityName> arrayAdapter = new ArrayAdapter<CityName>(this,android.R.layout.simple_spinner_item, cityName);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listView1.setAdapter(arrayAdapter);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CityName selItem = (CityName) adapterView.getItemAtPosition(i);
                String value= selItem.getcityNameTrain();
                Log.d("city", value);
                Intent intent = new Intent(HowToReachUMK.this,TrainDetails.class);
                intent.putExtra("name",value);
                startActivity(intent);
            }
        });

        new AlertDialog.Builder(view.getContext()).setView(alertLayout)
                .setCancelable(true)
                .setNeutralButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        }).create().show();
    }

    private void callBus(final View view) {

        final View alertLayout = LayoutInflater.from(view.getContext())
                .inflate(R.layout.list_view,null);

        final ListView listView1 = alertLayout.findViewById(R.id.listview);

        ArrayAdapter<CityName> arrayAdapter = new ArrayAdapter<CityName>(this,android.R.layout.simple_spinner_item, cityNamebus);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listView1.setAdapter(arrayAdapter);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CityName selItem = (CityName) adapterView.getItemAtPosition(i);
                String value= selItem.getcityNameTrain();
                Log.d("city", value);
                Intent intent = new Intent(HowToReachUMK.this,BusDetails.class);
                intent.putExtra("name",value);
                startActivity(intent);
            }
        });

        new AlertDialog.Builder(view.getContext()).setView(alertLayout)
                .setCancelable(true)
                .setNeutralButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        }).create().show();
    }
    // Setting headers and childs to expandable listview  um_bus um_train
    void setItems(){
        cityName.clear();
        cityNamebus.clear();
        String str_data = loadJSONFromAsset();

        try {
            JSONObject jsonObject_country = new JSONObject(str_data);
            JSONArray jsonArray_country = jsonObject_country.getJSONArray("um_train");

            for (int i = 0; i < jsonArray_country.length(); i++) {
                CityName cityName1 = new CityName();
                JSONObject jsonObject = jsonArray_country.getJSONObject(i);
                cityName1.setcityNameTrain(jsonObject.getString("cityName"));
                cityName.add(cityName1);
            }

            JSONArray jsonArray_bus = jsonObject_country.getJSONArray("um_bus");
            cityNamebus = new ArrayList<>();
            for (int i = 0; i < jsonArray_bus.length(); i++) {
                CityName cityName1 = new CityName();
                JSONObject jsonObject = jsonArray_bus.getJSONObject(i);
                cityName1.setcityNameTrain(jsonObject.getString("cityName"));
                cityNamebus.add(cityName1);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public String loadJSONFromAsset() {
        String json = null;
        try {

            InputStream is = getAssets().open("umck18howtoreach.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        Log.e( "Json response " , json);
        return json;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
            if (id == android.R.id.home) {
                finish();
            } else { if (id == R.id.action_refresh) {
                finish();
                startActivity(getIntent());
            } else {
                if (id == R.id.action_exit) {
                    System.exit(0);
                    return true;
                }else {
                    if (id == R.id.action_about) {
                        startActivity(new Intent(HowToReachUMK.this,AboutUs.class));
                        return true;
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }


}
