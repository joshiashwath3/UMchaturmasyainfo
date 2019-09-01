package org.chaturmasyakalaburgi2018.umchaturmasyainfo.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutManager;
import androidx.appcompat.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.chaturmasyakalaburgi2018.umchaturmasyainfo.R;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.activity.AboutUs;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.adapter.ViewVideoListAdapter;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.model.UMUrls;

import java.util.ArrayList;
import java.util.List;

public class VideoPravachana extends AppCompatActivity implements ValueEventListener {

    private RecyclerView recyclerView;
    private ViewVideoListAdapter adapter;
    private List<UMUrls> listitems;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference rootdatabaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_pravachana);

        firebaseDatabase = FirebaseDatabase.getInstance();
        rootdatabaseReference = firebaseDatabase.getReference();
        rootdatabaseReference.child("UMUrls").child("3").child("UMUrls").addValueEventListener(this);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listitems=new ArrayList<>();

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
                    startActivity(new Intent(VideoPravachana.this,AboutUs.class));
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

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {

        for (DataSnapshot objDataSnapshot : dataSnapshot.getChildren()){
            UMUrls seva = objDataSnapshot.getValue(UMUrls.class);

            Log.e("UMUrls", String.valueOf(seva));
            UMUrls item = new UMUrls(
                    seva.getUrlId(),
                    seva.getUrldate(),
                    seva.getUrlName(),
                    seva.getUrlUrl()
            );
            listitems.add(item);
            adapter = new ViewVideoListAdapter(listitems, getApplicationContext());
            recyclerView.setAdapter(adapter);
        }

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
