package com.codyme.youme.Adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
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
public class GoodItemAdapter extends ItemAdapter {

    public GoodItemAdapter(Context context) {
        super(context);
    }

    public View initItem (JSONObject info){
        View item = mInflater.inflate(R.layout.item_good, null);
        try {
            ((ImageView) item.findViewById(R.id.item_good_cover))
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
            ((TextView) item.findViewById(R.id.item_good_title)).setText(info.getString("title"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            ((TextView) item.findViewById(R.id.item_good_tag)).setText(info.getString("tag"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            SpannableStringBuilder priceStringBuilder = new SpannableStringBuilder();
            SpannableString symbolString = new SpannableString("￥");
            symbolString.setSpan(new RelativeSizeSpan(0.618f), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            priceStringBuilder.append(symbolString);

            String price[] = info.getString("price").split("\\.");
            priceStringBuilder.append(price[0]);
            SpannableString decimalString = new SpannableString("." + price[1]);
            decimalString.setSpan(new RelativeSizeSpan(0.618f), 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            priceStringBuilder.append(decimalString);

            ((TextView) item.findViewById(R.id.item_good_price)).setText(priceStringBuilder);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            ((TextView) item.findViewById(R.id.item_good_sales))
                    .setText(String.valueOf(info.getInt("sales")) + "人已购买");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return item;
    }
}
