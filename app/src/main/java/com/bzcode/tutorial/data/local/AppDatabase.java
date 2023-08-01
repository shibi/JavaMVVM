package com.bzcode.tutorial.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.bzcode.tutorial.data.local.dao.UserDao;
import com.bzcode.tutorial.data.local.entity.UserEntity;

@Database(entities = {UserEntity.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();

}
