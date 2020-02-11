package com.example.nasadataapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class DataAdaper extends RecyclerView.Adapter<DataAdaper.MyViewHolder> {

    Context mContext;
    List<MyPojo> listitem;
    int[] arr;

    public DataAdaper(Context mContext, List<MyPojo> listitem) {
        this.mContext = mContext;
        this.listitem = listitem;
        this.arr = arr;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_images, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.image_tittle.setText(listitem.get(position).getTitle());
        holder.explanation.setText(listitem.get(position).getExplanation());
        Picasso.with(mContext).load(listitem.get(position).getUrl()).into(holder.myimage);

        holder.myfilesgridlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ImagePreview.class);
                intent.putExtra("IMGTITTLE", listitem.get(position).getTitle());
                intent.putExtra("EXPLAIN", listitem.get(position).getExplanation());
                intent.putExtra("URL", listitem.get(position).getUrl());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listitem != null) {
            return listitem.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView image_tittle, explanation;
        ImageView myimage, img_back;
        RelativeLayout myfilesgridlay;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image_tittle = (TextView) itemView.findViewById(R.id.image_tittle);
            explanation = (TextView) itemView.findViewById(R.id.expln);
            myfilesgridlay = (RelativeLayout) itemView.findViewById(R.id.myfilesgridlay);
            myimage = (ImageView) itemView.findViewById(R.id.myimage);
        }

    }
}
