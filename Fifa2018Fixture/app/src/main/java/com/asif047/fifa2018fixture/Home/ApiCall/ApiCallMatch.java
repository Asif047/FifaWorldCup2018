package com.asif047.fifa2018fixture.Home.ApiCall;

import com.asif047.fifa2018fixture.Interface.MatchApi;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by admin on 6/4/2018.
 */

public class ApiCallMatch implements MatchApi {
    @Override
    public String GET(OkHttpClient client, String url) throws IOException {
        Request request = new Request.Builder()
                                .url(url)
                                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
