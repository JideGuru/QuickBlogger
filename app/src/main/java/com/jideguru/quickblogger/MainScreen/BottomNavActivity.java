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
import com.jideguru.quickblogger.Pages.PagesFragment;
import com.jideguru.quickblogger.Stats.StatsFragment;

public class BottomNavActivity extends AppCompatActivity implements
        PostFragment.OnFragmentInteractionListener,
        CommentsFragment.OnFragmentInteractionListener,
        PagesFragment.OnFragmentInteractionListener,
        StatsFragment.OnFragmentInteractionListener,

    NavigationView.OnNavigationItemSelectedListener {


    private TextView mTextMessage;

    String blog_id, blog_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);

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

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
//                case R.id.nav_blogs:
//                    Intent myIntent = new Intent(BottomNavActivity.this, BlogActivity.class);
//                    BottomNavActivity.this.startActivity(myIntent);
//                    return true;
                case R.id.nav_posts:
                    Bundle bundle = new Bundle();
                    bundle.putString("blogId", blog_id);
                    PostFragment fragment1 = new PostFragment();
                    fragment1.setArguments(bundle);
                    FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction1.replace(R.id.drawer_frame, fragment1, "postsFragment");
                    fragmentTransaction1.commit();
                    return true;
                case R.id.nav_stats:
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("blogId", blog_id);
                    StatsFragment fragment2 = new StatsFragment();
                    fragment2.setArguments(bundle2);
                    FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction2.replace(R.id.drawer_frame, fragment2, "StatsFragment");
                    fragmentTransaction2.commit();
                    return true;
                case R.id.nav_comments:
                    Bundle bundle3 = new Bundle();
                    bundle3.putString("blogId", blog_id);
                    CommentsFragment fragment3 = new CommentsFragment();
                    fragment3.setArguments(bundle3);
                    FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction3.replace(R.id.drawer_frame, fragment3, "CommentsFragment");
                    fragmentTransaction3.commit();
                    return true;
                case R.id.nav_pages:
                    Bundle bundle4 = new Bundle();
                    bundle4.putString("blogId", blog_id);
                    PagesFragment fragment4 = new PagesFragment();
                    fragment4.setArguments(bundle4);
                    FragmentTransaction fragmentTransaction4 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction4.replace(R.id.drawer_frame, fragment4, "PagesFragment");
                    fragmentTransaction4.commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
