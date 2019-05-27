package com.android.book.ui.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.book.R;
import com.android.book.data.db.entity.Bill;
import com.android.book.ui.adapter.BillListAdapter;
import com.android.book.ui.model.GloabalUtils;
import com.android.book.ui.model.RouteManager;
import com.android.book.utilitles.RecyclerViewDivider;
import com.android.book.utilitles.Util;
import com.android.book.viewmodel.BillViewModel;

import java.util.List;

/**
 * Activities that contain this fragment must implement the
 * {@link HomeFragment} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private static final String TAG = "home";

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    private BillViewModel billViewModel;

    private RecyclerView recyclerview;
    private BillListAdapter billListAdapter;

    private TextView tv_amount, tv_amount1;

    private double mTotalAmount;
    private double mTotalMakeMoney = 0.00;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        tv_amount1 = view.findViewById(R.id.tv_amount1);
        tv_amount = view.findViewById(R.id.tv_amount);
        FloatingActionButton fab_add = view.findViewById(R.id.fab_add);

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RouteManager.getInstance().addBill(getActivity());
            }
        });
        recyclerview = view.findViewById(R.id.recyclerview);
        billListAdapter = new BillListAdapter(getActivity());
        recyclerview.setAdapter(billListAdapter);
        recyclerview.addItemDecoration(new RecyclerViewDivider(getActivity(), LinearLayoutManager.HORIZONTAL));
        billViewModel = ViewModelProviders.of(this).get(BillViewModel.class);

        String phone = GloabalUtils.getUserPhone(getContext());
        //本月
        billViewModel.getBills(phone).observe(this, new Observer<List<Bill>>() {
            @Override
            public void onChanged(@Nullable List<Bill> bills) {
                if (bills != null) {
                    mTotalAmount = 0;
                    for (Bill bill : bills) {
                        mTotalAmount += bill.getAmount();
                    }
                    tv_amount.setText(String.format("￥%s", Util.formatAmount(mTotalAmount)));
                    tv_amount1.setText(String.format("￥%s", Util.formatAmount(mTotalMakeMoney)));
                }
            }
        });
        //最近一周
        billViewModel.getWeekBills(phone).observe(this, new Observer<List<Bill>>() {
            @Override
            public void onChanged(@Nullable List<Bill> bills) {
                if (bills != null) {
                    bills.addAll(bills);
                    billListAdapter.setBillList(bills);
                }
            }
        });
        return view;
    }
}
