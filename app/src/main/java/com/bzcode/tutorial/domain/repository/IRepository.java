package com.bzcode.tutorial.domain.repository;

import androidx.lifecycle.LiveData;

import com.bzcode.tutorial.data.remote.dto.login.User;
import com.bzcode.tutorial.data.remote.dto.register.RegisterResponse;
import com.bzcode.tutorial.domain.utils.utils.api_util.Resource;

/**
 * @author Shibin
 * created on 31-07-2023 at 22:02
 */
public interface IRepository {

    LiveData<Resource<User>> autoLogin();

    LiveData<Resource<User>> requestLogin(String email, String phone);

    LiveData<Resource<RegisterResponse>> registerUser(String name, String email, String phone);

    LiveData<Resource<Boolean>> saveUser(User user);

}
