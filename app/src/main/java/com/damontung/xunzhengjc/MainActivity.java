package com.damontung.xunzhengjc;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.PagerTitleStrip;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener{
    private String TAG = "DamonTung";
    public static boolean LoginOrSetting = true; //true 表示显示注册登陆页面，false 表示显示设置界面
    @Override
    public void onFragmentInteraction(Uri uri) {
        //void onFragmentInteraction(Uri uri);
    }

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private PagerTitleStrip mPagerTitleStrip;

    private RCLFragment mRCLFragment;
    private XZSFragment mXZSFragment;
    private Login2Fragment mLoginFragment;
    private SettingFragment mSettingFragment;
    private AboutFragment mAboutFragment;

    private ArrayList<Fragment> mFragmentArrayList;
    private ArrayList<String> mPagerTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewPager();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),mFragmentArrayList);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mPagerTitleStrip = (PagerTitleStrip) findViewById(R.id.paper_title);
        mPagerTitle = new ArrayList<String>();
        mPagerTitle.add("热处理");
        mPagerTitle.add("熏蒸室");
        //mPagerTitle.add("曲线图");
        mPagerTitle.add("登陆/设置");
        mPagerTitle.add("关于");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                startActivity(new Intent().setClass(getApplication(),LineChartActivity.class));
            }
        });
        fab.setVisibility(View.VISIBLE);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
  /*  public static class PlaceholderFragment extends Fragment {
        *//**
         * The fragment argument representing the section number for this
         * fragment.
         *//*
        private static final String ARG_SECTION_NUMBER = "section_number";
        private static final String ARG_SECTION_TITLE = "section_title";

        public PlaceholderFragment() {
        }

        *//**
         * Returns a new instance of this fragment for the given section
         * number.
         *//*
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            //sectionNumber = sectionNumber % 4;
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            String sectionTitle = "";
            switch (sectionNumber) {
                case 0:
                    sectionTitle = "热处理";
                    break;
                case 1:
                    sectionTitle = "熏蒸室";
                    break;
                case 2:
                    sectionTitle = "设置";
                    break;
                case 3:
                    sectionTitle = "其他";
                    break;
            }
            args.putString(ARG_SECTION_TITLE,sectionTitle);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            int section_number = getArguments().getInt(ARG_SECTION_NUMBER);
            *//*switch (section_number){
                case 0:
                    View rootView = inflater.inflate(R.layout.fragment_rcl,container,false);
                    return rootView;
            }*//*
            View rootView = inflater.inflate(R.layout.fragment_rcl,container,false);
            return rootView;
        }
    }
*/
    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> mFragments;
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public SectionsPagerAdapter(FragmentManager fm,ArrayList<Fragment> fragmentArrayList){
            super(fm);
            this.mFragments = fragmentArrayList;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            //return PlaceholderFragment.newInstance(position + 1);

            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mPagerTitle.get(position);
        }
    }
    private void initViewPager(){

        mFragmentArrayList = new ArrayList<Fragment>();

        mRCLFragment = new RCLFragment();
        mXZSFragment = new XZSFragment();
        mLoginFragment = new Login2Fragment();

        mSettingFragment = new SettingFragment();
        mAboutFragment = new AboutFragment();


        mFragmentArrayList.add(mRCLFragment);
        mFragmentArrayList.add(mXZSFragment);
        /*if(LoginOrSetting){
            mFragmentArrayList.add(mLoginFragment);
        }else {
            mFragmentArrayList.add(mSettingFragment);
        }*/
        mFragmentArrayList.add(mSettingFragment);

        mFragmentArrayList.add(mAboutFragment);


    }
    public void refreshViewPager(){
        initViewPager();

    }
}
