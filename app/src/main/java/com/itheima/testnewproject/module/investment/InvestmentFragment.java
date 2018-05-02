package com.itheima.testnewproject.module.investment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.itheima.testnewproject.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 创建者     yf
 * 创建时间   2018/4/28 18:38
 * 描述	      ${TODO}
 * <p>
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */

public class InvestmentFragment extends Fragment {
    @Bind(R.id.tv)
    TextView tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(),R.layout.fragment_home,null);
        //        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        ToastUtils.showShortToast("这是怎么了");
        return view;
    }
}
