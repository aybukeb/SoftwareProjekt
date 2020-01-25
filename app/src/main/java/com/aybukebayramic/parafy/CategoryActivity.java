package com.aybukebayramic.parafy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CategoryActivity extends AppCompatActivity {
    int i;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        firebaseFirestore=FirebaseFirestore.getInstance();

        ListView listView=findViewById(R.id.catlistView);
        final ArrayList<String> categoryNames=new ArrayList<>();
        categoryNames.add("Gıda");
        categoryNames.add("Giyim");
        categoryNames.add("Faturalar");
        categoryNames.add("Taksitler");
        categoryNames.add("Kira");
        categoryNames.add("Hediyeler");
        categoryNames.add("Ev Alışverişi");
        categoryNames.add("Sağlık");
        categoryNames.add("Spor");
        categoryNames.add("Eğlence");
        categoryNames.add("Hobi");
        categoryNames.add("Eğitim");


//        Map<String,String> map=new HashMap<>();
//        for(i=0;i<categoryNames.size();i++) {
//            map.put("category",categoryNames.get(i));
//            firebaseFirestore.collection("Category").add(map);
//
//        }

        ArrayAdapter arrayAdapter=new ArrayAdapter(CategoryActivity.this,android.R.layout.simple_list_item_1,categoryNames);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                //System.out.println("categoryNames.get(i)");

                Intent intent=new Intent();
                intent.putExtra("name",categoryNames.get(i));

                setResult(RESULT_OK, intent);
                finish();


            }
        });





    }

}
