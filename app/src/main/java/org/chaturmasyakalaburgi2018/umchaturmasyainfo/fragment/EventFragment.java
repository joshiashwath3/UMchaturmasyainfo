package org.chaturmasyakalaburgi2018.umchaturmasyainfo.fragment;


import android.os.Bundle;
import androidx.core.app.Fragment;
import androidx.appcompat.widget.LinearLayoutManager;
import androidx.appcompat.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.chaturmasyakalaburgi2018.umchaturmasyainfo.R;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.adapter.ViewEventsAdapter;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.model.UMCEvents;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventFragment extends Fragment {

    private RecyclerView recyclerView;
    private ViewEventsAdapter adapter;
    private List<UMCEvents> padaPoojaDatesList;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference rootdatabaseReference;
    public EventFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_event, container, false);
        padaPoojaDatesList = new ArrayList<UMCEvents>();

        recyclerView=(RecyclerView)v.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        firebaseDatabase = FirebaseDatabase.getInstance();
        rootdatabaseReference = firebaseDatabase.getReference();
        rootdatabaseReference.child("UMCEvents").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot objDataSnapshot : dataSnapshot.getChildren()) {
                    UMCEvents events = objDataSnapshot.getValue(UMCEvents.class);

                    Log.e("Event", String.valueOf(events.getEventDate()));
                    UMCEvents item = new UMCEvents(
                            events.getEventId(),
                            events.getEventTitle(),
                            events.getEventDiscription(),
                            events.getEventSubTiltle(),
                            events.getEventDate()
                    );
                    padaPoojaDatesList.add(item);
                    adapter = new ViewEventsAdapter(padaPoojaDatesList, getActivity());
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return v;
    }

}
