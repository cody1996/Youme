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
 * Created by cody on 2016/3/10.
 */
public class TourItemAdapter extends ItemAdapter {

    public TourItemAdapter(Context context) {
        super(context);
    }

    public View initItem (JSONObject info){
        View item = mInflater.inflate(R.layout.item_tour, null);
        try {
            ((ImageView)item.findViewById(R.id.item_tour_cover))
                    .setImageBitmap(
                            BitmapFactory.decodeStream(
                                    mContext.getAssets().open(info.getString("cover"))
                            )
                    );
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ((TextView)item.findViewById(R.id.item_tour_title)).setText(info.getString("title"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            ((TextView)item.findViewById(R.id.item_tour_subtitle)).setText(info.getString("subtitle"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            ((TextView)item.findViewById(R.id.item_tour_price)).setText("￥"+info.getString("price"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            ((TextView)item.findViewById(R.id.item_tour_tag)).setText(info.getString("tag"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return item;
    }
}
