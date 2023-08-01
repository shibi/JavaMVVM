package com.bzcode.tutorial.data.remote.api;

import com.bzcode.tutorial.data.remote.dto.login.LoginResponse;
import com.bzcode.tutorial.data.remote.dto.register.RegisterResponse;
import com.bzcode.tutorial.data.remote.request.login.LoginRequest;
import com.bzcode.tutorial.data.remote.request.register.RegisterRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("mockLogin")
    Call<LoginResponse> requestLogin(@Body LoginRequest request);

    @POST("mockRegister")
    Call<RegisterResponse> registerUser(@Body RegisterRequest request);

}
