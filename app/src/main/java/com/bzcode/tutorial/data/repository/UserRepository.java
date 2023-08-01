package com.bzcode.tutorial.data.repository;

import androidx.lifecycle.LiveData;

import com.bzcode.tutorial.data.remote.dto.login.User;
import com.bzcode.tutorial.data.remote.dto.register.RegisterResponse;
import com.bzcode.tutorial.domain.repository.IRepository;
import com.bzcode.tutorial.domain.usecase.AutoLoginUseCase;
import com.bzcode.tutorial.domain.usecase.LoginUseCase;
import com.bzcode.tutorial.domain.usecase.RegisterUseCase;
import com.bzcode.tutorial.domain.usecase.SaveUserUserCase;
import com.bzcode.tutorial.domain.utils.utils.api_util.Resource;



/**
 * @author Shibin
 * created on 01-08-2023 at 10:44
 */
public class UserRepository implements IRepository {

    private LoginUseCase loginUseCase;
    private RegisterUseCase registerUseCase;;

    private AutoLoginUseCase autoLoginUseCase;

    private SaveUserUserCase saveUserUserCase;

    public UserRepository(LoginUseCase loginUseCase ,RegisterUseCase registerUseCase, AutoLoginUseCase autoLoginUseCase, SaveUserUserCase saveUserUserCase){
        this.loginUseCase = loginUseCase;
        this.autoLoginUseCase = autoLoginUseCase;
        this.registerUseCase = registerUseCase;
        this.saveUserUserCase = saveUserUserCase;
    }

    @Override
    public LiveData<Resource<User>> autoLogin() {
        return autoLoginUseCase.execute();
    }

    @Override
    public LiveData<Resource<User>> requestLogin(String email, String phone) {
        return loginUseCase.execute(email, phone);
    }

    @Override
    public LiveData<Resource<RegisterResponse>> registerUser(String name, String email, String phone) {
        return registerUseCase.execute(name, email, phone);
    }

    @Override
    public LiveData<Resource<Boolean>> saveUser(User user) {
        return saveUserUserCase.execute(user);
    }
}
