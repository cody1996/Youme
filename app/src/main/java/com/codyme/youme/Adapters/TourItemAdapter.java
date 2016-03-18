package com.codyme.youme.Adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.codyme.youme.R;

import org.json.JSONObject;

/**
 * Created by cody on 2016/3/10.
 */
public class TourItemAdapter extends ItemAdapter {

    public TourItemAdapter(Context context) {
        super(context);
        add(initItem(null));
        add(initItem(null));
        add(initItem(null));
        add(initItem(null));
        add(initItem(null));
    }

    public View initItem (JSONObject info){
        View item = mInflater.inflate(R.layout.item_tour, null);
        return item;
    }
}
