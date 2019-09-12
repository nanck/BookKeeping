package com.android.book.ui;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.book.R;

public class SplashFragment extends Fragment {


    public static SplashFragment newInstance() {
        return new SplashFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.splash_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                ((MainActivity) requireActivity()).navController.navigate(R.id.action_splashFragment_to_homeFragment,
//                        null,
//                        new NavOptions.Builder().setPopUpTo(R.id.splashFragment, true).build()
//                );
                ((MainActivity) requireActivity()).navController.navigate(R.id.action_splashFragment_to_homeFragment);
            }
        }, 3_000);
    }

}
