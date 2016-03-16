package com.codyme.youme;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codyme.youme.Adapters.GoodsItemAdapter;
import com.codyme.youme.Views.InnerListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class GoodsFragment extends Fragment {

    private View contentView;

    private InnerListView listMain;
    private GoodsItemAdapter mAdapter;

    public GoodsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        contentView = inflater.inflate(R.layout.fragment_goods, container, false);

        initList();

        return contentView;
    }

    private void initList() {
        listMain = (InnerListView) contentView.findViewById(R.id.list_goods_main);
        mAdapter = new GoodsItemAdapter(getContext());
        listMain.setAdapter(mAdapter);
    }

}
