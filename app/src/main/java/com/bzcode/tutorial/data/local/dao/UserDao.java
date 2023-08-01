package com.bzcode.tutorial.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.bzcode.tutorial.data.local.entity.UserEntity;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void saveUser(UserEntity user);

    @Query("SELECT * FROM userstable")
    List<UserEntity> getAllUsers();

    @Query("DELETE FROM UsersTable")
    void deleteAll();

}
