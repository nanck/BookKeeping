package com.android.book.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

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

public class NewBillFragment extends Fragment {

    private NewBillViewModel mViewModel;


    private static final String TAG = NewBillFragment.class.getSimpleName();

    private AppCompatEditText et_desc, et_amount;

    private AppCompatSpinner spinner_type, sp_pay_type;
    private AppCompatButton btn_finish;

    private TypesViewModel typesViewModel;
    private BillViewModel billViewModel;
    private List<Type> mTpyes = new ArrayList<>();

    private List<PayType> mPayTypes = new ArrayList<>();

    private Type mCurrType;
    private PayType mCurrPayType;

    public static NewBillFragment newInstance() {
        return new NewBillFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_bill_fragment, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.addbill));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                finish();
            }
        });

        et_amount = view.findViewById(R.id.et_amount);
        et_desc = view.findViewById(R.id.et_desc);
        spinner_type = view.findViewById(R.id.spinner_type);
        sp_pay_type = view.findViewById(R.id.sp_pay_type);
        btn_finish = view.findViewById(R.id.btn_finish);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NewBillViewModel.class);


        final TypeSpinnerAdapter typeAdapter = new TypeSpinnerAdapter(requireContext());
        final PayTypeSpinnerAdapter payTypeAdapter = new PayTypeSpinnerAdapter(requireContext());

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
                    Toast.makeText(requireActivity(), "请输入消费描述", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mCurrType == null) {
                    Toast.makeText(requireActivity(), "请选择类型", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(amount)) {
                    Toast.makeText(requireActivity(), "请填写消费金额", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mCurrPayType == null) {
                    Toast.makeText(requireActivity(), "请选择支付渠道", Toast.LENGTH_SHORT).show();
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
//                finish();
            }
        });
    }

}
