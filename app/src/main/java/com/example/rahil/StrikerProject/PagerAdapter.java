package com.example.rahil.StrikerProject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RAHIL on 10/02/2018.
 */

public class PagerAdapter  extends FragmentPagerAdapter {

    List<Fragment>  mFragmentsList = new ArrayList<>();
    List<String>  mFragmentsTitles = new ArrayList<>();

    public  PagerAdapter(FragmentManager fm )
    {
        super(fm);

    }
    public void addFragment( Fragment newFragment , String fragmentName)
    {
        mFragmentsList.add(newFragment);
        mFragmentsTitles.add(fragmentName);
    }

    @Override
    public Fragment getItem(int position) {
        Log.e( "FragmentStaterAdapter" , "now returning");
        return  mFragmentsList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentsTitles.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentsList.size();
    }
}
