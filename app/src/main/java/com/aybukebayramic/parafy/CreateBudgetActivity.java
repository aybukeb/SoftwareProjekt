package com.aybukebayramic.parafy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.TagLostException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class CreateBudgetActivity extends AppCompatActivity {

    TextView catnameText2;
    EditText constrictionAmount;

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_budget);

        catnameText2=findViewById(R.id.catnameText2);
        constrictionAmount=findViewById(R.id.constrictionAmount);

        Intent intent2=getIntent();
        String catName2=intent2.getStringExtra("name2");
        catnameText2.setText(catName2);

        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
    }
    public void budgetCategoryClicked(View view) {
        Intent intent=new Intent(CreateBudgetActivity.this,SecondCategoryActivity.class);
        startActivity(intent);

    }

    public void createbudget_clicked(View view) {

        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        String userEmail=firebaseUser.getEmail();
        String catName2=catnameText2.getText().toString();
        String amountofconstriction=constrictionAmount.getText().toString();
        String uid=firebaseAuth.getCurrentUser().getUid();

        HashMap<String,Object> budgetData=new HashMap<>();
        budgetData.put("useremail",userEmail);
        budgetData.put("catname2",catName2);
        budgetData.put("amountconst",amountofconstriction);
        budgetData.put("useruid",uid);

        firebaseFirestore.collection("Budgets").add(budgetData).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Intent intent=new Intent(CreateBudgetActivity.this,BudgetActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CreateBudgetActivity.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
            }
        });



    }
}
