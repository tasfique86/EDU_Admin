package com.example.edu_admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DisplayTest extends AppCompatActivity {

    DatabaseReference databaseReferenceResult;
    int find=0;
    String phoneNumber,registrationNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_test);

        phoneNumber=getIntent().getExtras().getString("phone");
        registrationNo=getIntent().getExtras().getString("userRoll");


             databaseReferenceResult= FirebaseDatabase.getInstance().getReference("Auth Students");

        Toast.makeText(DisplayTest.this,registrationNo+" <> "+phoneNumber,Toast.LENGTH_SHORT).show();
                databaseReferenceResult.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for(DataSnapshot data: snapshot.getChildren())
                        {
                            AuthReadWrite authReadWrite = data.getValue(AuthReadWrite.class);


                            String sR=authReadWrite.getRegistrationID(),sM=authReadWrite.getReg_Mobile();
                             Toast.makeText(DisplayTest.this,sR+"   "+sM,Toast.LENGTH_SHORT).show();
                            if(sR.equals(registrationNo)&&sM.equals(phoneNumber))
                            {
                                find=1;
                            }

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(DisplayTest.this,"Erorr",Toast.LENGTH_SHORT).show();
                    }
                });


            if(find==0)
            {
                Toast.makeText(DisplayTest.this,"Invalid Mobile or RegistratonId",Toast.LENGTH_SHORT).show();
                return;
            }
    }
}