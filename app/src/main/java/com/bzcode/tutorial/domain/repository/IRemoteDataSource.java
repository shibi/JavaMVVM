package com.bzcode.tutorial.domain.repository;

import androidx.lifecycle.LiveData;

import com.bzcode.tutorial.data.remote.dto.login.User;
import com.bzcode.tutorial.data.remote.dto.register.RegisterResponse;
import com.bzcode.tutorial.domain.utils.utils.api_util.Resource;

/**
 * @author Shibin
 * created on 01-08-2023 at 10:40
 */
public interface IRemoteDataSource {

    LiveData<Resource<User>> requestLogin(String email, String phone);

    LiveData<Resource<RegisterResponse>> registerUser(String name, String email, String phone);

}
