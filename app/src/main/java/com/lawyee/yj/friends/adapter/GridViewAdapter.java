package com.lawyee.yj.friends.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.lawyee.yj.friends.R;

import java.util.List;

/**
 * @Author : Yufeilong  is Creating a porject in YFPHILPS
 * @Email : yufeilong92@163.com
 * @Time :2016/12/1 10:09
 * @Purpose : GridViewAdapter图片适配器
 */

public class GridViewAdapter extends BaseAdapter {

    private List<String> mData;
    private Context mContext;
    private final LayoutInflater inflater;

    public GridViewAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mData = list;
        inflater = LayoutInflater.from(mContext);

    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData == null ? null : mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View converview, ViewGroup viewGroup) {
        ViewHolder holder;
        if (converview == null) {
            converview = inflater.inflate(R.layout.list_item_pct, null);
            holder = new ViewHolder(converview);
            converview.setTag(holder);
        } else {
            holder = (ViewHolder) converview.getTag();
        }

        if (i == mData.size()) {
            holder.list_item_pct.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.jy_drltsz_btn_addperson));
        } else {
            loading(i, holder);

        }
//        holder.list_item_pct.setImageURI(uri);
        return converview;
    }

    private void loading(int i, ViewHolder holder) {

        Uri uri = Uri.parse(mData.get(i));

        ImageRequest request = ImageRequestBuilder
                .newBuilderWithSource(uri)
                .setProgressiveRenderingEnabled(true)
                .build();
        PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(holder.list_item_pct.getController())
                .build();
        holder.list_item_pct.setAspectRatio(2.1F);
        holder.list_item_pct.setController(controller);
//        holder.list_item_pct.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER);
//        Glide.with(mContext)
//                .load(mData.get(i))
//                .centerCrop()
//                .skipMemoryCache(false)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .placeholder(me.iwf.photopicker.R.drawable.__picker_ic_photo_black_48dp)
//                .error(me.iwf.photopicker.R.drawable.__picker_ic_broken_image_black_48dp)
//                .into(holder.list_item_pct);
    }


    public static class ViewHolder {
        public View rootView;
        public SimpleDraweeView list_item_pct;
        public ImageView v_selecteds;
        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.list_item_pct = (SimpleDraweeView) rootView.findViewById(R.id.list_item_pct);
//            this.v_selecteds = (ImageView) rootView.findViewById(R.id.v_selecteds);
        }

    }

}
