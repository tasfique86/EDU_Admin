package com.example.edu_admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.edu_admin.adapter.TransectionAdapter;
import com.example.edu_admin.model.TransectionReadWrite;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Transection extends AppCompatActivity {
RecyclerView recyclerView;
DatabaseReference databaseReference;
TransectionAdapter transectionAdapter;

ArrayList<TransectionReadWrite> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transection);

        recyclerView=findViewById(R.id.Transcetion_recyclerView);
        databaseReference= FirebaseDatabase.getInstance().getReference("Transection");
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        list=new ArrayList<>();

        transectionAdapter =new TransectionAdapter(this,list);
        recyclerView.setAdapter(transectionAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Toast.makeText(Transection.this,"successfully",Toast.LENGTH_SHORT).show();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    TransectionReadWrite transectionReadWrite= dataSnapshot.getValue(TransectionReadWrite.class);
                    list.add(transectionReadWrite);
                }
                transectionAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}