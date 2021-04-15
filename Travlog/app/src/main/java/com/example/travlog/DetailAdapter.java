package com.example.travlog;

import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static com.example.travlog.MainActivity.prefConfig;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailAdapterHolder> {
    public ArrayList<ArrayList<String>> mExampleList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void transportClick(int position);
        void hotelClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) { mListener=listener;}
    public static class DetailAdapterHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView s1,s2,s3,s4,s5,s6,s7,s8,s9;
        public DetailAdapterHolder(@NonNull View itemView, final OnItemClickListener listener) {

            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView1) ;
            s1=itemView.findViewById(R.id.s1);
            s2=itemView.findViewById(R.id.s2);
            s3=itemView.findViewById(R.id.s3);
            s4=itemView.findViewById(R.id.s4);
            s5=itemView.findViewById(R.id.s5);
            s6=itemView.findViewById(R.id.s6);
            s7=itemView.findViewById(R.id.s7);
            s8=itemView.findViewById(R.id.s8);
            s9=itemView.findViewById(R.id.s9);

            s8.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(View v) {
                    if(listener!=null )
                    {
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION && position==0)
                        {
                            listener.hotelClick(position);
                        }
                    }
                }
            });
            s9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null)
                    {
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION && position==0)
                        {
                            listener.transportClick(position);
                        }
                    }

                }
            });
        }
    }


    public DetailAdapter(ArrayList<ArrayList<String>> exampleList){
        mExampleList=exampleList;
    }

    @NonNull
    @Override
    public DetailAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_list_item,parent,false);
        DetailAdapterHolder tah=new DetailAdapterHolder(v,mListener);
        return tah;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull DetailAdapterHolder holder, int position) {
        ArrayList<String> currItem = mExampleList.get(position);
        if(position==0)
        {
            holder.mImageView.setImageResource(R.drawable.hangout);
            holder.s1.setText(currItem.get(0));
            holder.s2.setText(currItem.get(1));
            if(currItem.get(2)!=null)
            {
                holder.s3.setText("    Total Expenses: "+currItem.get(5));
                holder.s4.setText("Drive Link"+currItem.get(2));}
            else{
                holder.s4.setText("Total Expenses: "+currItem.get(5));
            }
            holder.s6.setText("Start Date : "+currItem.get(3));
            holder.s7.setText("End Date : " +currItem.get(4));

              holder.s8.setText("Add Hotel Booking   ");
                holder.s9.setText("   Add Transportation");

        }
        else if(currItem.size()==5){
            holder.mImageView.setImageResource(R.drawable.monuments);
            holder.s1.setText(currItem.get(0));
            holder.s2.setText("Address  : "+currItem.get(1));
            holder.s4.setText("Cost  : "+ currItem.get(4));
            holder.s6.setText("Checkin  : "+currItem.get(2));
           // holder.s5.setText(currItem.get(4));
            holder.s8.setText("Checkout  : "+currItem.get(3));
         //   holder.s7.setText(currItem.get(6));
          //  holder.s8.setText(currItem.get(7));*/
            holder.s9.setText("");
        }
        else{
            holder.mImageView.setImageResource(R.drawable.ic_shortcut_directions_car);
            holder.s1.setText(currItem.get(0));
            holder.s2.setText(currItem.get(1));
            holder.s4.setText("From : "+ currItem.get(2));
            holder.s5.setText("To : "+currItem.get(3));
            holder.s6.setText("Start :"+currItem.get(4));
            holder.s8.setText("End  :" + currItem.get(5));
            holder.s3.setText("       Cost :"+ currItem.get(6));
        }
    }


    @Override
    public int getItemCount() {

        return mExampleList.size();
    }



}
