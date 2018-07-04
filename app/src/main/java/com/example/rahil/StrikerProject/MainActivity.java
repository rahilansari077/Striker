package com.example.rahil.StrikerProject;

// color FF1E90FF
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements  Tab1.OnFragmentInteractionListener , Tab2.OnFragmentInteractionListener {

    ViewPager vp;
    PagerAdapter mpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("MainActivity", "ContentViewSet ");

        mpager = new PagerAdapter(getSupportFragmentManager());

        vp = (ViewPager) findViewById(R.id.view_pager);
        setupViewPager(vp);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(vp);
    }

    void setupViewPager(ViewPager vp) {
        mpager.addFragment(new Tab1(), "Now Goals");
        mpager.addFragment(new Tab2(), "Then Goals");
        vp.setAdapter(mpager);
        Log.e("MainActivity", "View Pager has set The adapter");
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finishAffinity();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}