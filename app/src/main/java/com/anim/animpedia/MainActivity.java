package com.anim.animpedia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

   List<Animal> animals;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_recycler_activity);

        animals = new ArrayList<>();
        animals.add(new Animal("Amphibians",R.drawable.amphibians));
        animals.add(new Animal("Arthropods",R.drawable.arthropods));
        animals.add(new Animal("Birds",R.drawable.bird));
        animals.add(new Animal("Fishes",R.drawable.fish));
        animals.add(new Animal("Mammals",R.drawable.mammels));
        animals.add(new Animal("Reptiles",R.drawable.reptiles));

       RecyclerView recyclerView = findViewById(R.id.startingRecyclerViewGrid);
       RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,animals);
       recyclerView.setLayoutManager(new GridLayoutManager(this,2));
       recyclerView.setAdapter(adapter);

       adapter.setOnItemClickListener(new RecyclerViewAdapter.ClickListener() {
           @Override
           public void onItemClick(int position, View v) {
               Intent intent=new Intent(getApplicationContext(),AnimalsActivity.class);
               switch(position){
                   case 4:
                       intent.putExtra("category","mammels");
                       break;
                   case 3:
                       intent.putExtra("category","fish");
                       break;
                   case 2:
                       intent.putExtra("category","birds");
                       break;
                   case 0:
                       intent.putExtra("category","amphibians");
                       break;
                   case 5:
                       intent.putExtra("category","reptiles");
                       break;
                   case 1:
                       intent.putExtra("category","arthropods");
                       break;
               }
               startActivity(intent);
           }
       });
    }


}