package org.chaturmasyakalaburgi2018.umchaturmasyainfo.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.chaturmasyakalaburgi2018.umchaturmasyainfo.MapsActivity;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.R;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.common.CommonLocation;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.model.Train;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 19SIMBLS LLP on 10,July,2018
 *
 * @Author Ashwath Joshi
 */
public class ViewTrainDetailsAdapter extends RecyclerView.Adapter<ViewTrainDetailsAdapter.ViewHolder>  implements Filterable {


    public List<Train> listitems;
    public Context context;
    protected List<Train> listSearch;

    String[] list = {"From Central Bus Stand to Venue ","From Railway Station to Venue ","From Current Location to Venue ",
    "From Jewargi Cross to Venue ","From Ram Mandir Circle Location to Venue "};

    public ViewTrainDetailsAdapter(List<Train> listitems, Context context) {
        this.listitems = listitems;
        this.context = context;
        this.listSearch = listitems;
    }

    public void updateData(int position, Train Train) {
        listitems.remove(position);
        listitems.add(position, Train);
        notifyItemChanged(position);
//        notifyDataSetChanged();
    }
    public void addItem(int position, Train Train) {
        listitems.add(position, Train);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        listitems.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public ViewTrainDetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_train_bus_list,parent,false);

        return new ViewTrainDetailsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewTrainDetailsAdapter.ViewHolder holder, final int position) {
        final Train listitem=listitems.get(position);

        holder.trainbusno.setText(listitem.getTrainNameNo());
        holder.train_from_to.setText(listitem.getTrainFromTo());
        holder.train_departur_day.setText(listitem.getTrainDepartsDay());
        holder.train_timeing.setText(listitem.getTrainTimeFromToDur());

        holder.imageButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                View alertLayout = LayoutInflater.from(view.getContext())
                        .inflate(R.layout.list_view,null);

                final ListView listView = alertLayout.findViewById(R.id.listview);

                ArrayAdapter arrayAdapter = new ArrayAdapter(view.getContext(),R.layout.support_simple_spinner_dropdown_item,list);
                listView.setAdapter(arrayAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        if (i == 0){
                            Log.d("city", list[i]);
                            Intent intent = new Intent(view.getContext(),MapsActivity.class);
                            intent.putExtra("latitude", CommonLocation.busStandLat);
                            intent.putExtra("longitude",CommonLocation.busStandLong);
                            intent.putExtra("from", CommonLocation.busStand);
                            view.getContext().startActivity(intent);
                        }else if (i == 1){
                            Log.d("city", list[i]);
                            Intent intent = new Intent(view.getContext(),MapsActivity.class);
                            intent.putExtra("latitude", CommonLocation.railwayStationLat);
                            intent.putExtra("longitude",CommonLocation.railwayStationLong);
                            intent.putExtra("from", CommonLocation.railwayStation);
                            view.getContext().startActivity(intent);
                        }else if (i == 2){
                            Log.d("city", list[i]);
                            Intent intent = new Intent(view.getContext(),MapsActivity.class);
                            intent.putExtra("latitude", CommonLocation.currentLat);
                            intent.putExtra("longitude",CommonLocation.currentLong);
                            intent.putExtra("from", CommonLocation.current);
                            view.getContext().startActivity(intent);
                        }else if (i == 3){
                            Log.d("city", list[i]);
                            Intent intent = new Intent(view.getContext(),MapsActivity.class);
                            intent.putExtra("latitude", CommonLocation.jewargiCrossLat);
                            intent.putExtra("longitude",CommonLocation.jewargiCrossLong);
                            intent.putExtra("from", CommonLocation.jewargiCross);
                            view.getContext().startActivity(intent);
                        }else if (i == 4){
                            Log.d("city", list[i]);
                            Intent intent = new Intent(view.getContext(),MapsActivity.class);
                            intent.putExtra("latitude", CommonLocation.ramMandirCircleLat);
                            intent.putExtra("longitude",CommonLocation.ramMandirCircleLong);
                            intent.putExtra("from", CommonLocation.ramMandirCircle);
                            view.getContext().startActivity(intent);
                        }
                    }
                });
                new AlertDialog.Builder(view.getContext()).setView(alertLayout)
                        .setView(alertLayout)
                        .setCancelable(true)
                        .setNeutralButton(
                                "Cancel", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create().show();
            }
        });



    }


    @Override
    public int getItemCount() {
        return listitems.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    listitems = listSearch;
                } else {
                    List<Train> filteredList = new ArrayList<>();
                    for (Train row : listSearch) {
                        if (row.getTrainNameNo().toLowerCase().contains(charString.toLowerCase()) || String.valueOf(row.getTrainTimeFromToDur()).contains(charSequence )) {
                            filteredList.add(row);
                        }
                    }

                    listitems = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listitems;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listitems = (ArrayList<Train>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView trainbusno;
        public TextView train_from_to;
        public TextView train_departur_day;
        public TextView train_timeing;
        public Button imageButtonEdit;
        public RelativeLayout relativeLayout;


        public ViewHolder(View itemView) {
            super(itemView);

            trainbusno=(TextView)itemView.findViewById(R.id.trainbusno);
            train_from_to=(TextView)itemView.findViewById(R.id.train_from_to);
            train_departur_day=(TextView)itemView.findViewById(R.id.train_departur_day);
            train_timeing=(TextView)itemView.findViewById(R.id.train_timeing);
            imageButtonEdit=(Button)itemView.findViewById(R.id.imageButtonEdit);
            relativeLayout=(RelativeLayout)itemView.findViewById(R.id.relativelayout_view);
        }
    }

}
