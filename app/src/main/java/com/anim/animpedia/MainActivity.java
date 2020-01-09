package com.anim.animpedia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

   private LinearLayout mammelsLayout,fishLayout,birdsLayout,amphibianslayout,reptileslayout,arthropodsLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mammelsLayout=findViewById(R.id.mammelsLayout);
        fishLayout=findViewById(R.id.fishlayout);
        birdsLayout=findViewById(R.id.birdsLayout);
        amphibianslayout=findViewById(R.id.amphibiansLayout);
        reptileslayout=findViewById(R.id.reptilsLayout);
        arthropodsLayout=findViewById(R.id.arthropodsLayout);
        mammelsLayout.setOnClickListener(this);
        fishLayout.setOnClickListener(this);
        birdsLayout.setOnClickListener(this);
        amphibianslayout.setOnClickListener(this);
        reptileslayout.setOnClickListener(this);
        arthropodsLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent(this,AnimalsActivity.class);
        switch(view.getId()){
            case R.id.mammelsLayout:
                intent.putExtra("category","mammels");
                break;
            case R.id.fishlayout:
                intent.putExtra("category","fish");
                break;
            case R.id.birdsLayout:
                intent.putExtra("category","birds");
                break;
            case R.id.amphibiansLayout:
                intent.putExtra("category","amphibians");
                break;
            case R.id.reptilsLayout:
                intent.putExtra("category","reptiles");
                break;
            case R.id.arthropodsLayout:
                intent.putExtra("category","arthropods");
                break;
        }
        startActivity(intent);

    }
}
