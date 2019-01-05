package com.jideguru.quickblogger.Posts;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.jideguru.quickblogger.Common.HTTPDataHandler;
import com.jideguru.quickblogger.Posts.Adapter.PostAdapter;
import com.jideguru.quickblogger.Posts.Models.PostObject;
import com.jideguru.quickblogger.R;
import com.jideguru.quickblogger.Util.Method;



public class PostFragment extends Fragment {


    RecyclerView recyclerView;
    PostObject postObject;
    SwipeRefreshLayout SwipeLayout;
    String idToken;
    String API_LINK;


    private Method method;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PostFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PostFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PostFragment newInstance(String param1, String param2) {
        PostFragment fragment = new PostFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String blogId = getArguments().getString("blogId");
        View view= inflater.inflate(R.layout.fragment_post, container, false);


        SwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        SwipeLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        loadFeed();
                        SwipeLayout.setRefreshing(false);
                    }
                }
        );
        method = new Method(getActivity());
        idToken = method.pref.getString(method.accessToken, null);

        API_LINK = "https://www.googleapis.com/blogger/v3/blogs/"+blogId+"/posts?access_token="+idToken+"&maxResults=100";

        Log.i("URILINK", API_LINK);
        recyclerView= (RecyclerView) view.findViewById(R.id.posts_contain);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        Log.i("URL", API_LINK);
        loadFeed();
        return view;
    }

    private void loadFeed() {
        AsyncTask<String, String, String> loadFeedAsync = new AsyncTask<String, String, String>() {

            ProgressDialog mDialog = new ProgressDialog(getActivity());

            @Override
            protected void onPreExecute() {
                mDialog.setMessage("Loading, Please Wait...");
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

                mDialog.dismiss();
                postObject = new Gson().fromJson(s,PostObject.class);
                PostAdapter adapter = new PostAdapter(postObject, getContext());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }
        };

        String url_get_data = API_LINK;
        loadFeedAsync.execute(url_get_data);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
