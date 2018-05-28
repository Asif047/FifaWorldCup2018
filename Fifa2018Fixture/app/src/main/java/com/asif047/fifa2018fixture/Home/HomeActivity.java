package com.asif047.fifa2018fixture.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.asif047.fifa2018fixture.Home.Fragments.GroupsFragment;
import com.asif047.fifa2018fixture.Home.Fragments.MatchesFragment;
import com.asif047.fifa2018fixture.Home.Fragments.ResultsFragment;
import com.asif047.fifa2018fixture.MainActivity;
import com.asif047.fifa2018fixture.R;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG="HomeActivity";
    private SectionPageAdapter mSectionPageadapter;
    private ViewPager mViewPager;


    private DrawerLayout dl;
    private ActionBarDrawerToggle toggle;
    NavigationView nvDrawer;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().hide();
        toolbar = findViewById(R.id.toolbar_home);
        toolbar.setTitle("Home");


        Log.d(TAG,"on create:starting.");

        mSectionPageadapter=new SectionPageAdapter(getSupportFragmentManager());

        mViewPager= (ViewPager) findViewById(R.id.container_home);
        setupViewPager(mViewPager);

        TabLayout tabLayout= (TabLayout) findViewById(R.id.tabs_home);
        tabLayout.setupWithViewPager(mViewPager);

        //navigation drawer works starts
        dl = findViewById(R.id.dl_home);
        nvDrawer = findViewById(R.id.nv_home);
        toggle = new ActionBarDrawerToggle(this, dl, toolbar, R.string.open, R.string.close);
        dl.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setHomeButtonEnabled(true);
        setupDrawerContent(nvDrawer);
        //navigation drawer work ends

    }


    private void setupViewPager(ViewPager viewPager)
    {
        SectionPageAdapter sectionPageadapter=new SectionPageAdapter(getSupportFragmentManager());
        sectionPageadapter.addFragment(new GroupsFragment(),"Groups");
        sectionPageadapter.addFragment(new MatchesFragment(),"Matches");
        sectionPageadapter.addFragment(new ResultsFragment(),"Results");

        viewPager.setAdapter(sectionPageadapter);

    }


    public void selectItemDrawer(MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.home:
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }

        try {
//            myFragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        menuItem.setChecked(true);
        //setTitle(menuItem.getTitle());
        dl.closeDrawers();

    }


    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectItemDrawer(item);
                return true;
            }
        });
    }



}
