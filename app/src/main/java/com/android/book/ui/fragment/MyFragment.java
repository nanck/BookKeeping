package com.android.book.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.book.R;
import com.android.book.ui.model.GloabalUtils;
import com.android.book.ui.model.RouteManager;
import com.android.book.ui.model.Status;
import com.android.book.ui.widget.CustomTextView;

/**
 * Activities that contain this fragment must implement the
 * {@link MyFragment} interface
 * to handle interaction events.
 * Use the {@link MyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyFragment extends Fragment {
    private static final String TAG = "ssx";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    private TextView tv_username;
    private Status userStatus = new RouteManager.LogoutStatus();


    public MyFragment() {
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
    public static MyFragment newInstance(String param1, String param2) {
        MyFragment fragment = new MyFragment();
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
        View view = inflater.inflate(R.layout.fragment_my, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.nav_my));
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        tv_username = view.findViewById(R.id.tv_username);
        CustomTextView ctv_setting = view.findViewById(R.id.ctv_setting);
        CustomTextView ctv_logout = view.findViewById(R.id.ctv_logout);

        userStatus = RouteManager.getInstance().getUserStatus();

        tv_username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userStatus.lookBasicInfo(getActivity());
            }
        });
        ctv_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userStatus.doSetting(getActivity());
            }
        });

        ctv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_username.setText(getString(R.string.hint_tologin));
                RouteManager.getInstance().setUserStatus(new RouteManager.LogoutStatus());
                GloabalUtils.clear(getActivity());
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        String userNme = GloabalUtils.getUserNme(getActivity());
        if (!TextUtils.isEmpty(userNme)) {
            tv_username.setText(userNme);
        }
        super.onResume();
    }
}
