package com.aybukebayramic.parafy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class ProfilActivity extends AppCompatActivity {

    Button saveWallet;
    EditText walletEdit;
    EditText incomeEdit;
    TextView walletText;
    TextView incomeText;

    private FirebaseAuth firebaseAuth;


    float fl;
    String st;
    int in;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.options_menu,menu);
        return super.onCreateOptionsMenu(menu);



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.add_expense) {
            Intent intentToAdd=new Intent(ProfilActivity.this,AddActivity.class);
            startActivity(intentToAdd);

        }
        if(item.getItemId()==R.id.my_expense) {
            Intent intentToExpense=new Intent(ProfilActivity.this, ExpensesActivity.class);
            startActivity(intentToExpense);


        }
        if(item.getItemId()==R.id.my_budget) {
            Intent intentToBudget=new Intent(ProfilActivity.this,BudgetActivity.class);
            startActivity(intentToBudget);

        }
        if(item.getItemId()==R.id.my_profil) {
            Intent intentToProfil=new Intent(ProfilActivity.this,ProfilActivity.class);
            startActivity(intentToProfil);

        }
        if(item.getItemId()==R.id.sign_out) {
            firebaseAuth.signOut();
            Intent intentToSign=new Intent(ProfilActivity.this,SignActivity.class);
            startActivity(intentToSign);
            finish();


        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        firebaseAuth=FirebaseAuth.getInstance();

        saveWallet=(Button)findViewById(R.id.saveWallet);
        walletEdit=(EditText)findViewById(R.id.walletEdit);
        incomeEdit=(EditText)findViewById(R.id.incomeEdit);
        walletText=(TextView)findViewById(R.id.walletText);
        incomeText=(TextView)findViewById(R.id.incomeText);

        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(this);
        Float fl1=sharedPreferences.getFloat("fl",0);
        incomeEdit.setText(""+fl1);

        String st1=sharedPreferences.getString("st","");
        walletEdit.setText(st1);


        saveWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                st=walletEdit.getText().toString();
                fl=Float.parseFloat((incomeEdit.getText().toString()));

                SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(ProfilActivity.this);
                SharedPreferences.Editor editor=sharedPreferences.edit();

                editor.putFloat("fl",fl);
                editor.putString("st",st);
                editor.apply();

            }
        });
    }
}
