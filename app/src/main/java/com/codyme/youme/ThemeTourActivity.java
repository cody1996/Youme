package com.codyme.youme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class ThemeTourActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private Spinner mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_tour);

        initToolbar();
        initSpinner();
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_theme_tour);
        mToolbar.setTitle("主题游");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
    }

    private void initSpinner() {
        mSpinner = (Spinner) findViewById(R.id.spinner_tour_theme_days);
        List<String> dataList = new ArrayList<String>();
        dataList.add("3");
        dataList.add("4");
        dataList.add("5");
        dataList.add("6");
        dataList.add("7");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dataList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(dataAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_only, menu);
        return true;
    }
}
