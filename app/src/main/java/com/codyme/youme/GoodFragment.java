package com.codyme.youme;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codyme.youme.Adapters.GoodItemAdapter;
import com.codyme.youme.Adapters.StoreItemAdapter;
import com.codyme.youme.Utils.JSONHelper;
import com.codyme.youme.Views.InnerListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class GoodFragment extends Fragment {

    private View contentView;

    private InnerListView listMain;
    private StoreItemAdapter mAdapter;

    public GoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        contentView = inflater.inflate(R.layout.fragment_good, container, false);

        initList();
        contentView.findViewById(R.id.btn_good_store).setSelected(true);

        return contentView;
    }

    private void initList() {
        listMain = (InnerListView) contentView.findViewById(R.id.list_goods_main);
        mAdapter = new StoreItemAdapter(getContext());
        listMain.setAdapter(mAdapter);

        try {
            JSONObject result = JSONHelper.buildObjectFromAssets(getContext(), "json/good_main_store.json");
            JSONArray infoList = null;

            if (result != null)
                infoList = result.getJSONArray("list");

            for(int i = 0; i < infoList.length(); i++){
                mAdapter.add(mAdapter.initItem(infoList.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
