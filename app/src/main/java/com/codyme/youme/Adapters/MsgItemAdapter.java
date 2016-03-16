package com.codyme.youme.Adapters;

import android.content.Context;
import android.view.View;
import com.codyme.youme.R;
import org.json.JSONObject;

/**
 * Created by cody on 2016/3/14.
 */
public class MsgItemAdapter extends ItemAdapter {
    public MsgItemAdapter(Context context) {
        super(context);
        add(initItem(null));
        add(initItem(null));
        add(initItem(null));
        add(initItem(null));
        add(initItem(null));
    }

    public View initItem(JSONObject info) {
        View item = mInflater.inflate(R.layout.item_msg, null);
        return item;
    }
}
