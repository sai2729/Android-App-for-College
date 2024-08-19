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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class TabMaterials extends Fragment {

    private Button btnfirst;
    private Button btnsecond;
    private Button btnthird;
    private Button btnfour;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview =inflater.inflate(R.layout.fragment_materials, container,false);


        btnfirst=(Button)rootview.findViewById(R.id.btnfirst);
        btnsecond=(Button)rootview.findViewById(R.id.btnsecond);
        btnthird=(Button)rootview.findViewById(R.id.btnthird);
        btnfour=(Button)rootview.findViewById(R.id.btnfour);

        btnfirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),mat1cse.class);
                startActivity(intent);
            }
        });

        btnsecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),mat2cse.class);
                startActivity(intent);
            }
        });
        btnthird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),mat3cse.class);
                startActivity(intent);
            }
        });
        btnfour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),mat4cse.class);
                startActivity(intent);
            }
        });

        return rootview;
    }
}
