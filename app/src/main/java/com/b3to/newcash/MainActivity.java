package com.b3to.newcash;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.b3to.newcash.Adapters.ADP_Main_Drawer;
import com.b3to.newcash.Adapters.DrawerItem;
import com.b3to.newcash.Adapters.SimpleItemDrawer;
import com.b3to.newcash.Adapters.SpaceItem;
import com.b3to.newcash.Fragments.FragmentAccountTypes;
import com.b3to.newcash.Fragments.FragmentAccounts;
import com.b3to.newcash.Fragments.FragmentCurrencies;
import com.b3to.newcash.Fragments.FragmentDashboard;
import com.b3to.newcash.Fragments.FragmentSettings;
import com.b3to.newcash.Fragments.FragmentTransactions;
import com.b3to.newcash.Tools.DML;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.yarolegovich.slidingrootnav.SlideGravity;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.Arrays;

import de.raphaelebner.roomdatabasebackup.core.RoomBackup;


public class MainActivity extends AppCompatActivity implements ADP_Main_Drawer.OnItemSelectedListener {
    private static final int POS_CLOSE = 0;

    private static final int POS_ACCOUNT_TYPE = 1;
    private static final int POS_REPORTS = 2;

    private static final int POS_SETTINGS = 4;

    @SuppressLint("StaticFieldLeak")
    public static  CrudToolbar crudToolbar;
    public static final String SECRET_KEY= "gd8b3lal";

    ADP_Main_Drawer adapter;
    BottomNavigationView navigationView;
    private String[] screenTitles;
    private Drawable[] screenIcons;
    @SuppressLint("StaticFieldLeak")
    private static Toolbar toolbar;
    public static View.OnClickListener toolbarNavListener;
    @SuppressLint("StaticFieldLeak")
    public static RoomBackup roomBackup;

    private SlidingRootNav slidingRootNav;

    private static int SPLASH_TIME_OUT = 8000; // Delay of 8 Seconds

    private static MaterialButton btnAdd;
    private static IBtnClick btnAddListener;
    private static AppCompatActivity activity;

