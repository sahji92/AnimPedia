package com.anim.animpedia;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AnimalsActivity extends AppCompatActivity {
    private TextView textView;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private ArrayList<Entry> entries;
    private String animalCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animals);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        final ProgressDialog dialog = new ProgressDialog(this);
//        dialog.setMessage("getting Animals");
//        dialog.show();
        textView = findViewById(R.id.textView);
        String animalCategory = getIntent().getStringExtra("category");
        String firstLetterInCapital = animalCategory.substring(0, 1).toUpperCase();
        textView.setText(firstLetterInCapital + animalCategory.substring(1));
        entries = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        FirebaseDatabase.getInstance().getReference().child("category").child(getIntent().getStringExtra("category")).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> iterable = dataSnapshot.getChildren();
                Iterator<DataSnapshot> i = iterable.iterator();

                while (i.hasNext()) {
                    Entry e = dataSnapshot.child(i.next().getKey()).getValue(Entry.class);
                    entries.add(e);
                }
                adapter = new MyAdapter(AnimalsActivity.this, entries);
                recyclerView.setAdapter(adapter);
                recyclerView.setVisibility(View.VISIBLE);
                findViewById(R.id.progressBar).setVisibility(View.GONE);
//                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

    }


}
