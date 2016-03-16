package com.codyme.youme;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codyme.youme.Adapters.TourItemAdapter;
import com.codyme.youme.Views.InnerListView;
import com.codyme.youme.Views.ViewPagerIndicator;


/**
 * A simple {@link Fragment} subclass.
 */
public class TourFragment extends Fragment {

    private View contentView;

    private View bannerMain;
    private ViewPager pagerMain;
    private ViewPagerIndicator indicatorMain;

    private InnerListView listMain;
    private TourItemAdapter mAdapter;

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

        return contentView;
    }

    private void initBanner() {
        bannerMain = contentView.findViewById(R.id.banner_tour_main);
        pagerMain = (ViewPager) bannerMain.findViewById(R.id.pager_banner);
        indicatorMain = (ViewPagerIndicator) bannerMain.findViewById(R.id.indicator_banner);
        indicatorMain.setViewPager(pagerMain);
    }

    private void initList() {
        listMain = (InnerListView) contentView.findViewById(R.id.list_tour_main);
        mAdapter = new TourItemAdapter(getContext());
        listMain.setAdapter(mAdapter);

    }

}
