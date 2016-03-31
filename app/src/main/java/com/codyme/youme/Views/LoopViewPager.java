package com.codyme.youme.Views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.codyme.youme.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by cody on 2016/3/9.
 */
public class LoopViewPager extends InnerViewPager {
    private final String TAG = "LoopViewPager";

    private int mCount = 6;
    private int mIndex = 0;

    private Context mContext;

    private ArrayList<View> mViewList;
    private PagerAdapter mAdapter;

    public LoopViewPager(Context context) {
        super(context);

        mContext = context;
    }

    public LoopViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
    }

    public void initViews(JSONArray infoList) {
        mViewList = new ArrayList<>();
        try {
            for (int i = 0; i < infoList.length(); i ++){
                ImageView imageView = new ImageView(mContext);
                imageView.setAdjustViewBounds(true);
                imageView.setImageBitmap(
                        BitmapFactory.decodeStream(
                                mContext.getAssets().open(
                                        infoList.getJSONObject(i).getString("cover")
                                )
                        )
                );
                mViewList.add(imageView);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void initAdapter() {

        mAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return mViewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                if (mViewList.get(position) == null)
                    container.removeView(mViewList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                try {
                    if (mViewList.get(position).getParent() != null){
                        ((ViewGroup)mViewList.get(position).getParent())
                                .removeView(mViewList.get(position));
                    }
                    container.addView(mViewList.get(position));
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    mIndex = position;
                }
                return mViewList.get(position);
            }
        };

        this.setAdapter(mAdapter);
    }
}
