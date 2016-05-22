package com.damontung.xunzhengjc;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link XZSFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link XZSFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class XZSFragment extends Fragment {
    //熏蒸室
    private TextView xzs_qtnd;//气体浓度
    private TextView xzs_wd;//温度
    private TextView xzs_sd;//湿度

    private TextView xzs_qtnd_text;
    private TextView xzs_wd_text;
    private TextView xzs_sd_text;

    private TextView xzs_qtnd_text_tag;
    private TextView xzs_wd_text_tag;
    private TextView xzs_sd_text_tag;

    private TextView xzs_qtnd_tag;
    private TextView xzs_wd_tag;
    private TextView xzs_sd_tag;

    private TextView set_time;
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public XZSFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment XZSFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static XZSFragment newInstance(String param1, String param2) {
        XZSFragment fragment = new XZSFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_xzs,container,false);
        initView(view);
        return view;

    }
    private void initView(View view) {
        //氣體濃度
        LinearLayout layout8 = (LinearLayout)view.findViewById(R.id.xzs_layout_qtnd).findViewById(R.id.item_tag);
        xzs_qtnd_text = (TextView)layout8.findViewById(R.id.item_name);
        xzs_qtnd = (TextView)layout8.findViewById(R.id.data);
        xzs_qtnd_text_tag = (TextView)layout8.findViewById(R.id.item_tag_1);
        xzs_qtnd_tag = (TextView)layout8.findViewById(R.id.item_color_tag);

        xzs_qtnd_text.setText(R.string.xzs_qtnd);
        xzs_qtnd_text_tag.setText(R.string.qtnd);
        xzs_qtnd.setText("89");
        int k = Integer.parseInt(xzs_qtnd.getText().toString());
        setBkColor(xzs_qtnd_tag,k);

        //溫度
        LinearLayout layout9 = (LinearLayout)view.findViewById(R.id.xzs_layout_wd).findViewById(R.id.item_tag);
        xzs_wd = (TextView) layout9.findViewById(R.id.data);
        xzs_wd_text = (TextView) layout9.findViewById(R.id.item_name);
        xzs_wd_text_tag = (TextView) layout9.findViewById(R.id.item_tag_1);
        xzs_wd_tag = (TextView) layout9.findViewById(R.id.item_color_tag);

        xzs_wd_text.setText(R.string.xzs_wd);
        xzs_wd.setText("34");
        xzs_wd_text_tag.setText(R.string.wd);
        k = Integer.parseInt(xzs_wd.getText().toString());
        setBkColor(xzs_wd_tag,k);

        //濕度
        LinearLayout layout10 = (LinearLayout)view.findViewById(R.id.xzs_layout_sd).findViewById(R.id.item_tag);
        xzs_sd = (TextView) layout10.findViewById(R.id.data);
        xzs_sd_text = (TextView)layout10.findViewById(R.id.item_name);
        xzs_sd_text_tag = (TextView) layout10.findViewById(R.id.item_tag_1);
        xzs_sd_tag = (TextView) layout10.findViewById(R.id.item_color_tag);
        xzs_sd.setText("44");
        xzs_sd_text.setText(R.string.xzs_sd);
        xzs_sd_text_tag.setText(R.string.sd);
        k = Integer.parseInt(xzs_sd.getText().toString());
        setBkColor(xzs_sd_tag,k);

        TextView jc_time = (TextView) view.findViewById(R.id.jc_time);
        setDate(jc_time);
    }

    private void setDate(View v){
        final Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        set_time = (TextView)v;
        updateDateDisplay();
    }

    private void updateDateDisplay(){
        set_time.setText(new StringBuilder().append(mYear).append("-")
                .append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1)).append("-")
                .append((mDay < 10) ? "0" + mDay : mDay));
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    /*public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
    private void setBkColor(View v, int k){
        if( k <= 40 ){
            v.setBackgroundDrawable(getResources().getDrawable(R.drawable.green));
        }else if ( k > 40 && k <50){
            v.setBackgroundDrawable(getResources().getDrawable(R.drawable.yellow));
        }else {
            v.setBackgroundDrawable(getResources().getDrawable(R.drawable.red));
        }
    }

}
