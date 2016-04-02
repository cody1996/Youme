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
 * Created by cody on 2016/3/31.
 */
public class StoreItemAdapter extends ItemAdapter {
    public StoreItemAdapter(Context context) {
        super(context);
    }

    public View initItem (JSONObject info){
        View item = mInflater.inflate(R.layout.item_store, null);
        try {
            ((ImageView) item.findViewById(R.id.item_store_cover))
                    .setImageBitmap(
                            BitmapFactory.decodeStream(
                                    mContext.getAssets().open(info.getString("cover"))
                            ));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            ((TextView) item.findViewById(R.id.item_store_title)).setText(info.getString("title"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            ((TextView) item.findViewById(R.id.item_store_subtitle)).setText(info.getString("subtitle"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            ((TextView) item.findViewById(R.id.item_store_tag)).setText(info.getString("tag"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            ((TextView) item.findViewById(R.id.item_store_sales))
                    .setText(String.valueOf(info.getInt("sales")) + "人已购买");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return item;
    }
}
