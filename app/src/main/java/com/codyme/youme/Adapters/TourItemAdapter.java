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
            String priceString = info.getString("price");
            if (!priceString.equals("")){
                TextView priceTV = (TextView)item.findViewById(R.id.item_tour_price);

                SpannableStringBuilder priceStringBuilder = new SpannableStringBuilder();
                SpannableString symbolString = new SpannableString("￥");
                symbolString.setSpan(new RelativeSizeSpan(0.618f), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                priceStringBuilder.append(symbolString);

                String pricePart[] = priceString.split("\\.");
                priceStringBuilder.append(pricePart[0]);
                SpannableString decimalString = new SpannableString("." + pricePart[1]);
                decimalString.setSpan(new RelativeSizeSpan(0.618f), 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                priceStringBuilder.append(decimalString);

                priceTV.setVisibility(View.VISIBLE);
                priceTV.setText(priceStringBuilder);
            }
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
