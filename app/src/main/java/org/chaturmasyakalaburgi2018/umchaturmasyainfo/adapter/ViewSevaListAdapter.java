package org.chaturmasyakalaburgi2018.umchaturmasyainfo.adapter;

import android.content.Context;
import android.content.Intent;
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
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.model.UMCSeva;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 19SIMBLS LLP on 29,June,2018
 *
 * @Author Ashwath Joshi
 */
public class ViewSevaListAdapter extends RecyclerView.Adapter<ViewSevaListAdapter.ViewHolder>  implements Filterable {


    public List<UMCSeva> listitems;
    public Context context;
    protected List<UMCSeva> listSearch;


    public ViewSevaListAdapter(List<UMCSeva> listitems, Context context) {
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
        final UMCSeva listitem=listitems.get(position);

        holder.name.setText(listitem.getSevaID()+" "+listitem.getSevaName());
        holder.email.setText("₹"+listitem.getSevaAmount());

        holder.imageButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, CommonWebView.class);
                i.putExtra("url",listitem.getSevaURL());
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
                    List<UMCSeva> filteredList = new ArrayList<>();
                    for (UMCSeva row : listSearch) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getSevaName().toLowerCase().contains(charString.toLowerCase()) || String.valueOf(row.getSevaAmount()).contains(charSequence )) {
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
                listitems = (ArrayList<UMCSeva>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView email;
        public TextView contact;
        public Button imageButtonEdit;


        public ViewHolder(View itemView) {
            super(itemView);

            name=(TextView)itemView.findViewById(R.id.trainbusno);
            email=(TextView)itemView.findViewById(R.id.discreption);
//            contact=(TextView)itemView.findViewById(R.id.contact);
            imageButtonEdit=(Button)itemView.findViewById(R.id.imageButtonEdit);
        }
    }

}
