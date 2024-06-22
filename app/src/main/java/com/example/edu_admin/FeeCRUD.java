package com.example.edu_admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FeeCRUD extends AppCompatActivity {

    private Button buttonset,buttonupdate,buttondisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee_crud);

        buttonset=findViewById(R.id.SetSemesterFee);
        buttondisplay=findViewById(R.id.displaySemesterFee);
        buttonupdate=findViewById(R.id.updateSemesterFee);

        buttonset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(FeeCRUD.this,SetSemesterFee.class);
                startActivity(intent);
            }
        });
    }
}