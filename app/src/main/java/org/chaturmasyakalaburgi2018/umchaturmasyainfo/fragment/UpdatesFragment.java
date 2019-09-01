package org.chaturmasyakalaburgi2018.umchaturmasyainfo.fragment;

import android.app.Activity;
import android.os.Bundle;
import androidx.core.app.Fragment;
import androidx.appcompat.widget.LinearLayoutManager;
import androidx.appcompat.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.chaturmasyakalaburgi2018.umchaturmasyainfo.R;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.adapter.ViewUpdatesDetailsAdapter;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.model.Upload;

import java.util.ArrayList;
import java.util.List;

public class UpdatesFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ViewUpdatesDetailsAdapter mAdapter;

    private ProgressBar mProgressCircle;

    private DatabaseReference mDatabaseRef;
    private List<Upload> mUploads;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference rootdatabaseReference;

    public UpdatesFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_updates, container, false);

        mRecyclerView = v.findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mProgressCircle =v.findViewById(R.id.progress_circle);

        mUploads = new ArrayList<>();

        firebaseDatabase = FirebaseDatabase.getInstance();
        rootdatabaseReference = firebaseDatabase.getReference();
        rootdatabaseReference.child("Updates").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUploads.clear();
                for (DataSnapshot objDataSnapshot : dataSnapshot.getChildren()) {
                    Upload events = objDataSnapshot.getValue(Upload.class);

                    Log.e("Updates", String.valueOf(events.getName()+
                            events.getImageUrl()+
                            events.getDate()+
                            events.getSubTitle()+
                            events.getDiscreption()));
                    Upload item = new Upload(
                            events.getName(),
                            events.getImageUrl(),
                            events.getDate(),
                            events.getSubTitle(),
                            events.getDiscreption()
                    );

                    mUploads.add(item);
                }

                mAdapter = new ViewUpdatesDetailsAdapter( mUploads,getActivity());

                mRecyclerView.setAdapter(mAdapter);
                mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });
        return v;
    }

}
