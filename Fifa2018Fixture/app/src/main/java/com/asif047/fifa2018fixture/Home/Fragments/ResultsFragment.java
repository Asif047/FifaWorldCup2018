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
import com.asif047.fifa2018fixture.Home.Adapters.RecyclerAdapterGroup;
import com.asif047.fifa2018fixture.Home.Adapters.RecyclerAdapterResult;
import com.asif047.fifa2018fixture.Home.ApiCall.ApiCallGroup;
import com.asif047.fifa2018fixture.Home.ApiCall.ApiCallResult;
import com.asif047.fifa2018fixture.Home.Model.Result;
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


public class ResultsFragment extends Fragment {


    private SweetAlertDialog pDialog;
    private String path, response;
    private OkHttpClient client;

    private Result[] results;
    private ApiCallResult apiCallResult;
    private List<Result> data;

    private RecyclerView recyclerView;
    private RecyclerAdapterResult recyclerAdapterResult;
    private RecyclerView.LayoutManager layoutManager;

    private MyNetworkCheck myNetworkCheck;
    private ShowAlert showAlert;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_results, container, false);

        apiCallResult = new ApiCallResult();
        data = new ArrayList<>();

        pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.PROGRESS_TYPE);
        myNetworkCheck = new MyNetworkCheck();
        showAlert = new ShowAlert(getContext());

        recyclerView = view.findViewById(R.id.recyclerview_result);
        layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        if(!myNetworkCheck.isConnected(getContext())) {
            showAlert.showWarningNetHomeActivity();
        } else {
            try {
                path = BaseUrl.BASE_URL_APP+"score/displayAll.php";
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

            recyclerAdapterResult = new RecyclerAdapterResult(getContext(), data);
            recyclerView.setAdapter(recyclerAdapterResult);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try{
                client = new OkHttpClient();
                response = apiCallResult.GET(client, path);
                Log.e("##RESULT: ", response);

                Gson gson = new Gson();
                Type type = new TypeToken<Collection<Result>>() {

                }.getType();

                Collection<Result> enums = gson.fromJson(response, type);
                results = enums.toArray(new Result[enums.size()]);

                if(data.isEmpty()) {
                    for (int i = 0; i< enums.size(); i++) {
                        data.add(results[i]);
                    }
                }

            } catch (IOException e){
                e.printStackTrace();
            }

            return null;

        }
    }


}
