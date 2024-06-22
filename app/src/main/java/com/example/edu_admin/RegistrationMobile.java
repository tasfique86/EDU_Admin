package com.example.edu_admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.edu_admin.model.RegisterMobile;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationMobile extends AppCompatActivity {


    EditText editTextregistrationNo,editTextMobileNo;
    Button buttonSubmit;

    DatabaseReference databaseReferenceResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_mobile);

        editTextMobileNo=findViewById(R.id.registrationMobile_mobile);
        editTextregistrationNo=findViewById(R.id.registrationMobile_registrationNo);
        buttonSubmit=findViewById(R.id.registrationMobile_btn);

        String mobile=editTextMobileNo.getText().toString();
        String registrationNo=editTextregistrationNo.getText().toString();



        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReferenceResult= FirebaseDatabase.getInstance().getReference("Register mobile");
                if(registrationNo.isEmpty()){
                    editTextregistrationNo.setError("Invalid registration NO");
                    return;
                }
                else if(mobile.isEmpty()){
                    editTextMobileNo.setError("Invalid Mobile NO");
                    return;
                }
                String key=databaseReferenceResult.push().getKey();
                RegisterMobile registerMobile=new RegisterMobile(registrationNo,mobile);
                databaseReferenceResult.child(key).setValue(registerMobile);

                Toast.makeText(RegistrationMobile.this, "Added Succesfully", Toast.LENGTH_SHORT).show();

            }

        });
    }
}