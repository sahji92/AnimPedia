package com.anim.animpedia;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private  List<Entry> entries;
    private Context context;

    public MyAdapter(Context context,List<Entry> list){
        this.context=context;
        this.entries=list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myview=LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item,parent,false);
        final MyViewHolder myViewHolder=new MyViewHolder(myview);
        myview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,AnimalInfoActivity.class);
                intent.putExtra("animalName",myViewHolder.animalName.getText());
                for(Entry entry:entries){
                 if(entry.getAnimalName().equalsIgnoreCase((String) myViewHolder.animalName.getText())){
                     intent.putExtra("imgUrl",entry.getImage());
                     intent.putExtra("description",entry.getDescription());
                     intent.putExtra("soundUrl",entry.getAnimalSound());
                     break;
                 }
                }
                context.startActivity(intent);
            }
        });
        return  myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Entry entry=entries.get(position);
        String animalName=entry.getAnimalName();
        String firstLetterInCapital=animalName.substring(0,1).toUpperCase();
        holder.setAnimalName(firstLetterInCapital+animalName.substring(1));
        Glide.with(context).load(entry.getImage()).into(holder.animalImage);
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }


    public  class MyViewHolder extends RecyclerView.ViewHolder {
         private ImageView animalImage;
         private TextView animalName;

       public void setAnimalName(String animalName) {
            this.animalName.setText(animalName);
        }

       /* public void setAnimalImage(String animalImage) {
           this.animalImage.setImageURI(Uri.parse(animalImage));

        }
       */
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            animalImage=itemView.findViewById(R.id.animalImage);
            animalName=itemView.findViewById(R.id.animalName);
        }
    }

}
