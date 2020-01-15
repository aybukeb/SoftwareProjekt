package com.aybukebayramic.parafy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class BudgetActivity extends AppCompatActivity {
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
            Intent intentToAdd=new Intent(BudgetActivity.this,AddActivity.class);
            startActivity(intentToAdd);

        }
        if(item.getItemId()==R.id.my_expense) {
            Intent intentToExpense=new Intent(BudgetActivity.this, BudgetActivity.class);
            startActivity(intentToExpense);


        }
        if(item.getItemId()==R.id.my_budget) {
            Intent intentToAdd=new Intent(BudgetActivity.this,BudgetActivity.class);
            startActivity(intentToAdd);

        }
        if(item.getItemId()==R.id.my_profil) {
            Intent intentToProfil=new Intent(BudgetActivity.this,ProfilActivity.class);
            startActivity(intentToProfil);

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
    }
}
