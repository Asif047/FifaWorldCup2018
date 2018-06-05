package com.asif047.fifa2018fixture.Interface;

import java.io.IOException;

import okhttp3.OkHttpClient;

/**
 * Created by admin on 6/4/2018.
 */

public interface MatchApi {

    public String GET(OkHttpClient client, String url) throws IOException;

}
