package com.codyme.youme.Adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cody on 2016/3/29.
 */
public class BannerPagerAdapter extends PagerAdapter {
    private List<View> itemList;
    private int mIndex;
    
    protected Context mContext;
    protected LayoutInflater mInflater;
    
    public BannerPagerAdapter (Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        
        itemList = new ArrayList<>();
        mIndex = 0;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (itemList.get(position) == null)
            container.removeView(itemList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        try {
            if (itemList.get(position).getParent() != null){
                ((ViewGroup)itemList.get(position).getParent())
                        .removeView(itemList.get(position));
            }
            container.addView(itemList.get(position));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mIndex = position;
        }
        return itemList.get(position);
    }

    public boolean add (View item){
        return itemList.add(item);
    }

    public View initItem(JSONObject info) {
        ImageView imageView = new ImageView(mContext);
        imageView.setAdjustViewBounds(true);
        try {
            imageView.setImageBitmap(
                    BitmapFactory.decodeStream(
                            mContext.getAssets().open(info.getString("cover"))
                    )
            );
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return imageView;
    }
}
