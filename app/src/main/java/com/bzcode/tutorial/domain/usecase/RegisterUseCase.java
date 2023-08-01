package com.bzcode.tutorial.domain.usecase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bzcode.tutorial.data.remote.dto.login.User;
import com.bzcode.tutorial.data.remote.dto.register.RegisterResponse;
import com.bzcode.tutorial.domain.repository.IRemoteDataSource;
import com.bzcode.tutorial.domain.utils.utils.api_util.Resource;

/**
 * @author Shibin
 * created on 01-08-2023 at 11:07
 */
public class RegisterUseCase {

    private IRemoteDataSource remoteDataSource;

    public RegisterUseCase(IRemoteDataSource remoteDataSource){
        this.remoteDataSource = remoteDataSource;
    }

    public LiveData<Resource<RegisterResponse>> execute(String name, String email, String phone){
        MutableLiveData<Resource<RegisterResponse>> statusLiveData = new MutableLiveData<>();

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

            return remoteDataSource.registerUser(name,email, phone);

        }catch (Exception e){
            e.printStackTrace();
            statusLiveData.postValue(Resource.error(e.getMessage(), null));
        }

        return statusLiveData;
    }
}
