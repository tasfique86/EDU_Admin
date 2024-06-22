package com.example.edu_admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

public class StudentsAuthenticationCode extends AppCompatActivity {
    private EditText editTextRegistration,editTextMobiletNo,editTextDepartment;
    CountryCodePicker countryCodePicker;
    private Button buttonAddaccount,buttonDis;
    DatabaseReference databaseReferenceAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_authentication_code);


        editTextMobiletNo=findViewById(R.id.accountNOEdittext);
        editTextRegistration=findViewById(R.id.registerNOEdittext);
        buttonAddaccount=findViewById(R.id.AddaccountButtom);
        editTextDepartment=findViewById(R.id.departmentEdittext);
        countryCodePicker=findViewById(R.id.registar_countrycode);
        buttonDis=findViewById(R.id.Addaccountdis);





        countryCodePicker.registerCarrierNumberEditText(editTextMobiletNo);

        buttonDis.setOnClickListener(v -> {
            String roll=editTextRegistration.getText().toString();
            String mob=editTextMobiletNo.getText().toString();

            Intent intent=new Intent(StudentsAuthenticationCode.this,DisplayTest.class);
            intent.putExtra("userRoll",roll);
            intent.putExtra("phone",mob);
            startActivity(intent);

        });

        buttonAddaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveAccount(databaseReferenceAdd);
            }
        });
    }

    private void saveAccount(DatabaseReference databaseReferenceAdd){


        String registrationNo=editTextRegistration.getText().toString();
        String MobileNo=editTextMobiletNo.getText().toString();
        String depart=editTextDepartment.getText().toString();

        if(TextUtils.isEmpty(depart)){
            editTextDepartment.setError("Department is not valid");
            return;
        }else if(TextUtils.isEmpty(registrationNo)){
            editTextRegistration.setError("Registration Id is not valid");
            return;
        } else if(!countryCodePicker.isValidFullNumber()) {
            editTextMobiletNo.setError("Phone number is not valid");
            return;
        }


        Toast.makeText(StudentsAuthenticationCode.this,"Auth Students for "+depart,Toast.LENGTH_SHORT).show();
        databaseReferenceAdd= FirebaseDatabase.getInstance().getReference("Auth Students");

        String key=databaseReferenceAdd.push().getKey();
        AuthReadWrite readWriteAccount=new AuthReadWrite(depart,registrationNo,MobileNo,key);
        databaseReferenceAdd.child(key).setValue(readWriteAccount);
        Toast.makeText(StudentsAuthenticationCode.this, "Added Succesfully", Toast.LENGTH_SHORT).show();

        editTextRegistration.setText("");
        editTextMobiletNo.setText("");
    }
}