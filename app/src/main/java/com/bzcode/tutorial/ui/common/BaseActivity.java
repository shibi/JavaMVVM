package com.bzcode.tutorial.ui.common;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Shibin
 * created on 01-08-2023 at 11:56
 */
public abstract class BaseActivity extends AppCompatActivity {

    private String TAG = "----------";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onCreateInit();

        int layout = setLayout();
        setContentView(layout);

        initView();

        onCreateEnd();

    }
    protected abstract int setLayout();

    protected abstract void initView();


    protected void onCreateInit(){

    }
    protected void onCreateEnd(){

    }


    protected <T> void redirectTo(Class<T> destination, Boolean keepHistory){
        Intent destinationIntent = new Intent(this, destination);
        startActivity(destinationIntent);
        if(!keepHistory){
            finish();
        }
    }
    protected void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void showLog(String message){
        Log.e(TAG, message);
    }


}
