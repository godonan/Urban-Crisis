package com.example.godonan.drawertest;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MainFragment fragment = new MainFragment();
        fragmentTransaction.add(R.id.content_frame, fragment);
        fragmentTransaction.commit();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionbar.setSubtitle("Main");
        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // have item get persistent highlighting
                        int id = menuItem.getItemId();
                        mDrawerLayout.closeDrawers();
                        Fragment newFragment = new UniversalFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("ID", Integer.toString(id));
                        newFragment.setArguments(bundle);
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.content_frame, newFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        return true;
                    }
                }
        );
    }


    @SuppressWarnings("deprecation")
    public static Spanned fromTheHtml(String html) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(html);
        }
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void changeBar(String name) {
        ActionBar actionbar = getSupportActionBar();
        actionbar.setSubtitle(name);
    }

    public static class UniversalFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            Bundle bundle = getArguments();
            if (bundle != null) {
                int id = Integer.parseInt(bundle.getString("ID"));
                switch (id) {
                    case R.id.introductionID:
                        return inflater.inflate(R.layout.introduction, container, false);
                    case R.id.referencesID:
                        return inflater.inflate(R.layout.references, container, false);
                    case R.id.activeID:
                        return inflater.inflate(R.layout.active, container, false);
                    case R.id.diseaseID:
                        return inflater.inflate(R.layout.disease, container, false);
                    case R.id.escapingID:
                        return inflater.inflate(R.layout.escaping, container, false);
                    case R.id.explosiveID:
                        return inflater.inflate(R.layout.explosive, container, false);
                    case R.id.medicalID:
                        return inflater.inflate(R.layout.medical, container, false);
                    case R.id.minimizingID:
                        return inflater.inflate(R.layout.minimizing, container, false);
                    case R.id.nbc1ID:
                        return inflater.inflate(R.layout.nbc1, container, false);
                    case R.id.nbc2ID:
                        return inflater.inflate(R.layout.nbc2, container, false);
                    case R.id.transportationID:
                        return inflater.inflate(R.layout.transportation, container, false);
                    case R.id.violentID:
                        return inflater.inflate(R.layout.violent, container, false);
                    case R.id.avoidingID:
                        return inflater.inflate(R.layout.avoiding, container, false);
                    case R.id.barricadingID:
                        return inflater.inflate(R.layout.barricading, container, false);
                    case R.id.circumventID:
                        return inflater.inflate(R.layout.circumvent, container, false);
                    case R.id.basicID:
                        return inflater.inflate(R.layout.basic, container, false);
                    case R.id.disarmingID:
                        return inflater.inflate(R.layout.disarming, container, false);
                    case R.id.firefightingID:
                        return inflater.inflate(R.layout.firefighting, container, false);
                    case R.id.improvisedID:
                        return inflater.inflate(R.layout.improvised, container, false);
                    case R.id.restrainingID:
                        return inflater.inflate(R.layout.restraining, container, false);
                    case R.id.codesID:
                        return inflater.inflate(R.layout.codes, container, false);
                    case R.id.signalsID:
                        return inflater.inflate(R.layout.signals, container, false);
                    case R.id.telecommunicationsID:
                        return inflater.inflate(R.layout.telecommunications, container, false);
                }
                return inflater.inflate(R.layout.introduction, container, false);
            }
            return inflater.inflate(R.layout.introduction, container, false);
        }
    }


    public static class MainFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.main_fragment, container, false);
        }
    }
}