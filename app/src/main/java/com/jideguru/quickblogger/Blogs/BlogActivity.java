package com.jideguru.quickblogger.Blogs;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import com.jideguru.quickblogger.Blogs.Adapter.BlogAdapter;
import com.jideguru.quickblogger.Blogs.Models.BlogObject;
import com.jideguru.quickblogger.Common.HTTPDataHandler;
import com.jideguru.quickblogger.R;
import com.jideguru.quickblogger.Util.Method;

public class BlogActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    BlogObject blogObject;
    SwipeRefreshLayout SwipeLayout;


    private String API_LINK = "";
    private Method method;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        Intent intent = getIntent();
//        idToken = intent.getStringExtra("token");

        method = new Method(BlogActivity.this);

        //Get the access token from shared preferences
        String idToken = method.pref.getString(method.accessToken, null);
        API_LINK = "https://www.googleapis.com/blogger/v3/users/self/blogs?access_token="+idToken;
        Log.i("URILINK", API_LINK);
        SwipeLayout = (SwipeRefreshLayout) findViewById(R.id.blog_swiperefresh);
        SwipeLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        loadFeed();
                        SwipeLayout.setRefreshing(false);
                    }
                }
        );


        recyclerView= (RecyclerView) findViewById(R.id.blog_contain);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        loadFeed();
        Log.i("urlink", API_LINK);
    }


    //Function to populate data into the BlogAdapter
    private void loadFeed() {
        @SuppressLint("StaticFieldLeak") AsyncTask<String, String, String> loadFeedAsync = new AsyncTask<String, String, String>() {

            ProgressDialog mDialog = new ProgressDialog(BlogActivity.this);

            @Override
            protected void onPreExecute() {
                mDialog.setMessage("Loading Blogs, Please Wait...");
                mDialog.show();
                mDialog.setCancelable(false);

            }

            @Override
            protected String doInBackground(String... params) {

                String result;
                HTTPDataHandler http = new HTTPDataHandler();
                result = http.GetHTTPData(params[0]);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                boolean failed = false;


                mDialog.dismiss();
                //Type BlogObject = new TypeToken<Collection<BlogObject>>(){}.getType();
                //Collection<BlogObject> blogObject = new Gson().fromJson(s, BlogObject);
                blogObject = new Gson().fromJson(s, BlogObject.class);
                BlogAdapter adapter = new BlogAdapter(blogObject, BlogActivity.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }
        };

        String url_get_data = API_LINK;
        loadFeedAsync.execute(url_get_data);
    }

}
