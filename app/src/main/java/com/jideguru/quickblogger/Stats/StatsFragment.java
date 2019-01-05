package com.jideguru.quickblogger.Stats;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jideguru.quickblogger.R;
import com.jideguru.quickblogger.Util.Method;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StatsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StatsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatsFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    String idToken;
    String API_LINK;
    String API_LINK1;
    String API_LINK2;
    private Method method;
    TextView today, month, alltime;

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
        month = view.findViewById(R.id.this_month_num);
        alltime = view.findViewById(R.id.all_time_num);


        getToday();
        return view;
    }

    public void getToday(){

        OkHttpClient httpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url(API_LINK1)
                .build();

        Response response = null;

        try {
            response = httpClient.newCall(request).execute();
            String res =  response.body().string();

            Log.i("URILINK", res);
        } catch (Exception e) {
            Log.e("URILINK", "error in getting response get request okhttp");
        }


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
