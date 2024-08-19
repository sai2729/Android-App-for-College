package com.example.myksvit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class Tabcse22 extends Fragment {

    private Button btnadd;
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;



    ListView MyPDFListView;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    List<uploadPDF> uploadPDFS;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview= inflater.inflate(R.layout.fragment_cse22, container,false);

        btnadd=(Button)rootview.findViewById(R.id.btn_add);


        mAuth = FirebaseAuth.getInstance();


        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("Access").child(mAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);

                if (userProfile.getUserrollno()==null){
                    btnadd.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Upload22Activity.class);
                startActivity(intent);

            }
        });

        btnadd=(Button)rootview.findViewById(R.id.btn_add);

        uploadPDFS = new ArrayList<>();
        MyPDFListView = (ListView)rootview.findViewById(R.id.myListView);


        MyPDFListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                uploadPDF uploadPDF = uploadPDFS.get(position);

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(uploadPDF.getUrl()));
                startActivity(intent);

            }
        });


        databaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS22);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {

                    uploadPDF uploadPDF = postsnapshot.getValue(com.example.myksvit.uploadPDF.class);
                    uploadPDFS.add(uploadPDF);

                }

                final String[] uploads = new String[uploadPDFS.size()];

                for (int i = 0; i < uploads.length; i++) {

                    uploads[i] = uploadPDFS.get(i).getName();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                MyPDFListView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return rootview;
    }
}
