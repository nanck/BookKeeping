package com.android.book.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import com.android.book.AppExecutors;
import com.android.book.data.DataRepository;
import com.android.book.data.db.entity.PayType;
import com.android.book.data.db.entity.Type;

import java.util.List;

public class TypesViewModel extends AndroidViewModel {

    private DataRepository repository;

    public TypesViewModel(Application application) {
        super(application);
        AppExecutors appExecutors = AppExecutors.getInstance();
        repository = new DataRepository(application, appExecutors);
    }

    public LiveData<List<Type>> getAllTypes() {
        return repository.getTypes();
    }

    public LiveData<List<PayType>> getAllPayTypes() {
        return repository.getPayTypes();
    }
}
