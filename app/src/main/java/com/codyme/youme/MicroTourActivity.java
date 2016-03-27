package com.codyme.youme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.codyme.youme.Adapters.TourItemAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MicroTourActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private ListView listMain;
    private TourItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_micro_tour);

        initToolbar();
        initList();
    }

    private void initList() {
        findViewById(R.id.btn_micro_town).setSelected(true);
        listMain = (ListView) findViewById(R.id.list_micro_tour_main);
        mAdapter = new TourItemAdapter(this);
        listMain.setAdapter(mAdapter);

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open("tour_micro_town.json"), "UTF-8"));
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }
            JSONArray infoList = new JSONObject(builder.toString()).getJSONArray("list");

            for(int i = 0; i < infoList.length(); i++){
                mAdapter.add(mAdapter.initItem(infoList.getJSONObject(i)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initToolbar() {

        mToolbar = (Toolbar) findViewById(R.id.toolbar_micro_tour);
        mToolbar.setTitle("微旅行");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_only, menu);
        return true;
    }
}
