package com.b3to.newcash.Activities.AccountOverview;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.b3to.newcash.QueryModels.Q_Account;
import com.b3to.newcash.R;
import com.b3to.newcash.Tools.Widgets;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ActivityAccountOverview extends AppCompatActivity {

    TabLayout tablayout;
    ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_overview);
        initialize();
        initTabs();
    }

    private void initialize() {

        // set init views
        Toolbar toolbar = findViewById(R.id.toolbar);

        AppBarLayout appBar = findViewById(R.id.appbar);
        ImageView img = findViewById(R.id.account_image);
        tablayout = findViewById(R.id.tab_layout);
        viewpager = findViewById(R.id.view_pager);
        // get data
        Q_Account model = new Q_Account();
        Bundle bundle = getIntent().getExtras();
        model.setName(bundle.getString("account_name"));
        model.setType_name(bundle.getString("account_type"));
        model.setImg(bundle.getByteArray("account_image"));
        model.setId(bundle.getLong("account_id"));

        toolbar.setTitle(model.getName());
        toolbar.setSubtitle(model.getType_name());
        // getWindow().setStatusBarColor(Color.parseColor(bundle.getString("color_code")));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // set account image
        if (model.getImg() == null || model.getImg().length == 0)
            Widgets.setImageTextThump(img, model.getName());
        else Glide.with(this).load(model.getImg()).circleCrop().into(img);
    }
    private void initTabs() {
        Bundle bundle = getIntent().getExtras();
        long account_id = bundle.getLong("account_id",0);

        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);

        FragmentAccountTotals fTotals = new FragmentAccountTotals(account_id);
        FragmentAccountTransactions fTransactions = new FragmentAccountTransactions(account_id);

        pagerAdapter.addFragment(fTotals, getResources().getString(R.string.summery));
        pagerAdapter.addFragment(fTransactions, getResources().getString(R.string.transactions));

        viewpager.setAdapter(pagerAdapter);
        tablayout.setupWithViewPager(viewpager);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> fragmentsTitles = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            fragmentsTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentsTitles.get(position);
        }


    }
}