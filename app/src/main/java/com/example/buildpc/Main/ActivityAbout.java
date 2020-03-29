package com.example.buildpc.Main;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buildpc.R;

public class ActivityAbout extends AppCompatActivity {
    private ListView lv;
    ImageView icon_close_about;
    private String tentv[] ={
            "\u25CF  Nguyễn Thái Bảo (Nhóm Trưởng)",
            "\u25CF  Lê Xuân Quốc Anh",
            "\u25CF  Nguyễn Xuân Hiếu",
            "\u25CF  Hữu Thành Chung"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        lv = findViewById(R.id.lv_tv);
        icon_close_about = findViewById(R.id.icon_close_about);

        icon_close_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,tentv);
        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ActivityAbout.this, "",  Toast.LENGTH_SHORT) .show();
            }
        });
    }
}
