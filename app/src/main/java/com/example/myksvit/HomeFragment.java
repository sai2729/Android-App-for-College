package com.example.myksvit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Collections;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private DatabaseReference mDatabase;


    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthListener;
    private Button btnpost,btnprofile;
    private FirebaseDatabase firebaseDatabase;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        btnpost = (Button) rootView.findViewById(R.id.btnpost);
        btnprofile=(Button)rootView.findViewById(R.id.btnprofile);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Blogzone");
        mAuth = FirebaseAuth.getInstance();

        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("Access").child(mAuth.getUid());


        databaseReference.addValueEventListener(new ValueEventListener(){
     @Override
 public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
         UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);

         if (userProfile.getUserrollno()==null){
             btnpost.setVisibility(View.VISIBLE);
         }

 }

 @Override
 public void onCancelled(@NonNull DatabaseError databaseError) {

     }
        });





        FirebaseUser firebaseUser=mAuth.getCurrentUser();
        if (firebaseUser!=null){
            String uid=firebaseUser.getUid();
            
        }
        btnprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),ProfileActivity.class);
                startActivity(intent);
            }
        });

        btnpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), PostActivity.class);
                startActivity(intent);

            }
        });


        return rootView;
    }

        @Override
        public void onStart() {
            super.onStart();
            FirebaseRecyclerAdapter<Blogzone, HomeFragment.BlogzoneViewHolder> FBRA = new FirebaseRecyclerAdapter<Blogzone, HomeFragment.BlogzoneViewHolder>(
                    Blogzone.class,
                    R.layout.card_items,
                    HomeFragment.BlogzoneViewHolder.class,
                    mDatabase
            ) {
                @Override
                protected void populateViewHolder(HomeFragment.BlogzoneViewHolder viewHolder, Blogzone model, int position) {
                    final String post_key = getRef(position).getKey().toString();
                    viewHolder.setTitle(model.getTitle());
                    viewHolder.setDesc(model.getDesc());
                    viewHolder.setImageUrl(getActivity().getApplicationContext(), model.getImageUrl());
                    viewHolder.setUserName(model.getUsername());
                    viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent singleActivity = new Intent(getActivity(), SinglePostActivity.class);
                            singleActivity.putExtra("PostID", post_key);
                            startActivity(singleActivity);
                        } });}};
            recyclerView.setAdapter(FBRA);
        }


        public static class BlogzoneViewHolder extends RecyclerView.ViewHolder{
            View mView;
            public BlogzoneViewHolder(View itemView) {
                super(itemView);
                mView = itemView;
            } public void setTitle(String title){
                TextView post_title = mView.findViewById(R.id.post_title_txtview);
                post_title.setText(title);
            } public void setDesc(String desc){
                TextView post_desc = mView.findViewById(R.id.post_desc_txtview);
                post_desc.setText(desc);
            } public void setImageUrl(Context ctx, String imageUrl){
                ImageView post_image = mView.findViewById(R.id.post_image);
                Picasso.with(ctx).load(imageUrl).into(post_image);
            } public void setUserName(String userName){
                TextView postUserName = mView.findViewById(R.id.post_user);
                postUserName.setText(userName);
            } }



    }


