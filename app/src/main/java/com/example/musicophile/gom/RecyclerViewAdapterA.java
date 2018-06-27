package com.example.musicophile.gom;

import android.app.LauncherActivity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;



public class RecyclerViewAdapterA extends RecyclerView.Adapter<RecyclerViewAdapterA.ViewHolder> {

    Context context;
    List<ImageUploadInfo> list;


    public RecyclerViewAdapterA(Context context,     List<ImageUploadInfo> list){
      //  this.MainImageUploadInfoList = templist;

        this.context = context;
        this.list = list;


    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_moreinfo, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder (final ViewHolder holder, int position) {

        ImageUploadInfo moreProductInfo = list.get(position);


        holder.imageNameTextView.setText(moreProductInfo.getName());
        holder.Amount.setText(moreProductInfo.getAmount());
        holder.Price.setText(moreProductInfo.getPrice());
        holder.Duration.setText(moreProductInfo.getDuration());
       // holder.FName.setText(moreProductInfo.getFName());
     //   holder.Flocation.setText(moreProductInfo.getFlocation());
      //  holder.FEmail.setText(moreProductInfo.getFEmail());
     //   holder.FPhonenumber.setText(moreProductInfo.getFPhonenumber());

        Glide.with(context).load(moreProductInfo.getImageURL()).into(holder.imageView);
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
       public ImageView imageView;
        public TextView imageNameTextView, Amount, Price, Duration;

        public ViewHolder(final View itemView) {
            super(itemView);


                imageView = (ImageView) itemView.findViewById(R.id.imageView);
             imageNameTextView = (TextView)itemView.findViewById(R.id.ImageNameTextVie);
                Amount = (TextView) itemView.findViewById(R.id.ImageNameTextView_c);
                Price = (TextView) itemView.findViewById(R.id.ImageNameTextView_b);
                Duration = (TextView) itemView.findViewById(R.id.ImageNameTextView_a);
          //  FName = (TextView) itemView.findViewById(R.id.ImageNameTextView_);
        //    Flocation = (TextView) itemView.findViewById(R.id.ImageNameTextView_r);
         //   FEmail = (TextView) itemView.findViewById(R.id.ImageNameTextView_i);
         //   FPhonenumber = (TextView) itemView.findViewById(R.id.ImageNameTextView_e);
        }
    }
}
