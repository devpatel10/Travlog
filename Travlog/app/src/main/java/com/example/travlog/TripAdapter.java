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

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.travlog.MainActivity.prefConfig;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.TripAdapterHolder> {
    public ArrayList<TripItem> mExampleList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onDeleteClick(int position);
        void onDetailsClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) { mListener=listener;}
    public static class TripAdapterHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;
        public Button DetailBtn,DeleteBtn;

        public TripAdapterHolder(@NonNull View itemView, final OnItemClickListener listener) {

            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView1) ;
            mTextView1=itemView.findViewById(R.id.item_title);
            mTextView2=itemView.findViewById(R.id.item_description);
             DetailBtn = (Button) itemView.findViewById( R.id.detls );
             DeleteBtn = (Button) itemView.findViewById( R.id.delete );
            DetailBtn.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(View v) {
                    if(listener!=null)
                    {
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION)
                        {
                            listener.onDetailsClick(position);
                        }
                    }
                }
            });
            DeleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null)
                    {
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION)
                        {
                            listener.onDeleteClick(position);
                        }
                    }

                }
            });
        }
    }


        public TripAdapter(ArrayList<TripItem> exampleList){
            mExampleList=exampleList;
        }

    @NonNull
    @Override
    public TripAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.map_list_item,parent,false);
        TripAdapterHolder tah=new TripAdapterHolder(v,mListener);
        return tah;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull TripAdapterHolder holder, int position) {
        TripItem currItem = mExampleList.get(position);

        holder.mTextView1.setText(currItem.getText1());
        holder.mTextView2.setText(currItem.getText2());

    }


    @Override
    public int getItemCount() {

        return mExampleList.size();
    }



}
