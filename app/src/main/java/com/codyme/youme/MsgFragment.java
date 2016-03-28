package com.codyme.youme;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.codyme.youme.Adapters.MsgItemAdapter;
import com.codyme.youme.Utils.JSONHelper;
import com.codyme.youme.Views.InnerListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class MsgFragment extends Fragment {

    private View contentView;

    private InnerListView listMain;
    private MsgItemAdapter mAdapter;

    public MsgFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        contentView = inflater.inflate(R.layout.fragment_msg, container, false);

        initList();

        return contentView;
    }

    private void initList() {
        listMain = (InnerListView) contentView.findViewById(R.id.list_msg_main);
        mAdapter = new MsgItemAdapter(getContext());
        listMain.setAdapter(mAdapter);

        try {
            JSONObject result = JSONHelper.buildObjectFromAssets(getContext(), "json/msg_main.json");
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
