package com.codyme.youme.Adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
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
    private final String TAG = "MsgItemAdapter";

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
            ((TextView)item.findViewById(R.id.item_msg_last_msg)).setText(info.getString("last_msg"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            ((TextView)item.findViewById(R.id.item_msg_last_time)).setText(info.getString("last_time"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            int unread_count = info.getInt("unread_count");
            if (unread_count > 0){
                //Log.i(TAG, "unread_count > 0");
                TextView unreadTV = (TextView)item.findViewById(R.id.item_msg_unread_count);
                unreadTV.setVisibility(View.VISIBLE);
                if (unread_count > 99){
                    //Log.i(TAG, "unread_count > 99");
                    unreadTV.setText("99+");
                } else {
                    //Log.i(TAG, "0 < unread_count < 99");
                    unreadTV.setText(String.valueOf(unread_count));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }
}
