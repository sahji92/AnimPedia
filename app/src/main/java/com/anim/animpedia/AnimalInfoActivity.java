package com.anim.animpedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class AnimalInfoActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener {
    private CircleImageView circulerImageView;
    private TextView aName;
    private TextView description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_info);
        circulerImageView=findViewById(R.id.circulerImage);
        aName=findViewById(R.id.aName);
        description=findViewById(R.id.des);
        Intent intent=getIntent();
        String animalName=intent.getStringExtra("animalName");
        String imageUrl=intent.getStringExtra("imgUrl");
        String des=intent.getStringExtra("description");
        final String soundUrl=intent.getStringExtra("soundUrl");
        aName.setText(animalName);
        Glide.with(this).load(imageUrl).fitCenter().into(circulerImageView);
        description.setText(des);
        description.setMovementMethod(new ScrollingMovementMethod());
        final MediaPlayer mediaPlayer=new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        circulerImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 try {
                     mediaPlayer.setDataSource(soundUrl);
                     mediaPlayer.setOnPreparedListener(AnimalInfoActivity.this);
                     mediaPlayer.prepareAsync();
                 }
                 catch (IOException e){
                     e.printStackTrace();
                 }
            }
        });

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }
}
