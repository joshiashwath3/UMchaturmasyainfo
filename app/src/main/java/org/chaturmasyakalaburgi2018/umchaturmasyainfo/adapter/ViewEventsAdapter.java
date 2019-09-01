package org.chaturmasyakalaburgi2018.umchaturmasyainfo.adapter;

import android.content.Context;
import androidx.appcompat.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.chaturmasyakalaburgi2018.umchaturmasyainfo.R;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.model.UMCEvents;

import java.util.List;

/**
 * Created by 19SIMBLS LLP on 29,June,2018
 *
 * @Author Ashwath Joshi
 */
public class ViewEventsAdapter extends RecyclerView.Adapter<ViewEventsAdapter.ViewHolder>   {


    public List<UMCEvents> listitems;
    public Context context;
    protected List<UMCEvents> listSearch;


    public ViewEventsAdapter(List<UMCEvents> listitems, Context context) {
        this.listitems = listitems;
        this.context = context;
        this.listSearch = listitems;
    }

    @Override
    public ViewEventsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_view_event_list,parent,false);

        return new ViewEventsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewEventsAdapter.ViewHolder holder, final int position) {
        final UMCEvents listitem=listitems.get(position);

        holder.title.setText(listitem.getEventTitle()+"\n"+listitem.getEventSubTiltle());
        holder.discreption.setText(listitem.getEventDiscription());
//        holder.subtitle.setText(listitem.getEventSubTiltle());
        holder.date.setText(listitem.getEventDate());

    }


    @Override
    public int getItemCount() {
        return listitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public TextView subtitle;
        public TextView discreption;
        public TextView date;
        public RelativeLayout relativeLayout;


        public ViewHolder(View itemView) {
            super(itemView);

            title=(TextView)itemView.findViewById(R.id.trainbusno);
//            subtitle=(TextView)itemView.findViewById(R.id.event_subTtitle);
            discreption=(TextView)itemView.findViewById(R.id.discreption);
            date=(TextView)itemView.findViewById(R.id.event_date);
        }
    }

}

