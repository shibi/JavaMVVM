package com.bzcode.tutorial.domain.utils;

import android.app.Application;
import androidx.room.Room;

import com.bzcode.tutorial.data.local.AppDatabase;
import com.bzcode.tutorial.data.remote.api.ApiGenerator;
import com.bzcode.tutorial.data.remote.api.ApiService;

public class CoreApp extends Application {

    private AppDatabase appDatabase;

    public AppDatabase getLocalDb(){
        if(appDatabase==null){
            appDatabase = Room.databaseBuilder(this, AppDatabase.class, Config.DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return appDatabase;
    }

    public ApiService getWebService(){
        ApiService apiService = ApiGenerator.createApiService(ApiService.class, Config.ENCRYPTED_API_KEY);
        return apiService;
    }

    public AppExecutors getAppExecutors(){
        AppExecutors appExecutors = new AppExecutors();
        return appExecutors;
    }



    @Override
    public void onTerminate() {
        super.onTerminate();

        appDatabase = null;
    }
}
