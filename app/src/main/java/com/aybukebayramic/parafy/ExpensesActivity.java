package com.aybukebayramic.parafy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
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

public class ExpensesActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    String uid;
    long sum;
    ArrayList<String> userSumAmount;
    ArrayList<String> usersumAmountCategory;
    ExpenseRecyclerAdapter expenseRecyclerAdapter;

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_expense) {
            Intent intentToAdd = new Intent(ExpensesActivity.this, AddActivity.class);
            startActivity(intentToAdd);

        }
        if (item.getItemId() == R.id.my_expense) {
            Intent intentToExpense = new Intent(ExpensesActivity.this, ExpensesActivity.class);
            startActivity(intentToExpense);


        }
        if (item.getItemId() == R.id.my_budget) {
            Intent intentToAdd = new Intent(ExpensesActivity.this, BudgetActivity.class);
            startActivity(intentToAdd);

        }
        if (item.getItemId() == R.id.my_profil) {
            Intent intentToProfil = new Intent(ExpensesActivity.this, ProfilActivity.class);
            startActivity(intentToProfil);

        }
        if (item.getItemId() == R.id.sign_out) {

            firebaseAuth.signOut();
            Intent intentToSign = new Intent(ExpensesActivity.this, SignActivity.class);
            startActivity(intentToSign);
            finish();


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);


        usersumAmountCategory=new ArrayList<>();
        userSumAmount=new ArrayList<>();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        uid = firebaseAuth.getCurrentUser().getUid();

        getCatsAmountfromFirestore();

        RecyclerView recyclerView=findViewById(R.id.recyclerView_catExpense);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        expenseRecyclerAdapter=new ExpenseRecyclerAdapter(usersumAmountCategory,userSumAmount);
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.recyclerview_divider));
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(expenseRecyclerAdapter);


    }

    public void getCatsAmountfromFirestore() {
        CollectionReference collectionReference = firebaseFirestore.collection("Expenses");

        collectionReference.whereEqualTo("useruid", uid).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if (e != null) {
                    Toast.makeText(ExpensesActivity.this, e.getLocalizedMessage().toString(), Toast.LENGTH_LONG).show();
                }
                if (queryDocumentSnapshots != null) {

                    for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {

                            Map<String, Object> data = snapshot.getData();

                            if(data!=null) {

                                String cat = (String) data.get("categoryNames");
                                String amount = String.valueOf((String) data.get("amount"));



                            if (cat == "Gıda") {
                                sum=0;
                                sum += Long.valueOf((Long) data.get("amount"));

                                }

                            if (cat == "Giyim") {
                                sum=0;
                                sum += Long.valueOf((Long) data.get(amount));
                            }
                            if (cat == "Faturalar") {
                                sum=0;
                                sum += Long.valueOf((Long) data.get(amount));

                            }
                            if (cat == "Taksitler") {
                                sum=0;
                                sum += Long.valueOf((Long) data.get(amount));

                            }
                            if (cat == "Kira") {
                                sum=0;
                                sum += Long.valueOf((Long) data.get(amount));

                            }
                            if (cat == "Hediyeler") {
                                sum=0;
                                sum += Long.valueOf((Long) data.get(amount));

                            }
                            if (cat == "Ev Alışverişi") {
                                sum=0;
                                sum += Long.valueOf((Long) data.get(amount));
                            }
                            if (cat == "Sağlık") {
                                sum=0;
                                sum += Long.valueOf((Long) data.get(amount));
                            }
                            if (cat == "Spor") {
                                sum=0;
                                sum += Long.valueOf((Long) data.get(amount));
                            }
                            if (cat == "Eğlence") {
                                sum=0;
                                sum += Long.valueOf((Long) data.get(amount));
                            }
                            if (cat == "Hobi") {
                                sum=0;
                                sum += Long.valueOf((Long) data.get(amount));
                            }
                            if (cat == "Eğitim") {
                                sum=0;
                                sum += Long.valueOf((Long) data.get(amount));
                            }

                            userSumAmount.add(Long.toString(sum));
                            usersumAmountCategory.add(cat);

                        } else {
                            Toast.makeText(ExpensesActivity.this, "null", Toast.LENGTH_LONG).show();
                        }
                        expenseRecyclerAdapter.notifyDataSetChanged();


                    }
                }
            }
        });

    }


}
