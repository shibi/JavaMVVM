package com.bzcode.tutorial.ui.login;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bzcode.tutorial.R;
import com.bzcode.tutorial.data.local.AppDatabase;
import com.bzcode.tutorial.data.remote.api.ApiGenerator;
import com.bzcode.tutorial.data.remote.api.ApiService;
import com.bzcode.tutorial.data.remote.dto.login.User;
import com.bzcode.tutorial.data.repository.LocalDataSource;
import com.bzcode.tutorial.data.repository.RemoteDataSource;
import com.bzcode.tutorial.data.repository.UserRepository;
import com.bzcode.tutorial.domain.repository.ILocalDataSource;
import com.bzcode.tutorial.domain.repository.IRemoteDataSource;
import com.bzcode.tutorial.domain.repository.IRepository;
import com.bzcode.tutorial.domain.usecase.AutoLoginUseCase;
import com.bzcode.tutorial.domain.usecase.LoginUseCase;
import com.bzcode.tutorial.domain.usecase.RegisterUseCase;
import com.bzcode.tutorial.domain.usecase.SaveUserUserCase;
import com.bzcode.tutorial.domain.utils.AppExecutors;
import com.bzcode.tutorial.domain.utils.CoreApp;
import com.bzcode.tutorial.domain.utils.utils.AppDialogs;
import com.bzcode.tutorial.domain.utils.utils.api_util.Resource;
import com.bzcode.tutorial.ui.common.BaseActivity;
import com.bzcode.tutorial.ui.dashboard.DashBoardActivity;

public class LoginActivity extends BaseActivity {

    private LoginViewModel loginViewModel;

    private AppCompatEditText etEmail, etPhone;
    private AppCompatButton btn_login;
    private AppDialogs progressDialog;


    @Override
    protected int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

        etEmail = findViewById(R.id.et_email);
        etPhone = findViewById(R.id.et_phone);
        btn_login = findViewById(R.id.btn_signIn);

        progressDialog = new AppDialogs(this);

        //init viewmodel block start>
        //services
        ApiService apiService = ApiGenerator.createNoTokenApiService(ApiService.class);
        AppDatabase appDatabase = ((CoreApp)getApplication()).getLocalDb();
        AppExecutors appExecutors = new AppExecutors();
        //data sources
        IRemoteDataSource remoteDataSource = new RemoteDataSource(apiService);
        ILocalDataSource localDataSource = new LocalDataSource(appDatabase);
        //use cases
        LoginUseCase loginUseCase = new LoginUseCase(remoteDataSource);
        RegisterUseCase registerUseCase = new RegisterUseCase(remoteDataSource);
        AutoLoginUseCase autoLoginUseCase = new AutoLoginUseCase(localDataSource, appExecutors);
        SaveUserUserCase saveUserUserCase = new SaveUserUserCase(localDataSource, appExecutors);
        //repository
        IRepository userRepository = new UserRepository(loginUseCase, registerUseCase, autoLoginUseCase, saveUserUserCase);
        LoginViewModelFactory factory = new LoginViewModelFactory(userRepository);
        loginViewModel = new ViewModelProvider(this, factory).get(LoginViewModel.class);
        //--init viewmodel block end>

        //set live data observer
        setObserver();

        //login button click
        btn_login.setOnClickListener(v -> onLoginButtonClick());

    }

    /**
     * observers
     * */
    private void setObserver(){

        loginViewModel.getLoginLiveData().observe(this, new Observer<Resource<User>>() {
            @Override
            public void onChanged(Resource<User> userResource) {
                switch (userResource.status)
                {
                    case LOADING:
                        //show progress
                        progressDialog.showProgressBar();

                        break;
                    case SUCCESS:
                        //hide progress
                        progressDialog.hideProgressbar();
                        //alert user
                        showToast("LoginSuccess");
                        //redirect to dashboard
                        redirectTo(DashBoardActivity.class, false);

                        break;
                    case ERROR:
                        //alert error to user
                        showToast(userResource.message);
                        break;
                }
            }
        });

    }

    /**
     * handle login button click
     * */
    private void onLoginButtonClick(){

        //request login
        loginViewModel.requestLogin(
                etEmail.getText().toString().trim(),
                etPhone.getText().toString().trim());
    }

}