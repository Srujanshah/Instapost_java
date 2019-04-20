package com.example.instapost_java;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HashTagListAdapter extends RecyclerView.Adapter<HashTagListAdapter.RecyclerViewHolder> {

    private List<String> mHashTags = new ArrayList<>();
    private Context context;

    public HashTagListAdapter(List<String> mHashTags, Context context){
        this.mHashTags = mHashTags;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.user_card, viewGroup, false);
        return new HashTagListAdapter.RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int i) {
        //final Upload upload = mHashTags.get(i);
        recyclerViewHolder.nameTextView.setText("#" + mHashTags.get(i));
        recyclerViewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(context, HashTagImagesActivity.class);
                String s = mHashTags.get(position);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("HashTag", s);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mHashTags.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView nameTextView;
        private ItemClickListener itemClickListener;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.username_text_view);
            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition());
        }
    }
}
