package com.bzcode.tutorial.data.repository;

import com.bzcode.tutorial.data.local.AppDatabase;
import com.bzcode.tutorial.data.local.entity.UserEntity;
import com.bzcode.tutorial.data.remote.dto.login.User;
import com.bzcode.tutorial.domain.repository.ILocalDataSource;
import com.bzcode.tutorial.domain.utils.ModelConverter;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Shibin
 * created on 01-08-2023 at 11:30
 */
public class LocalDataSource implements ILocalDataSource {

    private AppDatabase appDatabase;

    public LocalDataSource(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    @Override
    public void saveUser(User user) {
        appDatabase.userDao().saveUser(ModelConverter.UserToUserEntity(user));
    }

    @Override
    public List<User> getUsers() {

        List<UserEntity> tempList = appDatabase.userDao().getAllUsers();
        if(tempList!=null && tempList.size()>0) {
            List<User> userList = new ArrayList<>();
            for (UserEntity entity : tempList) {
                userList.add(ModelConverter.UserEntitiyToUser(entity));
            }

            return userList;
        }
     return null;
    }
}
