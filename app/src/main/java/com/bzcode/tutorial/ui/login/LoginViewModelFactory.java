package com.bzcode.tutorial.ui.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.bzcode.tutorial.domain.repository.IRepository;

/**
 * @author Shibin
 * created on 01-08-2023 at 12:23
 */
public class LoginViewModelFactory extends ViewModelProvider.NewInstanceFactory{

    private IRepository repository;

    public LoginViewModelFactory(IRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == LoginViewModel.class) {
            return (T) new LoginViewModel(repository);
        }
        return null;
    }
}
