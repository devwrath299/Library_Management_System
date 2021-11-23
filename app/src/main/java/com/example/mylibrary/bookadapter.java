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

public class bookadapter extends RecyclerView.Adapter< bookadapter.Myviewholder>
{

    Context context;
    ArrayList<user>arrayList;

    public bookadapter(Context context, ArrayList<user> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(context).inflate(R.layout.booksinglerow,parent,false);
       return  new Myviewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, int position) {

        user users =arrayList.get(position);
        holder.title.setText("Title :"+users.getTitle());
        holder.author.setText("Author :"+users.getAuthor());
        holder.bookid.setText("BookId :"+users.getBookid());
        holder.cost.setText("cost :"+users.getCost());
        holder.quantity.setText("quantity :"+users.getQuantity());
//        holder.bookshelf.setText(users.getBookshelf());
        Glide.with(holder.image.getContext()).load(R.drawable.library).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder
    {
       TextView title,bookid,author,cost,quantity,bookshelf;
       ImageView image;

        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            bookid=itemView.findViewById(R.id.bkid);
            author=itemView.findViewById(R.id.author);
            cost=itemView.findViewById(R.id.costs);
            quantity=itemView.findViewById(R.id.quantitys);
            bookshelf=itemView.findViewById(R.id.bookshelfno);
            image=itemView.findViewById(R.id.imgd);

        }
    }

}