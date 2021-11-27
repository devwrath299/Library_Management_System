package com.example.mylibrary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter< UsersAdapter.Myviewholder>{
    Context context;
    ArrayList<user> arrayList;

    public UsersAdapter(Context context, ArrayList<user> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public UsersAdapter.Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.allusers,parent,false);
        return  new Myviewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.Myviewholder holder, int position) {
        user users =arrayList.get(position);
        holder.name.setText("Username :"+users.getUsername());
        holder.email.setText("Email :"+users.getEmail());
        holder.address.setText("Address :"+users.getAddress());
        holder.phone.setText("Contact No :"+users.getContact());
        Glide.with(holder.image.getContext()).load(users.getUri()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder {

        TextView name,email,address,phone;
        ImageView image;

        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.uname);
            email=itemView.findViewById(R.id.emi);
            address=itemView.findViewById(R.id.pata);
            phone=itemView.findViewById(R.id.contct);
            image=itemView.findViewById(R.id.imgd);

        }
    }
}
