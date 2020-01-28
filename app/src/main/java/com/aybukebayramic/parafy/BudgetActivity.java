package com.aybukebayramic.parafy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class BudgetActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    ArrayList<String> userConstAmountfromFB;
    ArrayList<String> usercategoryNames2fromFB;
    BudgetRecyclerAdapter budgetRecyclerAdapter;
    String uid;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.options_menu,menu);
        return super.onCreateOptionsMenu(menu);



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.add_expense) {
            Intent intentToAdd=new Intent(BudgetActivity.this,AddActivity.class);
            startActivity(intentToAdd);

        }
        if(item.getItemId()==R.id.my_expense) {
            Intent intentToExpense=new Intent(BudgetActivity.this, ExpensesActivity.class);
            startActivity(intentToExpense);


        }
        if(item.getItemId()==R.id.my_budget) {
            Intent intentToAdd=new Intent(BudgetActivity.this,BudgetActivity.class);
            startActivity(intentToAdd);

        }

        if(item.getItemId()==R.id.sign_out) {
            firebaseAuth.signOut();
            Intent intentToSign=new Intent(BudgetActivity.this,SignActivity.class);
            startActivity(intentToSign);
            finish();


        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        usercategoryNames2fromFB=new ArrayList<>();
        userConstAmountfromFB=new ArrayList<>();

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        uid=firebaseAuth.getCurrentUser().getUid();

        getBudgetsfromFirestore();
        RecyclerView recyclerView=findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        budgetRecyclerAdapter=new BudgetRecyclerAdapter(usercategoryNames2fromFB,userConstAmountfromFB);
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.recyclerview_divider));
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(budgetRecyclerAdapter);
    }


    public void create_budget(View view) {
        Intent intent=new Intent(BudgetActivity.this,CreateBudgetActivity.class);
        startActivity(intent);
    }

     public void getBudgetsfromFirestore() {
         CollectionReference collectionReference = firebaseFirestore.collection("Budgets");
         collectionReference.whereEqualTo("useruid",uid).addSnapshotListener(new EventListener<QuerySnapshot>() {
             @Override
             public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                 if (e != null) {
                     Toast.makeText(BudgetActivity.this, e.getLocalizedMessage().toString(), Toast.LENGTH_LONG).show();
                 }
                 if (queryDocumentSnapshots != null) {

                     for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {

                         Map<String, Object> data = snapshot.getData();
                         if (data != null) {
                             String amountconst = String.valueOf((String) data.get("amountconst"));
                             String categoryNames2 = (String) data.get("catname2");


                             userConstAmountfromFB.add(amountconst);
                             usercategoryNames2fromFB.add(categoryNames2);

                             budgetRecyclerAdapter.notifyDataSetChanged();

                         } else {
                             Toast.makeText(BudgetActivity.this, "null", Toast.LENGTH_LONG).show();
                         }
                     }
                 }

             }
         });
     }
}
