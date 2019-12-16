package com.aybukebayramic.parafy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FeedRecyclerAdapter extends RecyclerView.Adapter<FeedRecyclerAdapter.ExpenseHolder> {

    private ArrayList<String> amountList;
    private ArrayList<String> catnameList;
    private ArrayList<String> imageList;
    private ArrayList<String> commentList;

    public FeedRecyclerAdapter(ArrayList<String> amountList, ArrayList<String> catnameList, ArrayList<String> imageList, ArrayList<String> commentList) {
        this.amountList = amountList;
        this.catnameList = catnameList;
        this.imageList = imageList;
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public ExpenseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //viewholder oluşturulunca ne yapılacak

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.recycler_row,parent,false);
        return new ExpenseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseHolder holder, int position) {
        //viewholdera bağlanılınca ne yapılacak
        holder.amountText.setText(amountList.get(position));
        holder.catnameText.setText(catnameList.get(position));
        holder.commentText.setText(commentList.get(position));
        Picasso.get().load(imageList.get(position)).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return amountList.size();
    }

    class ExpenseHolder extends RecyclerView.ViewHolder {

        //layouttaki görünümleri tutmak için

        TextView amountText;
        TextView catnameText;
        ImageView imageView;
        TextView commentText;


        public ExpenseHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.recyclerView_imageView);
            amountText=itemView.findViewById(R.id.recyclerView_amount);
            catnameText=itemView.findViewById(R.id.recyclerView_category);
            commentText=itemView.findViewById(R.id.recyclerView_comment);

        }
    }

}
