package com.roshine.testwheelview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * @author Roshine
 * @date 2018/5/7 21:17
 * @blog http://www.roshine.xyz
 * @email roshines1016@gmail.com
 * @github https://github.com/Roben1016
 * @phone 136****1535
 * @desc
 */
public class WheelViewActivity extends Activity implements View.OnClickListener, OnWheelChangedListener, OnWheelScrollListener {

    private TextView tvCancel;
    private TextView btnSave;
    private TextView display;
    private WheelView mWheelView;
    private String[] data = new String[]{"即时", "一周以内", "一个月内", "1-3个月", "三月以后", "待定"};
    private ArrayWheelAdapter<String> mAdapter;
    private String mCurrentString;
    private String result;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_wheel);
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(lp);
        getWindow().setGravity(Gravity.BOTTOM);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        tvCancel = (TextView) findViewById(R.id.tv_job_intension_cancel);
        btnSave = (TextView) findViewById(R.id.tv_job_intension_sure);
        display = (TextView) findViewById(R.id.tv_job_intension_display_edit);
        mWheelView = (WheelView) findViewById(R.id.wv_job_intension_jobtypes);
        mWheelView.setWheelBackground(R.color.white);
        btnSave.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
    }

    private void initListener() {
        mWheelView.addChangingListener(this);
        mWheelView.addScrollingListener(this);
    }

    private void initData() {
        mAdapter = new ArrayWheelAdapter<>(this, data);
        mWheelView.setViewAdapter(mAdapter);//添加适配器
        mWheelView.setVisibleItems(7);// 设置可见条目数量
//		mViewJobType.setWheelBackground(R.color.white_alphe1);
        mAdapter.setTextColor(getResources().getColor(R.color.black));//设置所有字体的颜色   配合WheelView中的SHADOWS_COLORS透明度就可以设置中间文字高亮
//		mAdapter.setTextSize(20);//设置所有文本打字体大小
        mWheelView.setCurrentItem(0);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        mCurrentString = data[newValue];
    }



    @Override
    public void onScrollingStarted(WheelView wheel) {
        //滚动开始时的监听
        int i = mWheelView.getCurrentItem();
        result = data[i];
    }

    @Override
    public void onScrollingFinished(WheelView wheel) {
//滚动结束时的监听
        int currentItem = mWheelView.getCurrentItem();
//		Log.d(TAG, "选中的条目："+currentItem+"---"+data[currentItem]);
        mCurrentString = data[currentItem];
        display.setText(mCurrentString);
    }
}
