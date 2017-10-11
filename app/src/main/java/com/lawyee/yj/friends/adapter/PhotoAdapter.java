package com.lawyee.yj.friends.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lawyee.yj.friends.R;

import java.io.File;
import java.util.ArrayList;

import me.iwf.photopicker.utils.AndroidLifecycleUtils;

/**
 * @Author : Yufeilong  is Creating a porject in
 * @Email : yufeilong92@163.com
 * @Time : :
 * @Purpose :PhotoAdapter 适配器
 */



public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {
public static final String TAG = "查询结果";
  private ArrayList<String> photoPaths = new ArrayList<String>();
  private LayoutInflater inflater;

  private Context mContext;


  public PhotoAdapter(Context mContext, ArrayList<String> photoPaths) {
    this.photoPaths = photoPaths;
    this.mContext = mContext;
    inflater = LayoutInflater.from(mContext);

  }


  @Override public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = inflater.inflate(me.iwf.photopicker.R.layout.__picker_item_photo, parent, false);
    return new PhotoViewHolder(itemView);
  }


  @Override
  public void onBindViewHolder(final PhotoViewHolder holder, final int position) {
    Log.e(TAG, ":======= "+position  +"======="+photoPaths.size()  );
    if (position==0&&photoPaths.size()==0){
      holder.ivPhoto.setImageResource(R.mipmap.camera);

    }else {
      Uri uri = Uri.fromFile(new File(photoPaths.get(position)));
      boolean canLoadImage = AndroidLifecycleUtils.canLoadImage(holder.ivPhoto.getContext());

      if (canLoadImage) {
        Glide.with(mContext)
                .load(uri)
                .centerCrop()
                .thumbnail(0.1f)
                .placeholder(me.iwf.photopicker.R.drawable.__picker_ic_photo_black_48dp)
                .error(me.iwf.photopicker.R.drawable.__picker_ic_broken_image_black_48dp)
                .into(holder.ivPhoto);
      }
    }


  }


  @Override public int getItemCount() {
    return photoPaths.size();
  }


  public static class PhotoViewHolder extends RecyclerView.ViewHolder {
    private ImageView ivPhoto;
    private View vSelected;
    public PhotoViewHolder(View itemView) {
      super(itemView);
      ivPhoto   = (ImageView) itemView.findViewById(me.iwf.photopicker.R.id.iv_photo);
      vSelected = itemView.findViewById(me.iwf.photopicker.R.id.v_selected);
      vSelected.setVisibility(View.GONE);
    }
  }

}
