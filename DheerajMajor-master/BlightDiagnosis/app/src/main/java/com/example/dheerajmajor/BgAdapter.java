package com.example.dheerajmajor;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class BgAdapter extends RecyclerView.Adapter<BgAdapter.ViewHolder> {
    private List<String> Models;
    private List<String> models1;
    private Context context;
    private static final int REQUEST_CALL = 1;
    public BgAdapter(Context context, List<String> Models, List<String> models1) {
        this.Models = Models;
        this.context = context;
        this.models1=models1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.bg,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView2.setText(Models.get(position));
        holder.img.setImageBitmap(StringToBitMap(models1.get(position)));
        //holder.img.setImageURI(Uri.parse(models1.get(position)));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,MainActivity3.class);
                intent.putExtra("img",models1.get(position));
                intent.putExtra("title",Models.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return Models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CircleImageView img;
        CardView cardView;
        private TextView textView2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView2=(TextView)itemView.findViewById(R.id.bgdis);
            img=itemView.findViewById(R.id.circle_img);
            cardView=itemView.findViewById(R.id.card1);
        }
    }
    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte= Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }
}