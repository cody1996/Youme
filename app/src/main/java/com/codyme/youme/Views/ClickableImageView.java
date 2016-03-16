package com.codyme.youme.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.codyme.youme.R;

/**
 * Created by cody on 2016/3/9.
 */
public class ClickableImageView extends ImageView {
    public ClickableImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ClickableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ClickableImageView(Context context) {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                this.setColorFilter(getResources().getColor(R.color.trans_dark_2));
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                this.setColorFilter(null);
                break;
        }
        return super.onTouchEvent(event);
    }
}
