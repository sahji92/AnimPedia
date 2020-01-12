package com.anim.animpedia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Animal> animals;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_recycler_activity);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mAdView = findViewById(R.id.adView);

        MobileAds.initialize(this);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        animals = new ArrayList<>();
        animals.add(new Animal("Amphibians", R.drawable.amphibians));
        animals.add(new Animal("Arthropods", R.drawable.arthropods));
        animals.add(new Animal("Birds", R.drawable.bird));
        animals.add(new Animal("Sea Animals", R.drawable.fish));
        animals.add(new Animal("Mammals", R.drawable.mammels));
        animals.add(new Animal("Reptiles", R.drawable.reptiles));

        RecyclerView recyclerView = findViewById(R.id.startingRecyclerViewGrid);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, animals);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new RecyclerViewAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent intent = new Intent(getApplicationContext(), AnimalsActivity.class);
                switch (position) {
                    case 4:
                        intent.putExtra("category", "mammels");
                        break;
                    case 3:
                        intent.putExtra("category", "seaAnimals");
                        break;
                    case 2:
                        intent.putExtra("category", "birds");
                        break;
                    case 0:
                        intent.putExtra("category", "amphibians");
                        break;
                    case 5:
                        intent.putExtra("category", "reptiles");
                        break;
                    case 1:
                        intent.putExtra("category", "arthropods");
                        break;
                }
                startActivity(intent);
            }
        });
    }


}
