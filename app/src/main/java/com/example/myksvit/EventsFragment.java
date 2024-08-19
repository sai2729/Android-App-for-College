package com.example.myksvit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class EventsFragment extends Fragment {

    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;


    private RecyclerView recyclerView;
    private DatabaseReference mDatabase;



    private FirebaseAuth.AuthStateListener mAuthListener;
    private Button btnpost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_events, container, false);

        btnpost = (Button) rootView.findViewById(R.id.btnpost);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);


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




        mDatabase = FirebaseDatabase.getInstance().getReference().child("Eventzone");
        mAuth = FirebaseAuth.getInstance();

        btnpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), EventPostActivity.class);
                startActivity(intent);

            }
        });


        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Eventzone, EventsFragment.EventzoneViewHolder> FBRA = new FirebaseRecyclerAdapter<Eventzone, EventsFragment.EventzoneViewHolder>(
                Eventzone.class,
                R.layout.event_card_items,
                EventsFragment.EventzoneViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(EventsFragment.EventzoneViewHolder viewHolder, Eventzone model, int position) {
                final String post_key = getRef(position).getKey().toString();
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getDesc());
                viewHolder.setUrl(model.getUrl());
                viewHolder.setUserName(model.getUsername());
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent singleActivity = new Intent(getActivity(), SingleEventPostActivity.class);
                        singleActivity.putExtra("PostID", post_key);
                        startActivity(singleActivity);
                    } });}};
        recyclerView.setAdapter(FBRA);
    }


    public static class EventzoneViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public EventzoneViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        } public void setTitle(String title){
            TextView post_title = mView.findViewById(R.id.post_title_txtview);
            post_title.setText(title);
        } public void setDesc(String desc){
            TextView post_desc = mView.findViewById(R.id.post_desc_txtview);
            post_desc.setText(desc);
        }public void setUrl(String url){
            TextView post_url = mView.findViewById(R.id.post_url_txtview);
            post_url.setText(url);
        } public void setUserName(String userName){
            TextView postUserName = mView.findViewById(R.id.post_user);
            postUserName.setText(userName);
        } }




}

