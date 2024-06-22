package com.example.edu_admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SetSemesterFee extends AppCompatActivity {

    private EditText editTextsemester1,editTextsemester2,editTextsemester3,editTextsemester4,editTextsemester5,editTextsemester6,
            editTextsemester7,editTextsemester8,editTextDeparment;
    private Button buttonSemesterFeeSubmit;
    DatabaseReference databaseReferenceSemesterFee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_semester_fee);


        editTextsemester1=findViewById(R.id.Edittext_admin_semester1);
        editTextsemester2=findViewById(R.id.Edittext_admin_semester2);
        editTextsemester3=findViewById(R.id.Edittext_admin_semester3);
        editTextsemester4=findViewById(R.id.Edittext_admin_semester4);
        editTextsemester5=findViewById(R.id.Edittext_admin_semester5);
        editTextsemester6=findViewById(R.id.Edittext_admin_semester6);
        editTextsemester7=findViewById(R.id.Edittext_admin_semester7);
        editTextsemester8=findViewById(R.id.Edittext_admin_semester8);

        editTextDeparment=findViewById(R.id.Edittext_admin_departmentName);


        buttonSemesterFeeSubmit=findViewById(R.id.Edittext_admin_submit_SemesterFee);


        buttonSemesterFeeSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=editTextsemester1.getText().toString();
                String s2=editTextsemester2.getText().toString();
                String s3=editTextsemester3.getText().toString();
                String s4=editTextsemester4.getText().toString();
                String s5=editTextsemester5.getText().toString();
                String s6=editTextsemester6.getText().toString();
                String s7=editTextsemester7.getText().toString();
                String s8=editTextsemester8.getText().toString();

                String department=editTextDeparment.getText().toString();

                databaseReferenceSemesterFee= FirebaseDatabase.getInstance().getReference("Semester Fee");
                if(TextUtils.isEmpty(department)){
                    Toast.makeText(SetSemesterFee.this,"Please enter your Full name",Toast.LENGTH_SHORT).show();
                    editTextDeparment.setError("Department name require");
                    editTextDeparment.requestFocus();
                } else if (department.equals("CSE")) {
                    Amount(department,s1,s2,s3,s4,s5,s6,s7,s8);
                }
                else {
                    Toast.makeText(SetSemesterFee.this,"This department is not include in Database",Toast.LENGTH_SHORT).show();
                    editTextDeparment.setError("Enter valid Department");
                    editTextDeparment.requestFocus();
                }
            }


        });


    }
    private void Amount(String dep,String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8) {


        String key=databaseReferenceSemesterFee.push().getKey();

        SemesterFeeAdmin semesterFeeAdmin=new SemesterFeeAdmin(dep,s1,s2,s3,s4,s5,s6,s7,s8);

        databaseReferenceSemesterFee.child(key).setValue(semesterFeeAdmin);

        Toast.makeText(SetSemesterFee.this, "Added Succesfully", Toast.LENGTH_SHORT).show();

        Intent intent=new Intent(SetSemesterFee.this,FeeCRUD.class);
        startActivity(intent);

    }
}