package com.itheima.testnewproject.module.investment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.utils.ToastUtils;
import com.itheima.testnewproject.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

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

public class InvestmentFragment extends Fragment implements RecyclerViewLeftAdapter.OnItemClickListener, RecyclerViewRightAdapter.OnRightItemClickListener {
    @Bind(R.id.classify_left_list)
    RecyclerView mRecyclerView;
    @Bind(R.id.classify_right_list)
    RecyclerView mRecyclerViewRight;

    private List<String> dataTitle = new ArrayList<>();
    private RecyclerViewLeftAdapter indexQueryLeftAdapter;

    private List<String> data = new ArrayList<>();
    private RecyclerViewRightAdapter indexQueryRightAdapter;
    private String taskPasition;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_home, null);
        //        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        //        ToastUtils.showShortToast("这是怎么了");
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    private void initData() {
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        for (int i = 0; i < 20; i++) {
            dataTitle.add("Android:\t" + i);
        }
        indexQueryLeftAdapter = new RecyclerViewLeftAdapter(getActivity(), dataTitle);
        mRecyclerView.setAdapter(indexQueryLeftAdapter);
        indexQueryLeftAdapter.setOnRecyclerItemClickListener(this);

        mRecyclerViewRight.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        for (int i = 0; i < 11; i++) {
            data.add("" + i);
        }
        indexQueryRightAdapter = new RecyclerViewRightAdapter(getActivity(), data);
        mRecyclerViewRight.setAdapter(indexQueryRightAdapter);
        indexQueryRightAdapter.setOnRightItemClickListener(this);
        mRecyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mRecyclerViewRight.setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    @Override
    public void onLeftItemClick(int position) {
        taskPasition = dataTitle.get(position);
        List<String> lineType = new ArrayList<>();
        for (int i = 0; i < Math.random() * 10; i++) {
            lineType.add(Integer.parseInt(new DecimalFormat("0").format(Math.random() * 10)) + "");
        }
        indexQueryRightAdapter.updateData(lineType);
//        scrollToTop(position);
    }

    @Override
    public void onRightItemClick(int position) {

        //        showToast("You clicked:\t" + position);
        ToastUtils.showShortToast( "You clicked:\t" + position);
    }

   /* private void scrollToTop(int n) {
        //先从RecyclerView的LayoutManager中获取第一项和最后一项的Position
        int firstItem = mLinearLayoutManager.findFirstVisibleItemPosition();
        int lastItem = mLinearLayoutManager.findLastVisibleItemPosition();
        printLogd("firstItem:\t" + firstItem);
        printLogd("lastItem:\t" + lastItem);
        //然后区分情况
        if (n <= firstItem) {
            //当要置顶的项在当前显示的第一个项的前面时
            //            rvRecyclerView.scrollToPosition(n);//有bug
            mRecyclerView.smoothScrollBy(0, mRecyclerView.getChildAt(n - firstItem).getTop(), new LinearInterpolator());
        } else if (n <= lastItem) {
            //当要置顶的项已经在屏幕上显示时
            int top = mRecyclerView.getChildAt(n - firstItem).getTop();
            mRecyclerView.smoothScrollBy(0, top, new LinearInterpolator());
            //            mRecyclerView.smoothScrollToPosition(n);
        } else {
            //当要置顶的项在当前显示的最后一项的后面时
            mRecyclerView.scrollToPosition(n);

            //这里这个变量是用在RecyclerView滚动监听里面的
            //            move = true;
        }
}*/
}
