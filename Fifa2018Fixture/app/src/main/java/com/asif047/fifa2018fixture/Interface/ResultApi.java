package com.asif047.fifa2018fixture.Interface;


import java.io.IOException;

import okhttp3.OkHttpClient;

public interface ResultApi {

    public String GET(OkHttpClient client, String url) throws IOException;

}
