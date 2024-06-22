package com.example.edu_admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.edu_admin.model.UserModel;
import com.example.edu_admin.utils.FirebaseUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;

import java.security.SecureRandom;

public class loginUserNameActivity extends AppCompatActivity {

    EditText usernameInput;
    Button letMeInBtn;
    String phoneNumber;
    ProgressBar progressBar;
    UserModel userModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user_name);

        usernameInput=findViewById(R.id.login_username);
        letMeInBtn=findViewById(R.id.login_let_me_in_btn);
        progressBar=findViewById(R.id.login_username_prograssbar);

        phoneNumber=getIntent().getExtras().getString("phone");

        getUsername();

        letMeInBtn.setOnClickListener(v -> {
            setUsername();
        });


    }

    void setUsername(){
       // setInProgress(true);
        String username=usernameInput.getText().toString();
        if(username.isEmpty()|| username.length()<3){
            usernameInput.setError("Username lenght should be at least 3 chars");
            return;
        }

        if(userModel!=null){
            userModel.setUsername(username);
        }else {
            userModel=new UserModel(phoneNumber,username, Timestamp.now(),FirebaseUtil.currentUserId());
        }
        FirebaseUtil.currentUserDetails().set(userModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                setInProgress(false);
                if(task.isSuccessful()){
                    Intent intent=new Intent(loginUserNameActivity.this,MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        });


    }
    void getUsername(){
        setInProgress(true);
        FirebaseUtil.currentUserDetails().get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                setInProgress(false);
                if(task.isSuccessful()){
                 userModel= task.getResult().toObject(UserModel.class);
                   if(userModel!=null)
                   {
                       usernameInput.setText(userModel.getUsername());
                   }
                }
            }
        });
    }
    void setInProgress(boolean inProgress)
    {
        if(inProgress)
        {
            progressBar.setVisibility(View.VISIBLE);
            letMeInBtn.setVisibility(View.GONE);
        }
        else {
            progressBar.setVisibility(View.GONE);
            letMeInBtn.setVisibility(View.VISIBLE);
        }
    }
}