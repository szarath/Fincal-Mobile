package com.example.szarathkumar.fincal;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.FragmentManager;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static FragmentManager fm;

    public static boolean isLogcheck() {
        return logcheck;
    }

    public static void setLogcheck(boolean logcheck) {
        MainActivity.logcheck = logcheck;
    }

    public static boolean logcheck;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fm = getSupportFragmentManager();
        Class fragclass = null;
        fragclass = Eventlist.class;
        fragmentchange(fragclass,fm);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

  /*  @Override
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
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        android.support.v4.app.Fragment fragment = null;
        Class fragclass = null;
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_login) {

        } else if (id == R.id.nav_register) {
            fragclass = Register.class;
            fragmentchange(fragclass,fm);
        }
          else if (id == R.id.nav_event) {

            fragclass = Eventlist.class;
            fragmentchange(fragclass,fm);
            // Handle the camera action
        } else if (id == R.id.nav_task) {
            fragclass = Task.class;
            fragmentchange(fragclass,fm);

        } else if (id == R.id.nav_schedule) {
            fragclass = Schedule.class;
            fragmentchange(fragclass,fm);

        } else if (id == R.id.nav_chat) {
            fragclass = Googlechat.class;
            fragmentchange(fragclass,fm);

        } else if (id == R.id.nav_account) {
            fragclass = Account.class;
            fragmentchange(fragclass,fm);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static void fragmentchange(Class frag,FragmentManager fm)
    {
        android.support.v4.app.Fragment fragment = null;


        try{
            fragment = (android.support.v4.app.Fragment) frag.newInstance();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        fm.beginTransaction().replace(R.id.flContent,fragment).addToBackStack(fragment.getTag()).commit();



    }
}
