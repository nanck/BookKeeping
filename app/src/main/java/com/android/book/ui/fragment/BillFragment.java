package com.android.book.ui.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.book.R;
import com.android.book.data.db.entity.Bill;
import com.android.book.data.db.entity.UserInfo;
import com.android.book.ui.adapter.BillListAdapter;
import com.android.book.ui.model.GloabalUtils;
import com.android.book.utilitles.RecyclerViewDivider;
import com.android.book.viewmodel.BillViewModel;

import java.util.List;

/**
 * Activities that contain this fragment must implement the
 * {@link BillFragment} interface
 * to handle interaction events.
 * Use the {@link BillFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BillFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    private BillViewModel billViewModel;

    private RecyclerView recyclerview;
    private BillListAdapter billListAdapter;

    public BillFragment() {

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
    public static BillFragment newInstance(String param1, String param2) {
        BillFragment fragment = new BillFragment();
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
        View view = inflater.inflate(R.layout.fragment_bill, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.nav_bill));
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        recyclerview = view.findViewById(R.id.recyclerview);
        billListAdapter = new BillListAdapter(getActivity());
        recyclerview.setAdapter(billListAdapter);
        recyclerview.addItemDecoration(new RecyclerViewDivider(getActivity(), LinearLayoutManager.HORIZONTAL));
        billViewModel = ViewModelProviders.of(this).get(BillViewModel.class);
        UserInfo user = GloabalUtils.getUser();
        String phone = "";
        if (user != null) {
            phone = user.getPhoneNumber();
        }
        //本月
        billViewModel.getMonthBills(phone).observe(this, new Observer<List<Bill>>() {
            @Override
            public void onChanged(@Nullable List<Bill> bills) {
                if (bills != null) {
                    billListAdapter.setBillList(bills);
                }
            }
        });
        return view;
    }


}
