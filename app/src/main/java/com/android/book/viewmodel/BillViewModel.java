package com.android.book.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import com.android.book.AppExecutors;
import com.android.book.data.DataRepository;
import com.android.book.data.db.entity.Bill;

import java.util.List;

/**
 * Created by sxshi on 2019-4-17.
 */

public class BillViewModel extends AndroidViewModel {

    private DataRepository repository;

    public BillViewModel(Application application) {
        super(application);
        AppExecutors appExecutors = AppExecutors.getInstance();
        repository = new DataRepository(application, appExecutors);
    }

    public LiveData<List<Bill>> getBills(String phone) {
        return repository.getBills(phone);
    }

    public LiveData<List<Bill>> getWeekBills(String phone) {
        return repository.getWeekBills(phone);
    }

    public LiveData<List<Bill>> getMonthBills(String phone) {
        return repository.getMonthBills(phone);
    }

    public void addBill(Bill bill) {
        repository.addBill(bill);
    }
}
