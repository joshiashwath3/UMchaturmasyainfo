package org.chaturmasyakalaburgi2018.umchaturmasyainfo.adapter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.chaturmasyakalaburgi2018.umchaturmasyainfo.R;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.activity.CommonWebView;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.model.Help;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.model.UMCSeva;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 19SIMBLS LLP on 03,August,2018
 *
 * @Author Ashwath Joshi
 */
public class ViewHelpListAdapter extends RecyclerView.Adapter<ViewHelpListAdapter.ViewHolder>  implements Filterable {


    public List<Help> listitems;
    public Context context;
    protected List<Help> listSearch;

    public ViewHelpListAdapter(List<Help> listitems, Context context) {
        this.listitems = listitems;
        this.context = context;
        this.listSearch = listitems;
    }


    @Override
    public ViewHelpListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_view_helpline_list,parent,false);

        return new ViewHelpListAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHelpListAdapter.ViewHolder holder, final int position) {
        final Help listitem=listitems.get(position);

        holder.name.setText(listitem.getHelpName());
        holder.email.setText(listitem.getHelpContact());



        holder.imageButtonEdit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {
                String no="tel:+91"+listitem.getHelpContact();
                Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse(no));
                view.getContext().startActivity(i);
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
                    List<Help> filteredList = new ArrayList<>();
                    for (Help row : listSearch) {
                        if (row.getHelpName().toLowerCase().contains(charString.toLowerCase()) || String.valueOf(row.getHelpContact()).contains(charSequence )) {
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
                listitems = (ArrayList<Help>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView email;
        public TextView contact;
        public Button imageButtonEdit;
        public RelativeLayout relativeLayout;


        public ViewHolder(View itemView) {
            super(itemView);

            name=(TextView)itemView.findViewById(R.id.trainbusno);
            email=(TextView)itemView.findViewById(R.id.discreption);
//            contact=(TextView)itemView.findViewById(R.id.contact);
            imageButtonEdit=(Button)itemView.findViewById(R.id.imageButtonEdit);
            relativeLayout=(RelativeLayout)itemView.findViewById(R.id.relativelayout_view);
        }
    }

}
