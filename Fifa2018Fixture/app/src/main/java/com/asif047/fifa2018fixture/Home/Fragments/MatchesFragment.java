package com.asif047.fifa2018fixture.Home.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asif047.fifa2018fixture.AlertBox.ShowAlert;
import com.asif047.fifa2018fixture.BaseUrl;
import com.asif047.fifa2018fixture.Home.Adapters.RecyclerAdapterMatch;
import com.asif047.fifa2018fixture.Home.ApiCall.ApiCallMatch;
import com.asif047.fifa2018fixture.Home.Model.Match;
import com.asif047.fifa2018fixture.R;
import com.asif047.fifa2018fixture.Utils.MyNetworkCheck;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import okhttp3.OkHttpClient;


public class MatchesFragment extends Fragment {

    private SweetAlertDialog pDialog;
    private String path, response;
    private OkHttpClient client;

    private Match[] matches;
    private ApiCallMatch apiCallMatch;
    private List<Match> data;

    private RecyclerView recyclerView;
    private RecyclerAdapterMatch recyclerAdapterMatch;
    private RecyclerView.LayoutManager layoutManager;

    private MyNetworkCheck myNetworkCheck;
    private ShowAlert showAlert;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_matches, container, false);

        apiCallMatch = new ApiCallMatch();
        data = new ArrayList<>();

        pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.PROGRESS_TYPE);
        myNetworkCheck = new MyNetworkCheck();
        showAlert = new ShowAlert(getContext());

        recyclerView = view.findViewById(R.id.recyclerview_match);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        if(!myNetworkCheck.isConnected(getContext())) {
            showAlert.showWarningNetHomeActivity();
        } else {

            try{
                path = BaseUrl.BASE_URL_APP + "match/displayAll.php";
                new GetDataFromServer().execute();
            } catch (Exception e) {

            }

        }

        return view;

    }


    private class GetDataFromServer extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            try {
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#26A65B"));
                pDialog.setTitleText("Loading");
                pDialog.setCancelable(false);
                pDialog.show();
            } catch (Exception e) {

            }
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if(pDialog.isShowing()) {
                pDialog.dismiss();
            }

            recyclerAdapterMatch = new RecyclerAdapterMatch(getContext(), data);
            recyclerView.setAdapter(recyclerAdapterMatch);

        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                client = new OkHttpClient();
                response = apiCallMatch.GET(client, path);

                Log.e("###MATCHES: ", response);
                Gson gson = new Gson();
                Type type = new TypeToken<Collection<Match>>() {

                }.getType();


                Collection<Match> enums = gson.fromJson(response, type);
                matches = enums.toArray(new Match[enums.size()]);

                if(data.isEmpty()) {
                    for(int i = 0; i<enums.size(); i++) {
                        data.add(matches[i]);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }


}
