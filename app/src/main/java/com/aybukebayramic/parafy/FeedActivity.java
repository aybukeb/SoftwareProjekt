package com.aybukebayramic.parafy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.SupportMenuInflater;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

     public class FeedActivity extends AppCompatActivity {

        private FirebaseAuth firebaseAuth;


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
            Intent intentToExpense=new Intent(FeedActivity.this,ExpenseActivity.class);
            startActivity(intentToExpense);


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
                firebaseAuth=FirebaseAuth.getInstance();



    }


}
