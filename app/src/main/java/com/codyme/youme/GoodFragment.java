package com.codyme.youme;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codyme.youme.Adapters.GoodItemAdapter;
import com.codyme.youme.Views.InnerListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class GoodFragment extends Fragment {

    private View contentView;

    private InnerListView listMain;
    private GoodItemAdapter mAdapter;

    public GoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        contentView = inflater.inflate(R.layout.fragment_good, container, false);

        initList();

        return contentView;
    }

    private void initList() {
        listMain = (InnerListView) contentView.findViewById(R.id.list_goods_main);
        mAdapter = new GoodItemAdapter(getContext());
        listMain.setAdapter(mAdapter);
    }

}