    public static AppCompatActivity requireActivity() {
        return activity;
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void changeMode() {
        setTheme(com.google.android.material.R.style.Theme_MaterialComponents_DayNight_DarkActionBar);
    }

    public static Toolbar getToolbar() {
        return toolbar;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        roomBackup = new RoomBackup(this);
        Dialog dialog = new Dialog(this, android.R.style.Theme_Light_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dg_splash);
        dialog.show();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        activity = this;
        initDrawer(savedInstanceState);
       initNavigation();
        new Handler().postDelayed(dialog::dismiss, SPLASH_TIME_OUT);
        initView();
    }

    @Override
    public void setTitle(int titleId) {
        super.setTitle(titleId);
        toolbar.setTitle(titleId);
    }

    private void initView() {
        btnAdd = findViewById(R.id.btn_main_add);
        btnAdd.setOnClickListener(v -> {
            if (btnAddListener != null) btnAddListener.click();
        });
        crudToolbar = new CrudToolbar(this);
    }


    public static void setBtnAddVisible(boolean visible) {
        if (btnAdd != null) {
            btnAdd.setEnabled(visible);
        }
    }

    public static void setOnBtnAddClick(IBtnClick listener) {
        btnAddListener = listener;
        setBtnAddVisible(listener != null);
    }


    private void initDrawer(Bundle saveState) {
        toolbar = findViewById(R.id.toolbar_main);
       // setSupportActionBar(toolbar);
        SlidingRootNavBuilder rootNav = new SlidingRootNavBuilder(this).withDragDistance(180).withRootViewScale(0.75f).withRootViewElevation(25).withMenuOpened(false).withToolbarMenuToggle(toolbar).withContentClickableWhenMenuOpened(false).withSavedState(saveState).withMenuLayout(R.layout.drawer_main_menu).withGravity(SlideGravity.LEFT); // error in arabic direction
        slidingRootNav = rootNav.inject();

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        adapter = new ADP_Main_Drawer(Arrays.asList(
                createItemFor(POS_CLOSE).setIsCloseBtn(true),
                createItemFor(POS_ACCOUNT_TYPE),
                createItemFor(POS_REPORTS),
                new SpaceItem(260),
                createItemFor(POS_SETTINGS)));

        adapter.setListener(this);
        RecyclerView list = findViewById(R.id.drawer_list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);
    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.id_main_drawer_items_titles);
    }

    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.id_main_drawer_items_icons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;
    }

    private DrawerItem createItemFor(int position) {
        return new SimpleItemDrawer(screenIcons[position], screenTitles[position])
                .withIconTint(DML.getAttr(this, com.google.android.material.R.attr.colorPrimary))
                .withTextTint(DML.getAttr(this, com.google.android.material.R.attr.colorPrimary))
                .withSelectedIconTint(DML.getAttr(this,com.google.android.material.R.attr.colorPrimary))
                .withSelectedTextTint(DML.getAttr(this,com.google.android.material.R.attr.colorPrimary))
                .withBackgroundTint(DML.getAttr(this,com.google.android.material.R.attr.backgroundColor))
                .withBackgroundSelectedTint(DML.getAttr(this,com.google.android.material.R.attr.backgroundColor));
    }

    @SuppressLint("NonConstantResourceId")
    void initNavigation() {
        navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setOnItemSelectedListener(item -> {
            this.adapter.setSelected(-1);
            switch (item.getItemId()) {
                case R.id.bottom_item_accounts:
                    return showAccountFrag();
                case R.id.bottom_item_currency:
                    return showCurrenciesFragment();
                case R.id.bottom_item_transaction:
                    return showTransactionsFragment();
                case R.id.bottom_item_dashboard:
                    return showDashboardFragment();
                default:
                    return false;
            }
        });
        navigationView.setSelectedItemId(R.id.bottom_item_dashboard);
    }

    private boolean showAccountFrag() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, FragmentAccounts.class, null).commit();
        setTitle(R.string.accounts);
        return true;
    }

    private boolean showCurrenciesFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, FragmentCurrencies.class, null).commit();
        setTitle(R.string.currencies);
        return true;
    }

    private boolean showTransactionsFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, FragmentTransactions.class, null).commit();
        setTitle(R.string.history);
        return true;
    }

    private boolean showDashboardFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, FragmentDashboard.class, null).commit();
        setTitle(R.string.dashboard);
        return true;
    }

    private boolean showSettingsFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, FragmentSettings.class, null).commit();
        setTitle(R.string.settings);
        return true;
    }

    private boolean showAccountTypesFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, FragmentAccountTypes.class, null).commit();
        setTitle(R.string.account_types);
        return true;
    }

    @Override
    public void OnItemSelected(int position) {

        switch (position) {
            case POS_ACCOUNT_TYPE:
                showAccountTypesFragment();
                break;
            case POS_SETTINGS:
                showSettingsFragment();
                break;
        }
        navigationView.setSelectedItemId(0);
        navigationView.setSelected(false);
        slidingRootNav.closeMenu();

    }

    public interface IBtnClick {
        void click();
    }

    public static class CrudToolbar {
        private Toolbar toolbar;
        private AppCompatActivity activity;
        private TextView txtCount;
        private IClose listener;

        public CrudToolbar(AppCompatActivity activity) {
            this.activity = activity;
            toolbar = activity.findViewById(R.id.toolbar_selection);
            toolbar.getMenu().clear();
            toolbar.inflateMenu(R.menu.menu_crud);
            toolbar.setVisibility(View.GONE);
            toolbar.setNavigationIcon(R.drawable.ic_close);

            txtCount = activity.findViewById(R.id.txt_selected_counter);

            toolbar.setNavigationOnClickListener(v -> {
                toolbar.setVisibility(View.GONE);
                if (listener != null) listener.onClose();
            });
        }

        public void show() {
            toolbar.setVisibility(View.VISIBLE);
        }

        public void hide() {
            toolbar.setVisibility(View.INVISIBLE);
        }

        public void setCount(int count) {
            String str = count + " " + activity.getResources().getString(R.string.items_selected);
        }

        public void setOnClose(IClose listener) {
            this.listener = listener;
        }

        public interface IClose {
            void onClose();
        }
    }

}

