package com.spinname.gerg.spinnames;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private ArrayList<String> names;
    private Context context;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onDeleteClick(int position);
    }
    public void onItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public void removeItem(int position){
        names.remove(position);
    }

    RecyclerViewAdapter(ArrayList<String> names, Context context) {
        this.names = names;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.names_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameView.setText(names.get(position));
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameView;
        ConstraintLayout parentLayout;
        ImageView deleteButton;

        ViewHolder(View itemView) {
            super(itemView);

            deleteButton = itemView.findViewById(R.id.removeButton);
            nameView = itemView.findViewById(R.id.nameView);
            parentLayout = itemView.findViewById(R.id.parentlayout);

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }

}
