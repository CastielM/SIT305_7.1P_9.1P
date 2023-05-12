package com.example.lostandfound;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class dataAdapter extends RecyclerView.Adapter<dataAdapter.MyViewHolder>{


    Context MyContext;
    ArrayList<DataModel> lostAndFoundData;

    DataListener listener;


    public dataAdapter(Context myContext, ArrayList<DataModel> lostAndFoundData, DataListener listener) {
        MyContext = myContext;
        this.lostAndFoundData = lostAndFoundData;
        this.listener = listener;


    }

    @NonNull
    @Override
    public dataAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(MyContext).inflate(R.layout.data_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull dataAdapter.MyViewHolder holder, int position) {

        holder.posttype.setTextColor(Color.parseColor("#32CD32"));
        if (Objects.equals(lostAndFoundData.get(position).getPostType(), "Lost"))
        {
            holder.posttype.setTextColor(Color.parseColor("#DC143C"));
            Log.i("colourtest", "Red");
        }


        holder.posttype.setText(lostAndFoundData.get(position).getPostType());
        holder.description.setText(lostAndFoundData.get(position).getDescription());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener(){
        @Override
            public void onClick(View view)
            {
            listener.onItemClick(lostAndFoundData.get(position));
            }
        });


    }

    @Override
    public int getItemCount() {
        return lostAndFoundData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView posttype;
        ConstraintLayout constraintLayout;
        TextView description;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            posttype = itemView.findViewById(R.id.posttype);
            description = itemView.findViewById(R.id.description);
            constraintLayout = itemView.findViewById(R.id.constraintLayout);

        }
    }


}
