package com.bzcode.tutorial.domain.repository;

import androidx.lifecycle.LiveData;

import com.bzcode.tutorial.data.remote.dto.login.User;
import com.bzcode.tutorial.domain.utils.utils.api_util.Resource;

import java.util.List;

/**
 * @author Shibin
 * created on 01-08-2023 at 10:39
 */
public interface ILocalDataSource {

    void saveUser(User user);

    List<User> getUsers();
}
