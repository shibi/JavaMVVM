package com.bzcode.tutorial.domain.usecase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bzcode.tutorial.data.local.AppDatabase;
import com.bzcode.tutorial.data.remote.api.ApiService;
import com.bzcode.tutorial.data.remote.dto.login.User;
import com.bzcode.tutorial.domain.repository.ILocalDataSource;
import com.bzcode.tutorial.domain.repository.IRemoteDataSource;
import com.bzcode.tutorial.domain.utils.utils.api_util.Resource;

/**
 * @author Shibin
 * created on 01-08-2023 at 11:07
 */
public class LoginUseCase {

    private IRemoteDataSource remoteDataSource;

    public LoginUseCase(IRemoteDataSource remoteDataSource){
        this.remoteDataSource = remoteDataSource;
    }

    public LiveData<Resource<User>> execute(String email, String phone){
        MutableLiveData<Resource<User>> statusLiveData = new MutableLiveData<>();
        try{

            if(email.isEmpty()){
                statusLiveData.postValue(Resource.error("Please enter email",null));
                return statusLiveData;
            }

            if(phone.isEmpty()){
                statusLiveData.postValue(Resource.error("Please enter phone",null));
                return statusLiveData;
            }

            if(phone.length() != 10){
                statusLiveData.postValue(Resource.error("Verify your phone number",null));
                return statusLiveData;
            }

            return remoteDataSource.requestLogin(email, phone);

        }catch (Exception e){
            e.printStackTrace();
            statusLiveData.postValue(Resource.error(e.getMessage(), null));
        }

        return statusLiveData;
    }
}
