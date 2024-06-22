package com.example.edu_admin.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edu_admin.ChatActivity;
import com.example.edu_admin.R;
import com.example.edu_admin.model.UserModel;
import com.example.edu_admin.utils.AndroidUtil;
import com.example.edu_admin.utils.FirebaseUtil;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class SearchUserRecyclerAdaptar extends FirestoreRecyclerAdapter<UserModel,SearchUserRecyclerAdaptar.UserModelViewHolder> {

    Context context;

    public SearchUserRecyclerAdaptar(@NonNull FirestoreRecyclerOptions<UserModel> options,Context context) {
        super(options);
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull UserModelViewHolder holder, int position, @NonNull UserModel model) {
        holder.usernameText.setText(model.getUsername());
        holder.phoneText.setText(model.getPhone());

        if(model.getUserId().equals(FirebaseUtil.currentUserId())){
            holder.usernameText.setText(model.getUsername()+" (Me)");
        }




        holder.itemView.setOnClickListener(v -> {


            ///navigate to chat activity
            Intent intent =new Intent(context, ChatActivity.class);
            AndroidUtil.passUserModelAsIntent(intent,model);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        });


    }

    @NonNull
    @Override
    public UserModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.search_user_recycler_row,parent,false);

        return new UserModelViewHolder(view);
    }

    class UserModelViewHolder extends RecyclerView.ViewHolder{
        TextView usernameText;
        TextView phoneText;

        ImageView profilePic;
        public UserModelViewHolder(@NonNull View itemView) {
            super(itemView);

            usernameText =itemView.findViewById(R.id.user_name_text);
            phoneText=itemView.findViewById(R.id.phone_text);
            profilePic=itemView.findViewById(R.id.profile_pic_image_view);
        }
    }
}
