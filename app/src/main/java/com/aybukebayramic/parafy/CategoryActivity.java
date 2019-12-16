package com.aybukebayramic.parafy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
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

        ArrayAdapter arrayAdapter=new ArrayAdapter(CategoryActivity.this,android.R.layout.simple_list_item_1,categoryNames);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                //System.out.println("categoryNames.get(i)");
                Intent intent=new Intent(getApplicationContext(),AddActivity.class);
                intent.putExtra("name",categoryNames.get(i));

                startActivity(intent);

            }
        });
    }

}
