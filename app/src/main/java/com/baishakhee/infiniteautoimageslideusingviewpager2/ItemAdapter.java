package com.baishakhee.infiniteautoimageslideusingviewpager2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

   List<ItemModel> itemModelList;
   Context context;
   ViewPager2 viewPager2;

    public ItemAdapter(List<ItemModel> itemModelList, Context context) {

    }

    public ItemAdapter(Context context, List<ItemModel> itemModelList, ViewPager2 viewPager2) {
        this.itemModelList = itemModelList;
        this.context = context;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public ItemAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.slide_item_container, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ItemViewHolder holder, int position) {

     //   holder.setRoundedImageView(itemModelList.get(position).getImage());
        Glide.with(context)
                .load(itemModelList.get(position).getImage()).
                into(holder.roundedImageView);



        if (position==itemModelList.size()-2){
            viewPager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return itemModelList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        RoundedImageView roundedImageView;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            roundedImageView=itemView.findViewById(R.id.roundImageViewSlider);
        }

        void setRoundedImageView(ItemModel itemModel){
            roundedImageView.setBackgroundResource(itemModel.getImage());
        }
    }


    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            itemModelList.addAll(itemModelList);
            notifyDataSetChanged();
        }
    };
}
