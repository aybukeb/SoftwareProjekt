package com.aybukebayramic.parafy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExpenseRecyclerAdapter extends RecyclerView.Adapter<ExpenseRecyclerAdapter.catExpenseHolder> {

    private ArrayList<String> usercatAmountList;
    private ArrayList<String> userSumAmountList;

    public ExpenseRecyclerAdapter(ArrayList<String> usercatAmountList, ArrayList<String> userSumAmountList) {
        this.usercatAmountList = usercatAmountList;
        this.userSumAmountList = userSumAmountList;
    }

    @NonNull
    @Override
    public catExpenseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.recycler_row_catexpense,parent,false);
        return new catExpenseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull catExpenseHolder holder, int position) {
        //rowların içinde neler olacak
        holder.rcycler_sumAmountCat.setText(usercatAmountList.get(position));
        holder.rcycler_sumAmount.setText(userSumAmountList.get(position)+" TL");



    }

    @Override
    public int getItemCount() {
        return usercatAmountList.size();
    }

    class catExpenseHolder extends RecyclerView.ViewHolder {
        //row daki görünümler burada tutuluyor
        TextView rcycler_sumAmountCat;
        TextView rcycler_sumAmount;



        public catExpenseHolder(@NonNull View itemView) {
            super(itemView);

            rcycler_sumAmountCat=itemView.findViewById(R.id.rcycler_sumAmountcat);
            rcycler_sumAmount=itemView.findViewById(R.id.rcycler_sumAmount);

        }
    }
}
