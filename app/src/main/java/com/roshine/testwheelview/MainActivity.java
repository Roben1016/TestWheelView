package com.roshine.testwheelview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.roshine.testwheelview.pickview.PickerScrollView;
import com.roshine.testwheelview.pickview.Pickers;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PickerScrollView.onSelectListener {
    private PickerScrollView pickerScrollView;
    private ArrayList<Pickers> list;
    private String[] id;
    private String[] name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,WheelViewActivity.class));
            }
        });
        pickerScrollView = findViewById(R.id.picker_view);
        pickerScrollView.setOnSelectListener(this);
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        list = new ArrayList<Pickers>();
        id = new String[] { "1", "2", "3", "4", "5", "6" };
        name = new String[] { "中国银行", "农业银行", "招商银行", "工商银行", "建设银行", "民生银行" };
        for (int i = 0; i < name.length; i++) {
            list.add(new Pickers(name[i], id[i]));
        }
        // 设置数据，默认选择第一条
        pickerScrollView.setData(list);
        pickerScrollView.setSelected(0);
    }

    @Override
    public void onSelect(Pickers pickers) {
        Toast.makeText(getApplicationContext(), "选中"+pickers.getShowConetnt(), Toast.LENGTH_SHORT).show();
    }
}
