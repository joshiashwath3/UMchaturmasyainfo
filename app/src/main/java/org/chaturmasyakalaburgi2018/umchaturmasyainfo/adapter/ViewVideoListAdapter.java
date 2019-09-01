package org.chaturmasyakalaburgi2018.umchaturmasyainfo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
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
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.controller.LivePravachana;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.model.UMUrls;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 19SIMBLS LLP on 04,September,2018
 *
 * @Author Ashwath Joshi
 */
public class ViewVideoListAdapter extends RecyclerView.Adapter<ViewVideoListAdapter.ViewHolder>  implements Filterable {


    public List<UMUrls> listitems;
    public Context context;
    protected List<UMUrls> listSearch;


    public ViewVideoListAdapter(List<UMUrls> listitems, Context context) {
        this.listitems = listitems;
        this.context = context;
        this.listSearch = listitems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_view_seva_list,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final UMUrls listitem=listitems.get(position);

        holder.name.setText(listitem.getUrldate());
        holder.email.setText(listitem.getUrlName());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, LivePravachana.class);
                i.putExtra("url",listitem.getUrlUrl());
                v.getContext().startActivity(i);
            }
        });

        holder.imageButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, LivePravachana.class);
                i.putExtra("url",listitem.getUrlUrl());
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
                    List<UMUrls> filteredList = new ArrayList<>();
                    for (UMUrls row : listSearch) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getUrldate().toLowerCase().contains(charString.toLowerCase()) || String.valueOf(row.getUrlName()).contains(charSequence )) {
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
                listitems = (ArrayList<UMUrls>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView email;
        public TextView contact;
        public Button imageButtonEdit;
        public ConstraintLayout relativeLayout;


        public ViewHolder(View itemView) {
            super(itemView);

            name=(TextView)itemView.findViewById(R.id.trainbusno);
            email=(TextView)itemView.findViewById(R.id.discreption);
//            contact=(TextView)itemView.findViewById(R.id.contact);
            imageButtonEdit=(Button)itemView.findViewById(R.id.imageButtonEdit);
            imageButtonEdit.setText("Watch Now");
            relativeLayout=(ConstraintLayout)itemView.findViewById(R.id.relativelayout_view);
        }
    }

}
