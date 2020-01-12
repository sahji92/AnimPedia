package com.anim.animpedia;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class FullImageActivity extends AppCompatActivity {
    private ImageView fullImage;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        fullImage = findViewById(R.id.fullImage);
        Glide.with(this).load(getIntent().getStringExtra("pUrl")).into(fullImage);
        fullImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mInterstitialAd.isLoaded()) {
            //here we check if 160 seconds from last time we showed the app until now has passed
            //if yes we will show the ad, if not we won't show because it would be annoying to user if
            //we keep showing add too often
            if (System.currentTimeMillis() - AdTimeTracker.LAST_TIME_AD_SEEN > 160_000) {
                mInterstitialAd.show();
                AdTimeTracker.LAST_TIME_AD_SEEN = System.currentTimeMillis();
            }
        }
    }
}
