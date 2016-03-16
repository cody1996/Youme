package com.codyme.youme.Views;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.codyme.youme.R;

import java.util.ArrayList;

/**
 * Created by cody on 2016/3/9.
 */
public class LoopViewPager extends InnerViewPager {
    private final String TAG = "LoopViewPager";

    private int mCount = 5;
    private int mIndex = 0;

    private Context mContext;

    private ArrayList<View> mViewList;
    private PagerAdapter mAdapter;

    public LoopViewPager(Context context) {
        super(context);

        mContext = context;

        initViews();
        initAdapter();
    }

    public LoopViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;

        initViews();
        initAdapter();
    }

    private void initViews() {

        mViewList = new ArrayList<View>();
        for (int i = 0; i < mCount; i++){
            ClickableImageView imageView = new ClickableImageView(mContext);
            imageView.setAdjustViewBounds(true);
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.banner_default));
            mViewList.add(imageView);
        }
    }

    private void initAdapter() {

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
