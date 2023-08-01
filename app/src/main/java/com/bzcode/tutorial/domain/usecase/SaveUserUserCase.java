package com.bzcode.tutorial.domain.usecase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bzcode.tutorial.data.remote.dto.login.User;
import com.bzcode.tutorial.data.repository.LocalDataSource;
import com.bzcode.tutorial.domain.repository.ILocalDataSource;
import com.bzcode.tutorial.domain.utils.AppExecutors;
import com.bzcode.tutorial.domain.utils.utils.api_util.Resource;

import java.util.List;

/**
 * @author Shibin
 * created on 01-08-2023 at 11:26
 */
public class SaveUserUserCase {

    private ILocalDataSource localDataSource;
    private AppExecutors appExecutors;

    public SaveUserUserCase(ILocalDataSource localDataSource, AppExecutors appExecutors) {
        this.localDataSource = localDataSource;
        this.appExecutors = appExecutors;
    }

    public LiveData<Resource<Boolean>> execute(User user){

        MutableLiveData<Resource<Boolean>> statusLiveData = new MutableLiveData<>();
        try {

            localDataSource.saveUser(user);

            statusLiveData.postValue(Resource.success(true));

        }catch (Exception e){
            e.printStackTrace();
            statusLiveData.postValue(Resource.error(e.getMessage(), null));
        }

        return statusLiveData;
    }

}
