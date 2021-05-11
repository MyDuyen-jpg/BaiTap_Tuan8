package com.myduyen.baitap_tuan8;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapterRecycler extends RecyclerView.Adapter<CustomAdapterRecycler.ViewHolder> {
    List<User> mNames;
    private OnClickListner listener;
    public CustomAdapterRecycler(List<User> names, OnClickListner listener) {
        mNames = names;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = mNames.get(position);
        holder.tvName.setText(user.getFirstName()+" ");
        holder.imgBtn_sua.setImageResource(R.drawable.edit);
        holder.imgBtn_xoa.setImageResource(R.drawable.daux);
        holder.mUser=  user;

    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageButton imgBtn_sua,imgBtn_xoa;
        User mUser;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.textView);
            imgBtn_sua = itemView.findViewById(R.id.imgBtn_sua);
            imgBtn_xoa = itemView.findViewById(R.id.imgBtn_xoa);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    listener.itemClicklistener(mUser);
                }
            });
            imgBtn_sua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.buttonsuaClick(mUser);
                }
            });
            imgBtn_xoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.buttonxoaClick(mUser);
                }
            });
        }
    }
    public void resetList(List<User> users) {
        mNames = users;
        notifyDataSetChanged();
    }
}
