package com.codyme.youme.Adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.codyme.youme.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by cody on 2016/3/14.
 */
public class MsgItemAdapter extends ItemAdapter {
    public MsgItemAdapter(Context context) {
        super(context);
    }

    public View initItem(JSONObject info) {
        View item = mInflater.inflate(R.layout.item_msg, null);
        try {
            ((ImageView)item.findViewById(R.id.item_msg_head))
                    .setImageBitmap(
                            BitmapFactory.decodeStream(
                                    mContext.getAssets().open(info.getString("head"))
                            )
                    );
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            ((TextView)item.findViewById(R.id.item_msg_nickname)).setText(info.getString("nickname"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            ((TextView)item.findViewById(R.id.item_msg_lastmsg)).setText(info.getString("lastmsg"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            ((TextView)item.findViewById(R.id.item_msg_lasttime)).setText(info.getString("lasttime"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            ((TextView)item.findViewById(R.id.item_msg_unreadcount)).setText(info.getString("unreadcount"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return item;
    }
}
