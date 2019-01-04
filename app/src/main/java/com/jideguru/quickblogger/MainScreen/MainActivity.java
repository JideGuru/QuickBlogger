package com.jideguru.quickblogger.MainScreen;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.jideguru.quickblogger.Comments.CommentsFragment;
import com.jideguru.quickblogger.Posts.PostFragment;
import com.jideguru.quickblogger.R;
import com.jideguru.quickblogger.Blogs.BlogActivity;
import com.jideguru.quickblogger.Pages.PagesFragment;
import com.jideguru.quickblogger.Stats.StatsFragment;

public class MainActivity extends AppCompatActivity implements
        PostFragment.OnFragmentInteractionListener,
        CommentsFragment.OnFragmentInteractionListener,
        PagesFragment.OnFragmentInteractionListener,
        StatsFragment.OnFragmentInteractionListener,

    NavigationView.OnNavigationItemSelectedListener {


    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_blogs:
                    Intent myIntent = new Intent(MainActivity.this, BlogActivity.class);
                    MainActivity.this.startActivity(myIntent);
                    return true;
                case R.id.nav_posts:
                    PostFragment fragment1 = new PostFragment();
                    FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction1.replace(R.id.drawer_frame, fragment1, "postsFragment");
                    fragmentTransaction1.commit();
                    return true;
                case R.id.nav_stats:
                    StatsFragment fragment2 = new StatsFragment();
                    FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction2.replace(R.id.drawer_frame, fragment2, "StatsFragment");
                    fragmentTransaction2.commit();
                    return true;
                case R.id.nav_comments:
                    CommentsFragment fragment3 = new CommentsFragment();
                    FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction3.replace(R.id.drawer_frame, fragment3, "CommentsFragment");
                    fragmentTransaction3.commit();
                    return true;
                case R.id.nav_pages:
                    PagesFragment fragment4 = new PagesFragment();
                    FragmentTransaction fragmentTransaction4 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction4.replace(R.id.drawer_frame, fragment4, "PagesFragment");
                    fragmentTransaction4.commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PostFragment fragment1 = new PostFragment();
        FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
        fragmentTransaction1.replace(R.id.drawer_frame, fragment1, "postsFragment");
        fragmentTransaction1.commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
