package com.damontung.xunzhengjc;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.*;

import java.util.Calendar;
import java.util.Date;

public class RCLFragment extends Fragment {
    private String TAG = "DamonTung";
    //热处理
    private TextView rcl_gqwd;//干球温度
    private TextView rcl_sqwd;//湿球温度
    private TextView rcl_hjwd;//环境温度
    private TextView rcl_mxwd;//木芯温度
    private TextView rcl_xdsd;//相对湿度

    private TextView rcl_gqwd_text;
    private TextView rcl_sqwd_text;
    private TextView rcl_hjwd_text;
    private TextView rcl_mxwd_text;
    private TextView rcl_xdsd_text;

    private TextView rcl_gqwd_text_tag;
    private TextView rcl_sqwd_text_tag;
    private TextView rcl_hjwd_text_tag;
    private TextView rcl_mxwd_text_tag;
    private TextView rcl_xdsd_text_tag;

    private TextView rcl_gqwd_tag;
    private TextView rcl_sqwd_tag;
    private TextView rcl_hjwd_tag;
    private TextView rcl_mxwd_tag;
    private TextView rcl_xdsd_tag;


    //处理开始时间、结束时间
    private Date time_start_set,time_end_set;
    private TextView start_time_text;
    private TextView end_time_text;
    private TextView start_time;
    private TextView end_time;
    private TextView set_time;
    private EditText showDateStart;
    private EditText showDateEnd;

    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;

    private static final int SHOW_DATAPICK = 0;
    private static final int DATE_DIALOG_ID = 1;

