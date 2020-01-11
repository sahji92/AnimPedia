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

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class AnimalInfoActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener {
    private CircleImageView circulerImageView;
    private TextView aName;
    private TextView description;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_info);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

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
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                }
                else{
                    onPrepared(mediaPlayer);
                    Intent intent1=new Intent(AnimalInfoActivity.this,FullImageActivity.class);
                    intent1.putExtra("pUrl",imageUrl);
                    startActivity(intent1);
                }
            }
        });

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mediaPlayer.stop();
    }
}
