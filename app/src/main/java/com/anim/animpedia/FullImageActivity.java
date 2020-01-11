package com.anim.animpedia;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class FullImageActivity extends AppCompatActivity {
      private ImageView fullImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);
        fullImage=findViewById(R.id.fullImage);
        Glide.with(this).load(getIntent().getStringExtra("pUrl")).into(fullImage);
        fullImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
