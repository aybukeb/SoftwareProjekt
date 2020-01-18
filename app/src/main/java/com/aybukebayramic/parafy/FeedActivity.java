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
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class FeedActivity extends AppCompatActivity {

        private FirebaseAuth firebaseAuth;
        private FirebaseFirestore firebaseFirestore;
        ArrayList<String> userAmountfromFB;
        ArrayList<String> usercategoryNamesfromFB;
        ArrayList<String> userCommentfromFB;
        ArrayList<String> userImagefromFB;
        //ArrayList<String> useremailfromFB;
        ArrayList<String> userDatefromFB;
        FeedRecyclerAdapter feedRecyclerAdapter;
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
            Intent intentToAdd=new Intent(FeedActivity.this,AddActivity.class);
            startActivity(intentToAdd);

        }
        if(item.getItemId()==R.id.my_expense) {
            Intent intentToExpense=new Intent(FeedActivity.this, BudgetActivity.class);
            startActivity(intentToExpense);


        }
        if(item.getItemId()==R.id.my_budget) {
            Intent intentToAdd=new Intent(FeedActivity.this,BudgetActivity.class);
            startActivity(intentToAdd);

        }
        if(item.getItemId()==R.id.my_profil) {
            Intent intentToProfil=new Intent(FeedActivity.this,ProfilActivity.class);
            startActivity(intentToProfil);

        }
        if(item.getItemId()==R.id.sign_out) {
            firebaseAuth.signOut();
            Intent intentToSign=new Intent(FeedActivity.this,SignActivity.class);
            startActivity(intentToSign);
            finish();


        }
        return super.onOptionsItemSelected(item);
    }





    @Override
             protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_feed);

                userAmountfromFB=new ArrayList<>();
                usercategoryNamesfromFB=new ArrayList<>();
                userCommentfromFB=new ArrayList<>();
                userImagefromFB=new ArrayList<>();
                //useremailfromFB=new ArrayList<>();
                userDatefromFB=new ArrayList<>();




                firebaseAuth=FirebaseAuth.getInstance();
                firebaseFirestore=FirebaseFirestore.getInstance();
                uid=firebaseAuth.getCurrentUser().getUid();

                getDataFromFirestore();

                RecyclerView recyclerView=findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
                feedRecyclerAdapter=new FeedRecyclerAdapter(userAmountfromFB,usercategoryNamesfromFB,userImagefromFB,userCommentfromFB,userDatefromFB);
                DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
                dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.recyclerview_divider));
                recyclerView.addItemDecoration(dividerItemDecoration);
                recyclerView.setAdapter(feedRecyclerAdapter);


    }
            public void getDataFromFirestore() {

                    CollectionReference collectionReference = firebaseFirestore.collection("Expenses");

                    collectionReference.whereEqualTo("useruid",uid).addSnapshotListener(new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                                if (e != null) {
                                    Toast.makeText(FeedActivity.this, e.getLocalizedMessage().toString(), Toast.LENGTH_LONG).show();
                                }
                                if (queryDocumentSnapshots != null) {

                                    for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {

                                        Map<String, Object> data = snapshot.getData();
                                        if (data != null) {
                                            String amount = String.valueOf((String) data.get("amount"));
                                            String categoryNames = (String) data.get("categoryNames");
                                            String comment = (String) data.get("comment");
                                            String downloadUrl = (String) data.get("downloadurl");
                                            //String userEmail=(String) data.get("useremail");
                                            String date = (String) data.get("calendardate");

                                            userAmountfromFB.add(amount);
                                            usercategoryNamesfromFB.add(categoryNames);
                                            userCommentfromFB.add(comment);
                                            userImagefromFB.add(downloadUrl);
                                            userDatefromFB.add(date);
                                        } else {
                                            Toast.makeText(FeedActivity.this, "null", Toast.LENGTH_LONG).show();
                                        }
                                        //useremailfromFB.add(userEmail);

                                        feedRecyclerAdapter.notifyDataSetChanged();


                                    }
                                }
                            }
                        });



                }
                    }




