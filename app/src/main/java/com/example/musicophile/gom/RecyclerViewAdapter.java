package com.example.musicophile.gom;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import static com.example.musicophile.gom.R.id.ImageNameTextView;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

        Context context;
        List<ImageUploadInfo> MainImageUploadInfoList;



    public RecyclerViewAdapter(Context context, List<ImageUploadInfo> TempList) {

        this.MainImageUploadInfoList = TempList;

        this.context = context;

        }


    @Override
public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
        }

@Override
public void onBindViewHolder(ViewHolder holder, int position) {
        ImageUploadInfo UploadInfo = MainImageUploadInfoList.get(position);
  //  if(MainImageUploadInfoList.get(position)){
  //      view.setvisiblity(View.VISIBLE);

   // }else{
   //     view.setvisiblitygg(View.GONE);
  //  }

    holder.imageNameTextView.setText(UploadInfo.getName());
    holder.Amount.setText(UploadInfo.getAmount());
    holder.Price.setText(UploadInfo.getPrice());
    holder.Duration.setText(UploadInfo.getDuration());


        //Loading image from Glide library.
        Glide.with(context).load(UploadInfo.getImageURL()).into(holder.imageView);
        }

@Override
public int getItemCount() {

        return MainImageUploadInfoList.size();
        }

class ViewHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;
    public TextView imageNameTextView, Amount, Price, Duration;

    public ViewHolder(final View itemView) {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.imageView);

        imageNameTextView = (TextView) itemView.findViewById(ImageNameTextView);
        Amount = (TextView) itemView.findViewById(R.id.ImageNameTextView_c);
        Price = (TextView) itemView.findViewById(R.id.ImageNameTextView_b);
        Duration = (TextView) itemView.findViewById(R.id.ImageNameTextView_a);

    }
}
}
