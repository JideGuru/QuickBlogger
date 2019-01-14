package com.jideguru.quickblogger.MainScreen;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.jideguru.quickblogger.Comments.CommentsFragment;
import com.jideguru.quickblogger.Pages.PagesFragment;
import com.jideguru.quickblogger.Posts.PostFragment;
import com.jideguru.quickblogger.R;
import com.jideguru.quickblogger.Stats.StatsFragment;
import com.jideguru.quickblogger.Util.Method;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.vansuita.materialabout.views.CircleImageView;

public class MainActivity extends AppCompatActivity implements
        PostFragment.OnFragmentInteractionListener,
        CommentsFragment.OnFragmentInteractionListener,
        PagesFragment.OnFragmentInteractionListener,
        StatsFragment.OnFragmentInteractionListener,

        NavigationView.OnNavigationItemSelectedListener {

    String blog_id, blog_name;
    public TextView navName, navEmail;
    private NavigationView navigationView;
    public ImageView navDp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        Method method = new Method(MainActivity.this);
        String userName = method.pref.getString(method.userName, null);
        String userEmail = method.pref.getString(method.userEmail, null);
        String userPhoto = method.pref.getString(method.userPhoto, null);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();




        Intent intent = getIntent();
        blog_id = intent.getStringExtra("blogId");
        blog_name = intent.getStringExtra("blogName");


        setTitle(blog_name);
//        Toast.makeText(BottomNavActivity.this,blog_id,Toast.LENGTH_SHORT).show();


        Bundle bundle = new Bundle();
        bundle.putString("blogId", blog_id);
        PostFragment fragment1 = new PostFragment();
        fragment1.setArguments(bundle);
        FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
        fragmentTransaction1.replace(R.id.drawer_frame, fragment1, "postsFragment");
        fragmentTransaction1.commit();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        navName = (TextView) headerView.findViewById(R.id.name);
        if (method.pref.getBoolean(method.pref_login, false)){
            navName.setText(userName);
        }else {
            navName.setText("Guest");
        }


        navEmail = (TextView) headerView.findViewById(R.id.email);
        if (method.pref.getBoolean(method.pref_login, false)) {
            navEmail.setText(userEmail);
        }else {
            navEmail.setText("atorneldevs@gmail.com");
        }

        navDp = (ImageView) headerView.findViewById(R.id.image);
        try{
            Picasso.with(MainActivity.this)
                    .load(userPhoto)
                    .networkPolicy(NetworkPolicy.NO_CACHE)
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(navDp);
        }catch (Exception e){
            Log.i("DP error", String.valueOf(e));
        }
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_posts) {
            Bundle bundle = new Bundle();
            bundle.putString("blogId", blog_id);
            PostFragment fragment1 = new PostFragment();
            fragment1.setArguments(bundle);
            FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
            fragmentTransaction1.replace(R.id.drawer_frame, fragment1, "postsFragment");
            fragmentTransaction1.commit();
        } else if (id == R.id.nav_stats) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("blogId", blog_id);
            StatsFragment fragment2 = new StatsFragment();
            fragment2.setArguments(bundle2);
            FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
            fragmentTransaction2.replace(R.id.drawer_frame, fragment2, "StatsFragment");
            fragmentTransaction2.commit();
        } else if (id == R.id.nav_comments) {
            Bundle bundle3 = new Bundle();
            bundle3.putString("blogId", blog_id);
            CommentsFragment fragment3 = new CommentsFragment();
            fragment3.setArguments(bundle3);
            FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
            fragmentTransaction3.replace(R.id.drawer_frame, fragment3, "CommentsFragment");
            fragmentTransaction3.commit();
        } else if (id == R.id.nav_pages) {

            Bundle bundle4 = new Bundle();
            bundle4.putString("blogId", blog_id);
            PagesFragment fragment4 = new PagesFragment();
            fragment4.setArguments(bundle4);
            FragmentTransaction fragmentTransaction4 = getSupportFragmentManager().beginTransaction();
            fragmentTransaction4.replace(R.id.drawer_frame, fragment4, "PagesFragment");
            fragmentTransaction4.commit();

        } else if (id == R.id.nav_signout) {
            finishAffinity();

        } else if (id == R.id.nav_share) {
            shareApp();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void shareApp() {

        try {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
            String sAux = "\n" + "Manage your blogs at your finger tips" + "\n\n";
            sAux = sAux + "https://play.google.com/store/apps/details?id=" + getApplication().getPackageName();
            i.putExtra(Intent.EXTRA_TEXT, sAux);
            startActivity(Intent.createChooser(i, "Choose an Application"));
        } catch (Exception e) {
            //e.toString();
        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
