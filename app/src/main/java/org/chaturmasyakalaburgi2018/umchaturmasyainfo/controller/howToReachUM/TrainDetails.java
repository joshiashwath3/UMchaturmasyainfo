package org.chaturmasyakalaburgi2018.umchaturmasyainfo.controller.howToReachUM;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.LinearLayoutManager;
import androidx.appcompat.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.chaturmasyakalaburgi2018.umchaturmasyainfo.R;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.activity.AboutUs;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.adapter.ViewTrainDetailsAdapter;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.model.Train;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TrainDetails extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ViewTrainDetailsAdapter adapter;
    private List<Train> listitems;
    String nameofCity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_details);

        nameofCity=getIntent().getStringExtra("name");

        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listitems=new ArrayList<>();

        setItems();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.search_view_menu_item, menu);

        MenuItem myActionMenuItem = menu.findItem( R.id.action_search);
        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (adapter != null) adapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (adapter != null) adapter.getFilter().filter(newText);
                return true;
            }
        });

        return true;
    }

    void setItems(){

        String str_data = loadJSONFromAsset();

        try {
            JSONObject jsonObject_country = new JSONObject(str_data);
            JSONArray jsonArray_country = jsonObject_country.getJSONArray("um_train");
            for (int i = 0; i < jsonArray_country.length(); i++) {
                JSONObject jsonObject = jsonArray_country.getJSONObject(i);
                if (jsonObject.getString("cityName").equals(nameofCity)){
                    JSONArray jsonArray_train = jsonObject.getJSONArray("train");
                    ArrayList<Train> trains = new ArrayList<>();
                    for (int j = 0; j < jsonArray_train.length(); j++) {

                        JSONObject jsonObject_train = jsonArray_train.getJSONObject(j);

                        Log.e("jsonObject_train", String.valueOf(jsonObject_train));
                        Train item = new Train(
                                jsonObject_train.getString("trainNameNo"),
                                jsonObject_train.getString("trainFromTo"),
                                jsonObject_train.getString("trainDepartsDay"),
                                jsonObject_train.getString("trainTimeFromToDur")
                        );
                        listitems.add(item);
                        adapter = new ViewTrainDetailsAdapter(listitems, getApplicationContext());
                        recyclerView.setAdapter(adapter);
                    }
                }

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
                    startActivity(new Intent(TrainDetails.this,AboutUs.class));
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
