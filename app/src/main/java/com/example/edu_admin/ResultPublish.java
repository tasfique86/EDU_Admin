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

public class ResultPublish extends AppCompatActivity {
    private EditText editTextsemester1,editTextsemester2,editTextsemester3,editTextsemester4,editTextsemester5,editTextsemester6,
            editTextsemester7,editTextsemester8,editTextDeparment,editTextRollno;

    private Button buttonSemesterFeeSubmit;
    DatabaseReference databaseReferenceResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_publish);

        editTextsemester1=findViewById(R.id.Edittext_admin_semester1_result);
        editTextsemester2=findViewById(R.id.Edittext_admin_semester2_result);
        editTextsemester3=findViewById(R.id.Edittext_admin_semester3_result);
        editTextsemester4=findViewById(R.id.Edittext_admin_semester4_result);
        editTextsemester5=findViewById(R.id.Edittext_admin_semester5_result);
        editTextsemester6=findViewById(R.id.Edittext_admin_semester6_result);
        editTextsemester7=findViewById(R.id.Edittext_admin_semester7_result);
        editTextsemester8=findViewById(R.id.Edittext_admin_semester8_result);

        editTextDeparment=findViewById(R.id.Edittext_admin_departmentName_result);
        editTextRollno=findViewById(R.id.Edittext_admin_Rollno__result);


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

                String rollNo=editTextRollno.getText().toString();

                String department=editTextDeparment.getText().toString();

                databaseReferenceResult= FirebaseDatabase.getInstance().getReference("Academic Result of "+department);
                if(TextUtils.isEmpty(department)){
                    Toast.makeText(ResultPublish.this,"Please enter your Full name",Toast.LENGTH_SHORT).show();
                    editTextDeparment.setError("Department name require");
                    editTextDeparment.requestFocus();
                } else if (department.equals("CSE")) {
                    SetResult(department,rollNo,s1,s2,s3,s4,s5,s6,s7,s8);

                    editTextRollno.setText("");

                    editTextsemester1.setText("");
                    editTextsemester2.setText("");
                    editTextsemester3.setText("");
                    editTextsemester4.setText("");
                    editTextsemester5.setText("");
                    editTextsemester6.setText("");
                    editTextsemester7.setText("");
                    editTextsemester8.setText("");
                }
                else {
                    Toast.makeText(ResultPublish.this,"This department is not include in Database",Toast.LENGTH_SHORT).show();
                    editTextDeparment.setError("Enter valid Department");
                    editTextDeparment.requestFocus();
                }
            }


        });



    }


    private void SetResult(String dep,String roll,String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8) {


        String key=databaseReferenceResult.push().getKey();

        ResultReadWrite resultReadWrite=new ResultReadWrite(dep,roll,s1,s2,s3,s4,s5,s6,s7,s8);

        databaseReferenceResult.child(key).setValue(resultReadWrite);

        Toast.makeText(ResultPublish.this, "Added Succesfully", Toast.LENGTH_SHORT).show();

    }
}
