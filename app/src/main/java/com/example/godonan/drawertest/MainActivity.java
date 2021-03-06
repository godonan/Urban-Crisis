package com.example.godonan.drawertest;

import android.content.pm.ActivityInfo;
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
import android.widget.RelativeLayout;


public class MainActivity extends AppCompatActivity {
    // Create drawer layout
    private DrawerLayout mDrawerLayout;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Basic creation data
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Lock screen orientation to vertical only
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
        // Prepare the fragment managers and transactions
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        
        MainFragment fragment = new MainFragment(); // create the default main fragment
        fragmentTransaction.add(R.id.content_frame, fragment); // add the main fragment to content_frame (BUG CHECK THIS!)
        fragmentTransaction.commit(); // commit fragment to the screen
        
        // Prepare the toolbars
        Toolbar toolbar = findViewById(R.id.toolbar); // find the toolbar
        setSupportActionBar(toolbar); // set the support action bar

        // Set toolbar height
/**
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) toolbar.getLayoutParams();
        layoutParams.height = 10;
        toolbar.setLayoutParams(layoutParams);
        toolbar.requestLayout();
**/
        // Modify the action bar to my liking
        ActionBar actionbar = getSupportActionBar(); // extract the action bar
        actionbar.setDisplayHomeAsUpEnabled(true); // enable the up button
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);   // set the up to open the navigation bar
        actionbar.setTitle("Urban Crisis Manual"); // set up the title
        actionbar.setSubtitle("Main"); // set up the subtitle

        // set up the navigation view from the layout
        mDrawerLayout = findViewById(R.id.drawer_layout); // pull out the drawer_layout
        NavigationView navigationView = findViewById(R.id.nav_view); // navigation menu extract
        
        // set the listener for each navigation item
        navigationView.setNavigationItemSelectedListener(
            new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    // have item get persistent highlighting
                    int id = menuItem.getItemId();
                    mDrawerLayout.closeDrawers();
                    Fragment newFragment = new UniversalFragment();
                        
                    // alter the actionbar with the menu title
                    ActionBar actionbar = getSupportActionBar();
                    actionbar.setSubtitle(menuItem.getTitle());
                    
                    // bundle towards the navigation view object
                    Bundle bundle = new Bundle();
                    bundle.putString("ID", Integer.toString(id));
                    
                    // create the fragment, set its arguments to the bundle
                    newFragment.setArguments(bundle);
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.content_frame, newFragment);
                    
                    //transaction.addToBackStack(null); // history usage, commented out for now due to memory problems
                    transaction.commit();
                    return true;
                }
            }
        );
    }
    
    // extract html information
    @SuppressWarnings("deprecation")
    public static Spanned fromTheHtml(String html) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(html);
        }
    }
    
    // when the option is selected to 
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    
    public void changeBar(String name) {
       
        ActionBar actionbar = getSupportActionBar(); // get the support action bar
        actionbar.setSubtitle(name); // set the subtitle name
    }
    
   
    public static class UniversalFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            Bundle bundle = getArguments(); // get bundle arguments
            if (bundle != null) { // if the bundle exists
                int id = Integer.parseInt(bundle.getString("ID")); // extract the ID
                switch (id) {
                    case R.id.introductionID:
                        return inflater.inflate(R.layout.v0_introduction, container, false);
                    case R.id.referencesID:
                        return inflater.inflate(R.layout.v0_references, container, false);
                    case R.id.activeID:
                        return inflater.inflate(R.layout.v1_active, container, false);
                    //case R.id.escapingID:
                        //return inflater.inflate(R.layout.v1_escaping, container, false);
                    case R.id.explosiveID:
                        return inflater.inflate(R.layout.v1_explosive, container, false);
                    case R.id.minimizingID:
                        return inflater.inflate(R.layout.v1_minimizing, container, false);
                    case R.id.nbc1ID:
                        return inflater.inflate(R.layout.v1_nbc1, container, false);
                    //case R.id.nbc2ID:
                        //return inflater.inflate(R.layout.v1_nbc2, container, false);
                    case R.id.violentID:
                        return inflater.inflate(R.layout.v1_violent, container, false);
                   // case R.id.avoidingID:
                        //return inflater.inflate(R.layout.v2_avoiding, container, false);
                    case R.id.basicID:
                        return inflater.inflate(R.layout.v3_basic, container, false);
                    //case R.id.disarmingID:
                        //return inflater.inflate(R.layout.v3_disarming, container, false);
                    case R.id.firefightingID:
                        return inflater.inflate(R.layout.v3_firefighting, container, false);
                    //case R.id.codesID:
                        //return inflater.inflate(R.layout.v4_codes, container, false);
                    case R.id.signalsID:
                        return inflater.inflate(R.layout.v4_signals, container, false);
                    case R.id.telecommunicationsID:
                        return inflater.inflate(R.layout.v4_telecommunications, container, false);
                }
                return inflater.inflate(R.layout.v0_introduction, container, false);
            }
            return inflater.inflate(R.layout.v0_introduction, container, false);
        }
    }


    public static class MainFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.v0_introduction, container, false);
        }
    }
}