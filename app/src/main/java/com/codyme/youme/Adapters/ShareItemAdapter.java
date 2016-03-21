package com.codyme.youme.Adapters;

import android.content.Context;
import android.view.View;

import com.codyme.youme.R;

import org.json.JSONObject;

/**
 * Created by cody on 2016/3/16.
 */
public class ShareItemAdapter extends ItemAdapter {
    public ShareItemAdapter(Context context) {
        super(context);
        add(initItem(null));
        add(initItem(null));
        add(initItem(null));
        add(initItem(null));
        add(initItem(null));
    }

    public View initItem (JSONObject info) {
        View item = mInflater.inflate(R.layout.item_share, null);
        return item;
    }
}
