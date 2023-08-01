package com.bzcode.tutorial.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bzcode.tutorial.data.remote.api.ApiService;
import com.bzcode.tutorial.data.remote.dto.login.LoginResponse;
import com.bzcode.tutorial.data.remote.dto.login.User;
import com.bzcode.tutorial.data.remote.dto.register.RegisterResponse;
import com.bzcode.tutorial.data.remote.request.login.LoginRequest;
import com.bzcode.tutorial.data.remote.request.register.RegisterRequest;
import com.bzcode.tutorial.domain.repository.IRemoteDataSource;
import com.bzcode.tutorial.domain.utils.utils.api_util.Resource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Shibin
 * created on 01-08-2023 at 11:05
 */
public class RemoteDataSource implements IRemoteDataSource {

    private ApiService apiService;

    public RemoteDataSource(ApiService apiService){
        this.apiService = apiService;
    }

    @Override
    public LiveData<Resource<User>> requestLogin(String email, String phone) {
        MutableLiveData<Resource<User>> statusLiveData = new MutableLiveData<>();

        try
        {
            statusLiveData.postValue(Resource.loading(null));

            LoginRequest loginRequest = new LoginRequest(email, phone);
            Call<LoginResponse> apiResponse = apiService.requestLogin(loginRequest);

            apiResponse.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    try{
                        if(response.isSuccessful()){

                            LoginResponse loginResponse = response.body();
                            if(loginResponse!=null){

                                if(loginResponse.getStatus()){

                                    statusLiveData.postValue(Resource.success(loginResponse.getData()));

                                }else {

                                    statusLiveData.postValue(Resource.error(loginResponse.getMessage(), null));
                                }

                            }else {
                                statusLiveData.postValue(Resource.error(response.message(), null));
                            }

                        }else {
                            statusLiveData.postValue(Resource.error(response.message(), null));
                        }
                    }catch (Exception e){
                        statusLiveData.postValue(Resource.error(e.getMessage(), null));
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    statusLiveData.postValue(Resource.error(t.getLocalizedMessage(), null));
                }
            });

        }catch (Exception e){
            e.printStackTrace();
            statusLiveData.postValue(Resource.error(e.getMessage(), null));
        }

        return statusLiveData;
    }

    @Override
    public LiveData<Resource<RegisterResponse>> registerUser(String name, String email, String phone) {
        MutableLiveData<Resource<RegisterResponse>> statusLiveData = new MutableLiveData<>();

        try
        {
            RegisterRequest request = new RegisterRequest(name, email, phone);

            Call<RegisterResponse> apiResponse = apiService.registerUser(request);

            apiResponse.enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                    try{
                        if(response.isSuccessful()){

                            RegisterResponse registerResponse = response.body();
                            if(registerResponse!=null){

                                if(registerResponse.getStatus()){

                                    statusLiveData.postValue(Resource.success(registerResponse));

                                }else {

                                    statusLiveData.postValue(Resource.error(registerResponse.getMessage(), null));
                                }

                            }else {
                                statusLiveData.postValue(Resource.error(response.message(), null));
                            }

                        }else {
                            statusLiveData.postValue(Resource.error(response.message(), null));
                        }
                    }catch (Exception e){
                        statusLiveData.postValue(Resource.error(e.getMessage(), null));
                    }
                }

                @Override
                public void onFailure(Call<RegisterResponse> call, Throwable t) {
                    statusLiveData.postValue(Resource.error(t.getLocalizedMessage(), null));
                }
            });

        }catch (Exception e){
            e.printStackTrace();
            statusLiveData.postValue(Resource.error(e.getMessage(), null));
        }

        return statusLiveData;
    }

}
