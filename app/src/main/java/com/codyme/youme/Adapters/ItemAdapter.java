package com.codyme.youme.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cody on 2016/3/10.
 */
public class ItemAdapter extends BaseAdapter {
    protected Context mContext;
    protected LayoutInflater mInflater;

    private List<View> itemList;

    public ItemAdapter (Context context){
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        itemList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView != itemList.get(position)) {
            convertView = itemList.get(position);
        }
        return convertView;
    }

    public View remove (int position){
        try {
            if (itemList.get(position) != null)
                return itemList.remove(position);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean add (View item){
        return itemList.add(item);
    }

    public View set (int position, View item){
        return itemList.set(position, item);
    }
}
