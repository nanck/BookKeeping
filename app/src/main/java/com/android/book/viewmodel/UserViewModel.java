package com.android.book.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.android.book.AppExecutors;
import com.android.book.data.DataRepository;
import com.android.book.data.db.entity.UserInfo;

/**
 * Created by sxshi on 2019-4-17.
 */

public class UserViewModel extends AndroidViewModel {

    private DataRepository repository;

    public UserViewModel(Application application) {
        super(application);
        AppExecutors appExecutors = AppExecutors.getInstance();
        repository = new DataRepository(application, appExecutors);
    }

    public void addUser(UserInfo userInfo) {
        repository.addUser(userInfo);
    }

    public LiveData<UserInfo> getUser(String userName, String pwd) {
        return repository.getUserInfo(userName, pwd);
    }
}
