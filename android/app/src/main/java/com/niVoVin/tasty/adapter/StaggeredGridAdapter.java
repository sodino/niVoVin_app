package com.niVoVin.tasty.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.niVoVin.tasty.R;
import com.niVoVin.tasty.api.bean.Gallery;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/24.
 */
public class StaggeredGridAdapter extends RecyclerView.Adapter<StaggeredGridAdapter.GridHolder> implements View.OnClickListener{

    private List<Gallery> listGallery;

    public void addGallerys(List<Gallery> galleries) {
        if (listGallery == null) {
            listGallery = new LinkedList<>();
        }
        if (galleries != null && galleries.size() > 0) {
            listGallery.addAll(galleries);
        }
    }

    @Override
    public GridHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item_layout, parent, false);

        StaggeredGridAdapter.GridHolder holder = new StaggeredGridAdapter.GridHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(GridHolder holder, int position) {
        Gallery gallery = listGallery.get(position);
        String title = gallery.title;
        holder.txtTitle.setText(title);
    }

    @Override
    public int getItemCount() {
        return listGallery == null ? 0 : listGallery.size();
    }

    @Override
    public void onClick(View v) {

    }

    public class GridHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        public GridHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.title);
        }

    }
}
