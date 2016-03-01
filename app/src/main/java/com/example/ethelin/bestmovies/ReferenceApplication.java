package com.example.ethelin.bestmovies;

import android.app.Application;
import com.example.ethelin.bestmovies.di.AppComponent;
import com.example.ethelin.bestmovies.di.AppModule;
import com.example.ethelin.bestmovies.di.DaggerAppComponent;
import timber.log.Timber;

public class ReferenceApplication extends Application {

    private static final AppComponent COMPONENT;
    private static final AppModule APP_MODULE;
    private static ReferenceApplication instance;

    static {
        COMPONENT = DaggerAppComponent.builder()
                                      .appModule(APP_MODULE = new AppModule())
                                      .build();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        APP_MODULE.setApplication(this);

        instance = this;
    }

    public static AppComponent component() {
        return COMPONENT;
    }

    public static ReferenceApplication getInstance() {
        return instance;
    }
}