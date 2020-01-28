package com.aybukebayramic.parafy;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BudgetRecyclerAdapter extends RecyclerView.Adapter<BudgetRecyclerAdapter.BudgetHolder> {

    private ArrayList<String> userconstCategoryList;
    private ArrayList<String> userconstAmountList;

    public BudgetRecyclerAdapter(ArrayList<String> userconstCategoryList, ArrayList<String> userconstAmountList) {
        this.userconstCategoryList = userconstCategoryList;
        this.userconstAmountList = userconstAmountList;
    }

    @NonNull
    @Override
    public BudgetHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.recycler_row_2,parent,false);
        return new BudgetHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BudgetHolder holder, int position) {
        holder.constamount.setText(userconstAmountList.get(position)+" TL Harcamalıyım");
        holder.constcategory.setText(userconstCategoryList.get(position));

    }

    @Override
    public int getItemCount() {
        return userconstAmountList.size();
    }

    class BudgetHolder extends RecyclerView.ViewHolder {

        TextView constamount;
        TextView constcategory;

        public BudgetHolder(@NonNull View itemView) {
            super(itemView);

            constamount=itemView.findViewById(R.id.rcycler_constAmount);
            constcategory=itemView.findViewById(R.id.rcycler_constCategory);
        }
    }
}
