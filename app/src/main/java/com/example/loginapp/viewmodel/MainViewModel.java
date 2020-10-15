package com.example.loginapp.viewmodel;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.loginapp.model.LoginUser;

public class MainViewModel extends ViewModel {
    private final MutableLiveData<String> username = new MediatorLiveData<>();
    private final MutableLiveData<String> password = new MediatorLiveData<>();
    /**
     * Expose MutableLiveData to use two-way data binding
     */
    private MutableLiveData<LoginUser>loginUserMutableLiveData=new MutableLiveData<>();
    public MutableLiveData<String> getUsername() {
        return username;
    }
    public MutableLiveData<String> getPassword() {
        return password;
    }
    public void getUser() {
        Observer observer = new Observer<LoginUser>() {
            @Override
            public void onChanged(LoginUser loginUser) {
                username.setValue(loginUser == null ? null : loginUser.getUsername());
                password.setValue(loginUser == null ? null : loginUser.getPassword());
                if (username.getValue() != null && password.getValue() != null) {
                    loginUser = new LoginUser(username.getValue(), password.getValue());
                    loginUserMutableLiveData.setValue(loginUser);
                }
                else
                    loginUserMutableLiveData.setValue(null);
            }
        };
    }
}
