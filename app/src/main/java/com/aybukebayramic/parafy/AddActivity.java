package com.aybukebayramic.parafy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class AddActivity extends AppCompatActivity {
    Bitmap selectedImage;
    ImageView imageView;
    EditText commentText;
    EditText amountText;
    TextView catnameText;
    TextView dateText;
    CalendarView calendar;
    //String uid;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    Uri imageData;
    private FirebaseFirestore firebaseFirestore;
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
            Intent intentToAdd=new Intent(AddActivity.this,AddActivity.class);
            startActivity(intentToAdd);

        }
        if(item.getItemId()==R.id.my_expense) {
            Intent intentToExpense=new Intent(AddActivity.this, ExpensesActivity.class);
            startActivity(intentToExpense);


        }
        if(item.getItemId()==R.id.my_budget) {
            Intent intentToAdd=new Intent(AddActivity.this,BudgetActivity.class);
            startActivity(intentToAdd);

        }

        if(item.getItemId()==R.id.sign_out) {
            firebaseAuth.signOut();
            Intent intentToSign=new Intent(AddActivity.this,SignActivity.class);
            startActivity(intentToSign);
            finish();


        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        imageView=findViewById(R.id.imageView2);
        commentText=findViewById(R.id.commentText);
        amountText=findViewById(R.id.amountText);
        catnameText=findViewById(R.id.catnameText);
        //uid=firebaseAuth.getCurrentUser().getUid();



        calendar = (CalendarView) findViewById(R.id.calendarView);
        dateText=findViewById(R.id.recyclerView_date);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

           @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

               dateText.setText(dayOfMonth +" / " + (month+1) + " / " + year);

            }
        });



        firebaseStorage=FirebaseStorage.getInstance();
        storageReference=firebaseStorage.getReference();
        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();


    }
        public void add (View view) {
        if (imageData!=null) {

            UUID uuid=UUID.randomUUID();
            final String imageName = "/images" + uuid +".jpg";
            storageReference.child(imageName).putFile(imageData).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    StorageReference newReference = FirebaseStorage.getInstance().getReference(imageName);
                    newReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                        String downloadUrl= uri.toString();

                        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
                        String userEmail=firebaseUser.getEmail();
                        String amount=amountText.getText().toString();
                        //String amount=String.valueOf((String) amountText.getText().toString());
                        String catName=catnameText.getText().toString();
                        String comment=commentText.getText().toString();
                        String datee=dateText.getText().toString();
                        String uid=firebaseAuth.getCurrentUser().getUid();



                        HashMap<String, Object>  expenseData=new HashMap<>();
                        expenseData.put("useremail",userEmail);
                        expenseData.put("amount",amount);
                        expenseData.put("categoryNames",catName);
                        expenseData.put("downloadurl",downloadUrl);
                        expenseData.put("comment",comment);
                        expenseData.put("date",FieldValue.serverTimestamp());
                        expenseData.put("calendardate",datee);
                        expenseData.put("useruid",uid);

                         firebaseFirestore.collection("Expenses").add(expenseData).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                             @Override
                             public void onSuccess(DocumentReference documentReference) {
                                 Intent intent=new Intent(AddActivity.this,FeedActivity.class);
                                 intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                 startActivity(intent);
                                 finish();

                             }
                         }).addOnFailureListener(new OnFailureListener() {
                             @Override
                             public void onFailure(@NonNull Exception e) {
                                Toast.makeText(AddActivity.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                             }
                         });
                        }
                    });

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddActivity.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                }
            });

        }


    }
    public void selectImage(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},1);
        } else {
            Intent intentToGallery=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intentToGallery,2);
        }
    }
     public void categoryClicked (View view) {
         Intent intent=new Intent(AddActivity.this,CategoryActivity.class);
         startActivityForResult(intent,3);

     }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode==1) {
            if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED) {
                Intent intentToGallery=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intentToGallery,2);

            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==2 && resultCode==RESULT_OK && data != null) {
            imageData=data.getData(); //görselin kayıtlı olduğu yerin adresi

            try {

                if (Build.VERSION.SDK_INT >= 28) {
                    ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(), imageData);
                    selectedImage = ImageDecoder.decodeBitmap(source);
                    imageView.setImageBitmap(selectedImage);
                } else {
                    selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageData);
                    imageView.setImageBitmap(selectedImage);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (requestCode==3 && resultCode==RESULT_OK && data != null) {
            System.out.println("deneme " + data);
            catnameText.setText(data.getStringExtra("name"));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
