package com.anim.animpedia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>  {

    private List<Animal> data;
    private Context context;
    private static ClickListener clickListener;



    public int dpToPx(int dp, Context context) {
        float px = dp * context.getResources().getDisplayMetrics().density;
        return (int) px;

    }

    public RecyclerViewAdapter(Context context, List<Animal> data) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.row_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {

        holder.txtTitle.setText(data.get(position).getTitle());
        holder.imgView.setImageResource(data.get(position).getImgRes());
        int margin = dpToPx(4, context);

        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) holder.cardView.getLayoutParams();
        layoutParams.setMargins(margin, margin, margin, margin);
        holder.cardView.setLayoutParams(layoutParams);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtTitle;
        ImageView imgView;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtTitle);
            imgView = itemView.findViewById(R.id.imgCardImage);
            cardView = itemView.findViewById(R.id.cardHolder);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }
    }
    public void setOnItemClickListener(ClickListener clickListener) {
        RecyclerViewAdapter.clickListener = clickListener;
    }
    public interface ClickListener {
        void onItemClick(int position, View v);
    }
}
