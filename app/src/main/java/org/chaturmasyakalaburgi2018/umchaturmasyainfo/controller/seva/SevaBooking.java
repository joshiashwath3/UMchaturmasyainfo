package org.chaturmasyakalaburgi2018.umchaturmasyainfo.controller.seva;

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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.chaturmasyakalaburgi2018.umchaturmasyainfo.R;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.activity.AboutUs;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.adapter.ViewSevaListAdapter;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.model.UMCSeva;

import java.util.ArrayList;
import java.util.List;

public class SevaBooking extends AppCompatActivity implements ValueEventListener {

    private RecyclerView recyclerView;
    private ViewSevaListAdapter adapter;
    private List<UMCSeva> listitems;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference rootdatabaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seva_booking);

        firebaseDatabase = FirebaseDatabase.getInstance();
        rootdatabaseReference = firebaseDatabase.getReference();

        rootdatabaseReference.child("UMCSeva").addValueEventListener(this);

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
                    startActivity(new Intent(SevaBooking.this,AboutUs.class));
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
            UMCSeva seva = objDataSnapshot.getValue(UMCSeva.class);

            Log.e("UMCSeva", String.valueOf(seva));
            UMCSeva item = new UMCSeva(
                    seva.getSevaID(),
                    seva.getSevaName(),
                    seva.getSevaAmount(),
                    seva.getSevaURL()
            );
            listitems.add(item);
            adapter = new ViewSevaListAdapter(listitems, getApplicationContext());
            recyclerView.setAdapter(adapter);
        }

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}