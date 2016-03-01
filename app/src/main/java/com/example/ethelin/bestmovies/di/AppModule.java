package com.example.ethelin.bestmovies.di;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;
import java.io.File;

@Module
public class AppModule {

    private Application application;

    public void setApplication(Application application) {
        this.application = application;

    }

    @Provides
    public Context provideContext() {
        return application;
    }

    @Singleton
    @Provides
    public Picasso providePicasso(Context context, OkHttpClient client) {
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttpDownloader(client));
        builder.defaultBitmapConfig(Bitmap.Config.RGB_565);
        return builder.build();
    }

    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient(Context context) {
        OkHttpClient client = new OkHttpClient();
        client.setCache(new Cache(new File(context.getCacheDir().getPath() + "/http_cache/"), 8000000L));
        return client;
    }

    @Singleton
    @Provides
    public ObjectMapper provideObjectMapper() {
        return new ObjectMapper();
    }
}