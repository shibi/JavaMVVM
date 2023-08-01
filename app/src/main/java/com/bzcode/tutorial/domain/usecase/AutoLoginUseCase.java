package com.bzcode.tutorial.domain.usecase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bzcode.tutorial.data.local.AppDatabase;
import com.bzcode.tutorial.data.remote.dto.login.User;
import com.bzcode.tutorial.data.repository.LocalDataSource;
import com.bzcode.tutorial.domain.repository.ILocalDataSource;
import com.bzcode.tutorial.domain.utils.AppExecutors;
import com.bzcode.tutorial.domain.utils.utils.api_util.Resource;

import java.util.List;

import retrofit2.http.Body;

/**
 * @author Shibin
 * created on 01-08-2023 at 11:26
 */
public class AutoLoginUseCase {

    private ILocalDataSource localDataSource;
    private AppExecutors appExecutors;

    public AutoLoginUseCase(ILocalDataSource localDataSource, AppExecutors appExecutors) {
        this.localDataSource = localDataSource;
        this.appExecutors = appExecutors;
    }

    public LiveData<Resource<User>> execute(){

        MutableLiveData<Resource<User>> statusLiveData = new MutableLiveData<>();
        try {

            //retrieve saved users from local db
            List<User> users = localDataSource.getUsers();

            //check user list is valid
            if(users!=null && users.size()> 0){
                statusLiveData.postValue(Resource.success(users.get(0)));
            }else {
                statusLiveData.postValue(Resource.error("Empty data", null));
            }


        }catch (Exception e){
            e.printStackTrace();
            statusLiveData.postValue(Resource.error(e.getMessage(), null));
        }

        return statusLiveData;
    }

}
