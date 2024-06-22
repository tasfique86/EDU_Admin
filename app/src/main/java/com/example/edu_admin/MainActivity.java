package com.example.edu_admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.edu_admin.utils.FirebaseUtil;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    CardView cardViewpayment,cardViewsetting,cardViewresult,cardViewdetails,cardViewVerification,cardViewremove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cardViewdetails=findViewById(R.id.AdminstudentsDetailsCardView);
        cardViewVerification=findViewById(R.id.AdminlogoutCardView);
        cardViewresult=findViewById(R.id.AdminresultCardView);
        cardViewremove=findViewById(R.id.AdminremoveCardView);
        cardViewsetting=findViewById(R.id.AdminsettingCardView);
        cardViewpayment=findViewById(R.id.AdminpaymentCardView);


        cardViewpayment.setOnClickListener(this);
        cardViewVerification.setOnClickListener(this);
        cardViewremove.setOnClickListener(this);
        cardViewsetting.setOnClickListener(this);
        cardViewresult.setOnClickListener(this);
        cardViewdetails.setOnClickListener(this);

        getFCMToken();

    }

    void getFCMToken(){
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                String token=task.getResult();
                FirebaseUtil.currentUserDetails().update("fcmToken",token);
            }
        });

    }


    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.AdminpaymentCardView){
            Intent intent=new Intent(MainActivity.this,FeeCRUD.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.AdminlogoutCardView){
            Intent intent=new Intent(MainActivity.this, SearchUserActivity.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.AdminremoveCardView){
            Intent intent=new Intent(MainActivity.this, Transection.class);
            startActivity(intent);

        }
        else if(v.getId()==R.id.AdminstudentsDetailsCardView){
            Intent intent=new Intent(MainActivity.this,StudentsAuthenticationCode.class);
            startActivity(intent);

        }
        else if(v.getId()==R.id.AdminsettingCardView){

        }
        else if(v.getId()==R.id.AdminresultCardView){
            Intent intent=new Intent(MainActivity.this,ResultPublish.class);
             startActivity(intent);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}