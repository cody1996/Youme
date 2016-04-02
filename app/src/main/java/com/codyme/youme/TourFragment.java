package com.codyme.youme;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codyme.youme.Adapters.TourItemAdapter;
import com.codyme.youme.Utils.JSONHelper;
import com.codyme.youme.Views.InnerListView;
import com.codyme.youme.Views.LoopViewPager;
import com.codyme.youme.Views.ViewPagerIndicator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class TourFragment extends Fragment {

    private View contentView;

    private Intent gatewayIntent = new Intent();

    private View bannerMain;
    private LoopViewPager pagerMain;
    private ViewPagerIndicator indicatorMain;

    private InnerListView listMain;
    private TourItemAdapter tourItemAdapter;

    public TourFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        contentView = inflater.inflate(R.layout.fragment_tour, container, false);

        initBanner();
        initList();
        initGateway();

        return contentView;
    }

    private void initBanner() {
        bannerMain = contentView.findViewById(R.id.banner_tour_main);
        pagerMain = (LoopViewPager) bannerMain.findViewById(R.id.pager_banner);
        try {
            pagerMain.initViews(
                    JSONHelper.buildObjectFromAssets(
                            getContext(), "json/banner_tour_main.json"
                    ).getJSONArray("list")
            );
            pagerMain.initAdapter();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        indicatorMain = (ViewPagerIndicator) bannerMain.findViewById(R.id.indicator_banner);
        indicatorMain.setViewPager(pagerMain);
    }

    private void initList() {
        listMain = (InnerListView) contentView.findViewById(R.id.list_tour_main);
        tourItemAdapter = new TourItemAdapter(getContext());
        listMain.setAdapter(tourItemAdapter);

        try {
            JSONObject result = JSONHelper.buildObjectFromAssets(getContext(), "json/tour_main.json");
            JSONArray infoList = null;

            if (result != null)
                infoList = result.getJSONArray("list");

            for(int i = 0; i < infoList.length(); i++){
                tourItemAdapter.add(tourItemAdapter.initItem(infoList.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void initGateway() {
        View.OnClickListener gatewayListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.gateway_micro_tour :
                        gatewayIntent.setClass(getContext(), MicroTourActivity.class);
                        break;
                    case R.id.gateway_theme_tour :
                        gatewayIntent.setClass(getContext(), ThemeTourActivity.class);
                        break;
                }
                startActivity(gatewayIntent);
            }
        };

        contentView.findViewById(R.id.gateway_micro_tour).setOnClickListener(gatewayListener);
        contentView.findViewById(R.id.gateway_theme_tour).setOnClickListener(gatewayListener);
    }

}
