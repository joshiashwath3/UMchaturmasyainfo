package org.chaturmasyakalaburgi2018.umchaturmasyainfo.adapter;

import android.content.Context;
import androidx.appcompat.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.chaturmasyakalaburgi2018.umchaturmasyainfo.R;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.model.Upload;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 19SIMBLS LLP on 31,July,2018
 *
 * @Author Ashwath Joshi
 */
public class ViewUpdatesDetailsAdapter extends RecyclerView.Adapter<ViewUpdatesDetailsAdapter.ViewHolder> {


    public List<Upload> listitems;
    public Context context;
    protected List<Upload> listSearch;

    public ViewUpdatesDetailsAdapter(List<Upload> listitems, Context context) {
        this.listitems = listitems;
        this.context = context;
        this.listSearch = listitems;
    }

    @Override
    public ViewUpdatesDetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_view_updates_list,parent,false);

        return new ViewUpdatesDetailsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewUpdatesDetailsAdapter.ViewHolder holder, final int position) {
        final Upload listitem=listitems.get(position);

        holder.title.setText(listitem.getName());
        holder.date.setText(listitem.getDate());
        holder.subtitle.setText(listitem.getSubTitle());
        holder.discreption.setText(listitem.getDiscreption());

        Glide.with(context)
                .load(listitem.getImageUrl())
                .crossFade()
                .thumbnail(0.5f)
                .into(holder.imageView);


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
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.imageView);
            title=(TextView)itemView.findViewById(R.id.trainbusno);
            subtitle=(TextView)itemView.findViewById(R.id.subTtitle);
            discreption=(TextView)itemView.findViewById(R.id.discreption);
            date=(TextView)itemView.findViewById(R.id.date);
        }
    }


}
