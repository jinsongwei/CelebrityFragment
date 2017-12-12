package com.williamjin.celebrityfragment.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.williamjin.celeberity.R;
import com.williamjin.celeberity.model.Celebrity;
import com.williamjin.celeberity.model.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ListCelebrityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_celebrity);
    }

    public void handleListCelebrities(View view) {
        ListView lvList = findViewById(R.id.lv_list);
        ArrayList<String> list;
        ArrayAdapter<String> adapter;
        DatabaseHelper db = new DatabaseHelper(this);
        switch (view.getId()) {
            case R.id.btn_list_celebrities:
                List<Celebrity> celebrityList = db.listCelebrities();
                list = new ArrayList<>();
                for(Celebrity c : celebrityList){
                    list.add(c.toString());
//                    Log.e("list result", c.toString() + " -- " + c.getFavorite());
                }
                adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.tv_list_item, list);
                lvList.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
    }
}
