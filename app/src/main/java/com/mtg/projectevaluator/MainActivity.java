package com.mtg.projectevaluator;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /* Database Work starts here  */

        /* Database work ends here     */

        // setting the layout on creation of the activity

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // setting our customized toolbar

        drawer = findViewById(R.id.drawer_layout);

        //Now let's set the listener to the items in the navigation drawer
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle); // adding the listener
        toggle.syncState(); // take care of rotating icon during toggling

        if (savedInstanceState == null) {
            /*   Why the above statement is needed
             * We should know that when we change the orientation of the screen we recreate the
             * activity layout and onCreate will be called again
             * We don't want this to happen
             * So we check if there is any savedInstanceState
             */
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new ImportFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_import);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {    // To check which item is selected
            case R.id.nav_import:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ImportFragment()).commit();
                break; // break is important !!!
            case R.id.nav_view_marks:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ViewMarksFragment()).commit();
                break;
            case R.id.nav_about:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AboutFragment()).commit();
                break;
            case R.id.nav_changePwd:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ChangePasswordFragment()).commit();
                break;
            case R.id.nav_update_marks:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new UpdateMarksFragment()).commit();
                break;
            case R.id.nav_save:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ExportDatabaseFragment()).commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START); // After selection we should close the drawer

        return true;
        // Above true will take care which item is selected
        //   if we return false then although fragment layout will be set
        //   that item will not be checked in the navigation drawer
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START); // GravityCompat.START means close the drawer ont the left side of the screen
        } else {
            super.onBackPressed();
        }
    }
}
