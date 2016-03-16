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
public class GoodsItemAdapter extends ItemAdapter {

    public GoodsItemAdapter(Context context) {
        super(context);
        add(initItem(null));
        add(initItem(null));
        add(initItem(null));
        add(initItem(null));
        add(initItem(null));
    }

    public View initItem (JSONObject info){
        View item = mInflater.inflate(R.layout.item_tour_goods, null);
        ImageView cover = (ImageView) item.findViewById(R.id.item_cover);
        TextView title = (TextView) item.findViewById(R.id.item_title);
        TextView tag = (TextView) item.findViewById(R.id.item_tag);
        TextView price = (TextView) item.findViewById(R.id.item_price);
        return item;
    }
}
