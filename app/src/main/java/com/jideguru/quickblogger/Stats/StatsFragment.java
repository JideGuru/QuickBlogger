package com.jideguru.quickblogger.Stats;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jideguru.quickblogger.R;
import com.jideguru.quickblogger.Util.Method;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import es.dmoral.toasty.Toasty;


public class StatsFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    String idToken;
    String API_LINK;
    String API_LINK1;
    String API_LINK2;
    private Method method;
    TextView today,week, month, alltime;

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public StatsFragment() {
        // Required empty public constructor
    }

    public static StatsFragment newInstance(String param1, String param2) {
        StatsFragment fragment = new StatsFragment();
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
        View view= inflater.inflate(R.layout.fragment_stats, container, false);

        method = new Method(getActivity());
        idToken = method.pref.getString(method.accessToken, null);

        API_LINK = "https://www.googleapis.com/blogger/v3/blogs/"+blogId+"/pageviews?access_token="+idToken+"&range=7DAYS";
        API_LINK1 = "https://www.googleapis.com/blogger/v3/blogs/"+blogId+"/pageviews?access_token="+idToken+"&range=30DAYS";
        API_LINK2 = "https://www.googleapis.com/blogger/v3/blogs/"+blogId+"/pageviews?access_token="+idToken+"&range=all";

        today = view.findViewById(R.id.today_num);
        week = view.findViewById(R.id.week_num);
        month = view.findViewById(R.id.this_month_num);
        alltime = view.findViewById(R.id.all_time_num);


        getWeek();
        getMonth();
        getAllTime();
        return view;
    }

//    public void getEm(){
//        final ProgressDialog mDialog = new ProgressDialog(getActivity());
//        mDialog.setMessage("Loading, Please Wait...");
//        mDialog.show();
//        mDialog.setCancelable(false);
//        getWeek();
//        getMonth();
//        getAllTime();
//        mDialog.dismiss();
//
//    }

    public void getWeek(){

        final ProgressDialog mDialog = new ProgressDialog(getActivity());
        mDialog.setMessage("Loading, Please Wait...");
        mDialog.show();
        mDialog.setCancelable(false);

        OkHttpClient httpClient = new OkHttpClient();

//        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://www.googleapis.com/blogger/v3/blogs/"+blogId+"/pageviews?access_token=").newBuilder();
//        urlBuilder.addQueryParameter("access_token", idToken);
//        urlBuilder.addQueryParameter("range", "7DAYS");
//        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(API_LINK)
                .build();

        httpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) {
                    Toasty.error(getContext(), "Something went wrong", Toast.LENGTH_SHORT)
                                    .show();
                } else {

//                    Log.i("Response", String.valueOf(response.body().string()));
//                    String res = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        Log.i("Response", String.valueOf(jsonObject));

                        JSONArray jsonArray = jsonObject.getJSONArray("counts");
                        Log.i("Response", String.valueOf(jsonArray));
                        int length = jsonObject .length();

                        for(int i=0; i<length; i++)
                        {
                            JSONObject jObj = jsonArray.getJSONObject(i);

                            final String count = jObj.getString("count");
                            Log.i("Response", count);

                            getActivity().runOnUiThread(new Runnable() {

                                @Override
                                public void run() {

                                    week.setText(count);
                                    mDialog.dismiss();

                                }
                            });
                        }

                    } catch (Exception e) {
                        Log.i("Response", String.valueOf(e));
                    }

                }
            }

            @Override
            public void onFailure(Request request, IOException e) {

            }

        });


    }


    public void getMonth(){

        final ProgressDialog mDialog = new ProgressDialog(getActivity());
        mDialog.setMessage("Loading, Please Wait...");
        mDialog.show();
        mDialog.setCancelable(false);

        OkHttpClient httpClient = new OkHttpClient();

//        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://www.googleapis.com/blogger/v3/blogs/"+blogId+"/pageviews?access_token=").newBuilder();
//        urlBuilder.addQueryParameter("access_token", idToken);
//        urlBuilder.addQueryParameter("range", "7DAYS");
//        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(API_LINK1)
                .build();

        httpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) {
                    Toasty.error(getContext(), "Something went wrong", Toast.LENGTH_SHORT)
                            .show();
                } else {

//                    Log.i("Response", String.valueOf(response.body().string()));
//                    String res = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        Log.i("Response", String.valueOf(jsonObject));

                        JSONArray jsonArray = jsonObject.getJSONArray("counts");
                        Log.i("Response", String.valueOf(jsonArray));
                        int length = jsonObject .length();

                        for(int i=0; i<length; i++)
                        {
                            JSONObject jObj = jsonArray.getJSONObject(i);

                            final String count = jObj.getString("count");
                            Log.i("Response", count);

                            getActivity().runOnUiThread(new Runnable() {

                                @Override
                                public void run() {

                                    month.setText(count);
                                    mDialog.dismiss();

                                }
                            });
                        }

                    } catch (Exception e) {
                        Log.i("Response", String.valueOf(e));
                    }

                }
            }

            @Override
            public void onFailure(Request request, IOException e) {

            }

        });


    }

    public void getAllTime(){

        final ProgressDialog mDialog = new ProgressDialog(getActivity());
        mDialog.setMessage("Loading, Please Wait...");
        mDialog.show();
        mDialog.setCancelable(false);

        OkHttpClient httpClient = new OkHttpClient();

//        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://www.googleapis.com/blogger/v3/blogs/"+blogId+"/pageviews?access_token=").newBuilder();
//        urlBuilder.addQueryParameter("access_token", idToken);
//        urlBuilder.addQueryParameter("range", "7DAYS");
//        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(API_LINK2)
                .build();

        httpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) {
                    Toasty.error(getContext(), "Something went wrong", Toast.LENGTH_SHORT)
                            .show();
                } else {

//                    Log.i("Response", String.valueOf(response.body().string()));
//                    String res = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        Log.i("Response", String.valueOf(jsonObject));

                        JSONArray jsonArray = jsonObject.getJSONArray("counts");
                        Log.i("Response", String.valueOf(jsonArray));
                        int length = jsonObject .length();

                        for(int i=0; i<length; i++)
                        {
                            JSONObject jObj = jsonArray.getJSONObject(i);

                            final String count = jObj.getString("count");
                            Log.i("Response", count);

                            getActivity().runOnUiThread(new Runnable() {

                                @Override
                                public void run() {

                                    alltime.setText(count);
                                    mDialog.dismiss();

                                }
                            });
                        }

                    } catch (Exception e) {
                        Log.i("Response", String.valueOf(e));
                    }

                }
            }

            @Override
            public void onFailure(Request request, IOException e) {

            }

        });


    }

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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
