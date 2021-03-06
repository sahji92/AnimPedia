package com.anim.animpedia;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class AnimalInfoActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener {
    private CircleImageView circulerImageView;
    private TextView aName;
    private TextView description;
    MediaPlayer mediaPlayer;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_info);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mAdView = findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-4329448313558986/8737636113");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        circulerImageView=findViewById(R.id.circulerImage);

        aName=findViewById(R.id.aName);
        description=findViewById(R.id.des);
        final Intent intent=getIntent();
        String animalName=intent.getStringExtra("animalName");
        final String imageUrl=intent.getStringExtra("imgUrl");
        String des=intent.getStringExtra("description");
        final String soundUrl=intent.getStringExtra("soundUrl");
        aName.setText(animalName);
        Glide.with(this).load(imageUrl).diskCacheStrategy(DiskCacheStrategy.ALL) //using to load into cache then second time it will load fast.
                .into(circulerImageView);
        description.setText(des);
        description.setMovementMethod(new ScrollingMovementMethod());
        mediaPlayer=new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(soundUrl);
            mediaPlayer.setOnPreparedListener(AnimalInfoActivity.this);
            mediaPlayer.prepareAsync();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        circulerImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    onPrepared(mediaPlayer);
                if (mInterstitialAd.isLoaded()) {
                    //here we check if 160 seconds from last time we showed the app until now has passed
                    //if yes we will show the ad, if not we won't show because it would be annoying to user if
                    //we keep showing add too often
                    if (System.currentTimeMillis() - AdTimeTracker.LAST_TIME_AD_SEEN > 160_000) {
                        mInterstitialAd.show();
                        AdTimeTracker.LAST_TIME_AD_SEEN = System.currentTimeMillis();
                    }
                }
                    Intent intent1=new Intent(AnimalInfoActivity.this,FullImageActivity.class);
                    intent1.putExtra("pUrl",imageUrl);
                    startActivity(intent1);
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }

}
