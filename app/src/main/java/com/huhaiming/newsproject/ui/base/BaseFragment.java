package com.huhaiming.newsproject.ui.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by ouyang on 16/1/10.
 */
public class BaseFragment extends Fragment {
    protected ProgressDialog pd;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pd = new ProgressDialog(getActivity());

    }

    protected void showProgerss() {
        if (pd != null) {
            pd.show();
        }
    }

    protected void hideProgress() {
        if (pd != null) {
            pd.hide();
            pd.dismiss();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        hideProgress();
    }
}


