package com.android.book.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import com.android.book.R;
import com.android.book.data.db.entity.Bill;
import com.android.book.data.db.entity.PayType;
import com.android.book.data.db.entity.Type;
import com.android.book.data.db.entity.UserInfo;
import com.android.book.ui.adapter.PayTypeSpinnerAdapter;
import com.android.book.ui.adapter.TypeSpinnerAdapter;
import com.android.book.ui.model.GloabalUtils;
import com.android.book.utilitles.BookConstants;
import com.android.book.utilitles.Util;
import com.android.book.viewmodel.BillViewModel;
import com.android.book.viewmodel.TypesViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewBillActivity extends AppCompatActivity {
    private static final String TAG = NewBillActivity.class.getSimpleName();

    private AppCompatEditText et_desc, et_amount;

    private AppCompatSpinner spinner_type, sp_pay_type;
    private AppCompatButton btn_finish;

    TypesViewModel typesViewModel;
    BillViewModel billViewModel;
    private List<Type> mTpyes = new ArrayList<>();

    private List<PayType> mPayTypes = new ArrayList<>();

    private Type mCurrType;
    private PayType mCurrPayType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_bill);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.addbill));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        et_amount = findViewById(R.id.et_amount);
        et_desc = findViewById(R.id.et_desc);
        spinner_type = findViewById(R.id.spinner_type);
        sp_pay_type = findViewById(R.id.sp_pay_type);
        btn_finish = findViewById(R.id.btn_finish);

        final TypeSpinnerAdapter typeAdapter = new TypeSpinnerAdapter(this);
        final PayTypeSpinnerAdapter payTypeAdapter = new PayTypeSpinnerAdapter(this);

        typesViewModel = ViewModelProviders.of(this).get(TypesViewModel.class);
        billViewModel = ViewModelProviders.of(this).get(BillViewModel.class);

        typesViewModel.getAllPayTypes().observe(this, new Observer<List<PayType>>() {
            @Override
            public void onChanged(@Nullable List<PayType> payTypes) {
                mPayTypes = payTypes;
                payTypeAdapter.setmData(mPayTypes);
                Log.d(TAG, "onChanged: payTypes:" + payTypes.toString());
            }
        });

        typesViewModel.getAllTypes().observe(this, new Observer<List<Type>>() {
            @Override
            public void onChanged(@Nullable List<Type> types) {
                mTpyes = types;
                typeAdapter.setmData(mTpyes);
                Log.d(TAG, "onChanged: types:" + types.toString());
            }
        });
        spinner_type.setAdapter(typeAdapter);
        spinner_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mCurrType = mTpyes.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinner_type.setPrompt("请选择消费方式");
            }
        });
        sp_pay_type.setAdapter(payTypeAdapter);


        mCurrType = (Type) spinner_type.getSelectedItem();
        mCurrPayType = (PayType) sp_pay_type.getSelectedItem();


        sp_pay_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mCurrPayType = mPayTypes.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                sp_pay_type.setPrompt("请选择付款渠道");
            }
        });

        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String desc = et_desc.getText().toString();
                String amount = et_amount.getText().toString();
                if (TextUtils.isEmpty(desc)) {
                    Toast.makeText(NewBillActivity.this, "请输入消费描述", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mCurrType == null) {
                    Toast.makeText(NewBillActivity.this, "请选择类型", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(amount)) {
                    Toast.makeText(NewBillActivity.this, "请填写消费金额", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mCurrPayType == null) {
                    Toast.makeText(NewBillActivity.this, "请选择支付渠道", Toast.LENGTH_SHORT).show();
                    return;
                }
                Bill bill = new Bill();
                UserInfo user = GloabalUtils.getUser();
                String phone = "";
                if (user != null) {
                    phone = user.getPhoneNumber();
                }
                bill.setPhoneNumber(phone);
                bill.setAmount(Double.valueOf(amount));
                bill.setBillType(BookConstants.TYPE_EXPENSES_VALUE);
                bill.setPayType(mCurrPayType.getPayValue());
                bill.setDesc(desc);
                bill.setShoppingType(mCurrType.getValue());
                bill.setAddTtime(Util.formatDate(new Date()));
                billViewModel.addBill(bill);
                finish();
            }
        });
    }
}