    private TextView rcl_tag;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rcl,container,false);

        initView(view);
        return view;
    }

   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_rcl);

        initView();

    }*/
    private void initView(View view){
        //干球溫度
        LinearLayout layout1 = (LinearLayout)view.findViewById(R.id.show_data_gqwd).findViewById(R.id.layout_show_data);
        rcl_gqwd = (TextView)layout1.findViewById(R.id.data_num);
        rcl_gqwd_text = (TextView) layout1.findViewById(R.id.rcl_item_name);
        rcl_gqwd_text.setText(R.string.rcl_gqwd);
        rcl_gqwd.setText("35");


        //濕球溫度
        LinearLayout layout2 = (LinearLayout)view.findViewById(R.id.show_data_sqwd).findViewById(R.id.layout_show_data);
        rcl_sqwd_text = (TextView)layout2.findViewById(R.id.rcl_item_name);
        rcl_sqwd_text.setText(R.string.rcl_sqwd);
        rcl_sqwd = (TextView) layout2.findViewById(R.id.data_num);
        rcl_sqwd.setText("26");

        //環境溫度
        LinearLayout layout3 = (LinearLayout) view.findViewById(R.id.rcl_layout_hjwd).findViewById(R.id.item_tag);
        rcl_hjwd_text = (TextView)layout3.findViewById(R.id.item_name);
        rcl_hjwd_text.setText(R.string.rcl_hjwd);
        rcl_hjwd = (TextView)layout3.findViewById(R.id.data);
        rcl_hjwd.setText("32");
        int k = Integer.parseInt(rcl_hjwd.getText().toString());
        rcl_hjwd_text_tag = (TextView)layout3.findViewById(R.id.item_color_tag);
        setBkColor(rcl_hjwd_text_tag,k);

        //木芯溫度
        LinearLayout layout4 = (LinearLayout)view.findViewById(R.id.rcl_layout_mxwd).findViewById(R.id.item_tag);
        rcl_mxwd_text = (TextView)layout4.findViewById(R.id.item_name);
        rcl_mxwd_text.setText(R.string.rcl_mxwd);
        rcl_mxwd = (TextView)layout4.findViewById(R.id.data);
        rcl_mxwd.setText("42");
        k = Integer.parseInt(rcl_mxwd.getText().toString());
        rcl_mxwd_text_tag = (TextView)layout4.findViewById(R.id.item_color_tag);
        setBkColor(rcl_mxwd_text_tag,k);

        //相對濕度
        LinearLayout layout5 = (LinearLayout)view.findViewById(R.id.rcl_layout_xdsd).findViewById(R.id.item_tag);
        rcl_xdsd = (TextView)layout5.findViewById(R.id.data);
        rcl_xdsd.setText("56");
        rcl_xdsd_text = (TextView)layout5.findViewById(R.id.item_name);
        rcl_xdsd_text.setText(R.string.rcl_xdsd);
        rcl_xdsd_tag = (TextView)layout5.findViewById(R.id.item_tag_1);
        rcl_xdsd_tag.setText(R.string.sd);
        rcl_xdsd_text_tag = (TextView)layout5.findViewById(R.id.item_color_tag);
        k = Integer.parseInt(rcl_xdsd.getText().toString());
        setBkColor(rcl_xdsd_text_tag,k);

        //開始時間設置
        LinearLayout layout6 = (LinearLayout)view.findViewById(R.id.time_start).findViewById(R.id.item_set_data);
        start_time_text = (TextView)layout6.findViewById(R.id.s_e_time_text);
        start_time_text.setText(R.string.time_start);
        start_time = (TextView)layout6.findViewById(R.id.set_date);
        setDate(start_time);

        start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG,"start-time  clicked...");
                set_time = (TextView)v;
                Message msg = new Message();
                msg.what = RCLFragment.SHOW_DATAPICK;
                dateAndTimeHandler.sendMessage(msg);

            }
        });
        //結束時間設置
        LinearLayout layout7 = (LinearLayout)view.findViewById(R.id.time_end).findViewById(R.id.item_set_data);
        end_time_text = (TextView)layout7.findViewById(R.id.s_e_time_text);
        end_time_text.setText(R.string.time_end);
        end_time = (TextView)layout7.findViewById(R.id.set_date);
        setEndDate(end_time);

        end_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG,"end-time  clicked...");
                set_time = (TextView)v;
                Message msg = new Message();
                msg.what = RCLFragment.SHOW_DATAPICK;
                //RCLFragment.this.dateAndTimeHandler.sendMessage(msg);
            }
        });


    }
    private void setDate(View v){
        final Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        set_time = (TextView)v;
        updateDateDisplay();
    }
    private void setEndDate(View v){
        final Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = (calendar.get(Calendar.MONTH) + 1 ) % 12;
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        set_time = (TextView)v;
        updateDateDisplay();
    }

    private void updateDateDisplay(){
        set_time.setText(new StringBuilder().append(mYear).append("-")
                .append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1)).append("-")
                .append((mDay < 10) ? "0" + mDay : mDay));
    }
    /**
     * 处理日期和时间控件的Handler
     */
    Handler dateAndTimeHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case RCLFragment.SHOW_DATAPICK:
                    getActivity().showDialog(DATE_DIALOG_ID);
                    break;
            }
        }
    };


    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(getActivity(), mDateSetListener, mYear, mMonth,
                        mDay);
        }
        return null;
    }


    protected void onPrepareDialog(int id, Dialog dialog) {
        switch (id) {
            case DATE_DIALOG_ID:
                ((DatePickerDialog) dialog).updateDate(mYear, mMonth, mDay);
                break;

        }
    }
    /**
     * 日期控件的事件
     */
    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;

            updateDateDisplay();
        }
    };

    private void setBkColor(View v, int k){
        if( k <= 40 ){
            v.setBackgroundDrawable(getResources().getDrawable(R.drawable.green));
        }else if ( k > 40 && k <50){
            v.setBackgroundDrawable(getResources().getDrawable(R.drawable.yellow));
        }else {
            v.setBackgroundDrawable(getResources().getDrawable(R.drawable.red));
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcl_tag = (TextView)view.findViewById(R.id.rcl_activity_tag);
        rcl_tag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG,"--click textview 35");
                Toast.makeText(getActivity(),"历史数据图表展示" ,Toast.LENGTH_SHORT).show();
                startActivity(new Intent().setClass(getActivity(),LineChartActivity.class));

            }
        });
    }
}
