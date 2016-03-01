package com.example.ethelin.bestmovies.rx;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import org.apache.commons.io.IOUtils;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Func1;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;

public class UrlToJsonNodeFunc implements Func1<String, JsonNode> {

    private ObjectMapper objectMapper;
    private OkHttpClient okHttpClient;

    @Inject
    public UrlToJsonNodeFunc(OkHttpClient okHttpClient) {
        this.objectMapper = new ObjectMapper();
        this.okHttpClient = okHttpClient;
    }

//    @Inject
//    public UrlToJsonNodeFunc(ObjectMapper objectMapper, OkHttpClient okHttpClient) {
//        this.objectMapper = objectMapper;
//        this.okHttpClient = okHttpClient;
//    }

    @Override
    public JsonNode call(String s) {
        Call call = okHttpClient.newCall(new Request.Builder().url(s).build());
        InputStream inputStream = null;
        try {
            return objectMapper.readTree(inputStream = call.execute().body().byteStream());
        } catch (IOException e) {
            throw OnErrorThrowable.from(e);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }
}