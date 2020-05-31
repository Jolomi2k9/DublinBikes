

/*
Name: Oritsejolomi Sillo
Student Number: 20091
*/

package com.example.dublinbikes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class dublin_bikes_adapter extends RecyclerView.Adapter<dublin_bikes_adapter.dublin_bikes_ViewHolder> {
    private Context mContext;
    private ArrayList<dublin_bikes_item> mDublin_bikes_list;
    private onItemClickListener mListener;

    //forwards our click to the main activity
    public interface onItemClickListener{
        void onItemClick(int position);
    }

    //sets this activity to the onItemClicklistener
    public void setOnItemClickListener(onItemClickListener listener){
        mListener = listener;
    }

    public dublin_bikes_adapter(Context context, ArrayList<dublin_bikes_item> dublin_bikes_list){
        mContext = context;
        mDublin_bikes_list = dublin_bikes_list;
    }

    @NonNull
    @Override
    public dublin_bikes_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.dublin_bikes,parent,false);
        return new dublin_bikes_ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull dublin_bikes_ViewHolder holder, int position) {
        dublin_bikes_item currentItem = mDublin_bikes_list.get(position);

        String name = currentItem.getmName();
        String Status = currentItem.getmStatus();
        String city = currentItem.getmContract_name();

        holder.mTextViewName.setText("Station: "+name);
        holder.mTextViewStatus.setText(Status);
        holder.mTextViewContract_name.setText("City: "+city);
    }

    @Override
    public int getItemCount() {
        return mDublin_bikes_list.size();
    }

    public class dublin_bikes_ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextViewContract_name;
        public TextView mTextViewName;
        public TextView mTextViewAddress;
        public TextView mTextViewPositionLat;
        public TextView mTextViewPositionLng;
        public TextView mTextViewBike_stands;
        public TextView mTextViewAvailable_bike_stands;
        public TextView mTextViewAvailable_bikes;
        public TextView mTextViewStatus;


         public dublin_bikes_ViewHolder(@NonNull View itemView) {
            super(itemView);
             mTextViewName = itemView.findViewById(R.id.text_view_Name);
             mTextViewStatus = itemView.findViewById(R.id.text_view_Status);
             mTextViewContract_name = itemView.findViewById(R.id.text_view_Contract_name);

             //set our onclick listener on itemView
             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     //check if we actually have a listener
                     if(mListener != null){
                         int position = getAdapterPosition();
                         //check if the position is still valid
                         if(position != RecyclerView.NO_POSITION){
                             mListener.onItemClick(position);
                         }
                     }
                 }
             });
        }
    }
}
