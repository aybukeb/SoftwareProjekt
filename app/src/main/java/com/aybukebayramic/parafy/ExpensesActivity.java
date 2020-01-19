package com.aybukebayramic.parafy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class ExpensesActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.options_menu,menu);
        return super.onCreateOptionsMenu(menu);



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.add_expense) {
            Intent intentToAdd=new Intent(ExpensesActivity.this,AddActivity.class);
            startActivity(intentToAdd);

        }
        if(item.getItemId()==R.id.my_expense) {
            Intent intentToExpense=new Intent(ExpensesActivity.this, ExpensesActivity.class);
            startActivity(intentToExpense);


        }
        if(item.getItemId()==R.id.my_budget) {
            Intent intentToAdd=new Intent(ExpensesActivity.this,BudgetActivity.class);
            startActivity(intentToAdd);

        }
        if(item.getItemId()==R.id.my_profil) {
            Intent intentToProfil=new Intent(ExpensesActivity.this,ProfilActivity.class);
            startActivity(intentToProfil);

        }
        if(item.getItemId()==R.id.sign_out) {

            firebaseAuth.signOut();
            Intent intentToSign=new Intent(ExpensesActivity.this,SignActivity.class);
            startActivity(intentToSign);
            finish();


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
    }
}