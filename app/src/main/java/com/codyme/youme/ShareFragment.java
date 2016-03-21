package com.codyme.youme;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codyme.youme.Adapters.ShareItemAdapter;
import com.codyme.youme.Views.InnerListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShareFragment extends Fragment {

    private View contentView;

    private InnerListView listMain;
    private ShareItemAdapter mAdapter;

    public ShareFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        contentView = inflater.inflate(R.layout.fragment_share, container, false);

        initList();
        contentView.findViewById(R.id.btn_share_all).setSelected(true);

        return contentView;
    }

    private void initList() {
        listMain = (InnerListView) contentView.findViewById(R.id.list_share_main);
        mAdapter = new ShareItemAdapter(getContext());
        listMain.setAdapter(mAdapter);
    }

}
