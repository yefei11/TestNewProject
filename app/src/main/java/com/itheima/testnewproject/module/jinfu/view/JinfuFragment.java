package com.itheima.testnewproject.module.jinfu.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itheima.testnewproject.R;
import com.itheima.testnewproject.app.Injectors;
import com.itheima.testnewproject.bean.Banner;
import com.itheima.testnewproject.bean.BorrowPage;
import com.itheima.testnewproject.common.Constants;
import com.itheima.testnewproject.common.fragment.BasePresenterListFragment;
import com.itheima.testnewproject.module.jinfu.contract.FinanceHomeContract;
import com.itheima.testnewproject.module.jinfu.presenter.FinanceHomePresenterImplV205B;
import com.itheima.testnewproject.utils.NumUtil;
import com.itheima.testnewproject.utils.SpannableUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创建者     yf
 * 创建时间   2018/4/28 18:38
 * 描述	      ${TODO}
 * <p>
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */

public class JinfuFragment extends BasePresenterListFragment<BorrowPage, FinanceHomePresenterImplV205B> implements FinanceHomeContract.View {

    private ImageView typeTv;
    private TextView nameTv;
    private TextView timeTv;
    private TextView rateTv;
    private TextView yujiTv;
    private ImageView saledIv;
    private ProgressBar myCircleProgress;
    private ProgressBar getMyCircleProgressfull;
    private TextView projDesTv;
    private TextView moneyTv;
    private LinearLayout mProgeressLL;
    private LinearLayout mSubContentLl;
    private TextView mSubTv;
    private LinearLayout mSubSuccessRL;
    private TextView mujiDestTv;
    private TextView lockedDesTv;
    private TextView floatingSign;
    private ImageView mIvHelp;
    private ImageView mIvCallCenter;
    /**
     * 倒计时相关的
     */
    private TextView mHourHightTv;
    private TextView mHourLowTv;
    private TextView mMinuteHightTv;
    private TextView mMinuteLowTv;
    private TextView mSecondHightTv;
    private TextView mSecondLowTv;
    /**
     * headview
     */
    // 滑动多少距离后标题不透明
    private int slidingDistance;
    private int totalScrollY = 0;
    private ViewGroup floatTopView;
    private TextView titleTv;
    private ViewFlipper mViewFlipper;
    private LinearLayout inivteLL;
    private LinearLayout securetyLL;
    private LinearLayout sginedLL;
    private LinearLayout mineJinfuLL;
    private TextView moreNoticesTv;
    private TextView moreProdTv;
    private List<Integer> pos = new ArrayList<>();
    private ImageView ivSign;
    private ImageView mTitleHelp;
    private ImageView mTitleCallCenter;
    //    private AnimationDrawable animationDrawable;
    //公告
    private TextView tvVisibleNotice;
    private View btnCloseNotice;
    private View noticeContainer;

    @Override
    protected void initInjector() {
        super.initInjector();
        Injectors.financeHomeComponent(this).inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getAdapter().setOnLoadMoreListener(null);
    }

    @NonNull
    @Override
    protected Map<String, Object> getFetchMoreListItemsParams() {
        Map<String, Object> params = new HashMap<>();
        return params;
    }

    @NonNull
    @Override
    protected Map<String, Object> getFetchListItemsParams() {
        Map<String, Object> params = new HashMap<>();
        return params;
    }

    @Override
    protected BaseQuickAdapter createAdapter() {
        return new SectionAdapter(R.layout.item_jinfu_home, R.layout.item_jinfu_tag_finance, null);
    }

    @Override
    public void onGetBannerDataSuccess(List<Banner> banners) {

    }

    @Override
    public void onItemClick(BorrowPage item, BaseQuickAdapter adapter, View view, int position) {
        if (item.isHeader)
            return;
        goToProductDetail(item.getBorrowId());
    }

    /**
     * 跳转到产品详情页面
     * @param borrowId
     */
    private void goToProductDetail(String borrowId) {
        Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
        intent.putExtra(Constants.BORROWEID, borrowId);
        startActivity(intent);
    }

    class SectionAdapter extends BaseSectionQuickAdapter<BorrowPage, BaseViewHolder> {


        /**
         * Same as QuickAdapter#QuickAdapter(Context,int) but with
         * some initialization data.
         *
         * @param layoutResId      The layout resource id of each item.
         * @param sectionHeadResId The section head layout id for each item
         * @param data             A new list is created out of this one to avoid mutable list
         */
        public SectionAdapter(int layoutResId, int sectionHeadResId, List<BorrowPage> data) {
            super(layoutResId, sectionHeadResId, data);
        }

        @Override
        protected void convertHead(BaseViewHolder helper, BorrowPage item) {
            helper.setText(R.id.tv_suggestion, item.header);
            helper.setVisible(R.id.more_prod_tv, 1 == item.getIsMore());
            ImageView titleIcon = helper.getView(R.id.iv_project_title_icon);
            Glide.with(getActivity())
                    .load(item.getTitleImg())
                    .fitCenter().into(titleIcon);
        }

        @Override
        protected void convert(BaseViewHolder helper, BorrowPage item) {
            bindData(helper, item);
        }

        private void bindData(BaseViewHolder helper, BorrowPage bean) {
            mProgeressLL = (LinearLayout) helper.convertView.findViewById(R.id.progress_ll);
            lockedDesTv = (TextView) helper.convertView.findViewById(R.id.locked_time_des_tv);
            mujiDestTv = (TextView) helper.convertView.findViewById(R.id.muji_money_des_tv);
            typeTv = (ImageView) helper.convertView.findViewById(R.id.type_iv);
            nameTv = (TextView) helper.convertView.findViewById(R.id.name_tv);
            projDesTv = (TextView) helper.convertView.findViewById(R.id.proj_des_tv);
            timeTv = (TextView) helper.convertView.findViewById(R.id.time_tv);
            rateTv = (TextView) helper.convertView.findViewById(R.id.rate_tv);
            yujiTv = (TextView) helper.convertView.findViewById(R.id.text_yuji);
            saledIv = (ImageView) helper.convertView.findViewById(R.id.saled_iv);
            myCircleProgress = (ProgressBar) helper.convertView.findViewById(R.id.progress);
            getMyCircleProgressfull = (ProgressBar) helper.convertView.findViewById(R.id.progress_full);
            moneyTv = (TextView) helper.convertView.findViewById(R.id.muji_money_tv);

            if (!TextUtils.isEmpty(bean.getBorrowAmount())) {
                long borrowAmount = Long.parseLong(bean.getBorrowAmount());
                if (borrowAmount >= 10000) {
                    moneyTv.setText(NumUtil.save2Decimal(borrowAmount, 10000));
                    mujiDestTv.setText("募集金额(万元)");
                } else {
                    moneyTv.setText(borrowAmount + "");
                    mujiDestTv.setText("募集金额(元)");
                }
                //                moneyTv.setText(NumUtil.yuan2wanNoUnit(Integer.valueOf(bean.getBorrowAmount())));
            }
            nameTv.setText(bean.getTitle());

            mHourHightTv = (TextView) helper.convertView.findViewById(R.id.hour_hight_tv);
            mHourLowTv = (TextView) helper.convertView.findViewById(R.id.hour_low_tv);
            mMinuteHightTv = (TextView) helper.convertView.findViewById(R.id.minute_hight_tv);
            mMinuteLowTv = (TextView) helper.convertView.findViewById(R.id.minute_low_tv);
            mSecondHightTv = (TextView) helper.convertView.findViewById(R.id.second_hight_tv);
            mSecondLowTv = (TextView) helper.convertView.findViewById(R.id.second_low_tv);
            //   String rate = UIUtils.DecimalFormat(bean.getAnnualRate(),1) + "%";
            String rate = bean.getAnnualRate() + "%";
            rateTv.setText(SpannableUtils.setTextSize(rate, 0, rate.length() - 1, 22));
            timeTv.setText(bean.getBorrowTimeLimit() + bean.getLimitUnitDisplay());
            //        timeTv.setText(SpannableUtils.setTextSize(time, 0, time.contains("天") ? time.length() - 1 : time.length() - 2, 20));
            float prog = bean.getInvestProgressRate();
            myCircleProgress.setProgress((int) prog);
            mProgeressLL.setVisibility(View.VISIBLE);
//            mSubContentLl.setVisibility(View.GONE);

            switch (bean.getBorrowStatus()) {//标的状态(0.投资中;1.售磬;2.产品计息;3.产品到期)
                case "0":
                    //      myCircleProgress.setProgress(position*10);
                    myCircleProgress.setVisibility(View.VISIBLE);
                    getMyCircleProgressfull.setVisibility(View.GONE);
                    myCircleProgress.setProgress((int) prog);
                    projDesTv.setText((int) prog + "%");
                    projDesTv.setTextColor(mContext.getResources().getColor(R.color.recheck));
                    moneyTv.setTextColor(mContext.getResources().getColor(R.color.color_black));
                    nameTv.setTextColor(mContext.getResources().getColor(R.color.font_gray));
                    timeTv.setTextColor(mContext.getResources().getColor(R.color.color_black));
                    rateTv.setTextColor(mContext.getResources().getColor(R.color.recheck));
                    yujiTv.setTextColor(mContext.getResources().getColor(R.color.black_3));
                    mujiDestTv.setTextColor(mContext.getResources().getColor(R.color.black_3));
                    lockedDesTv.setTextColor(mContext.getResources().getColor(R.color.black_3));
                    saledIv.setVisibility(View.GONE);
                    //后台说这个版本没这个字段 暂时写死
                    typeTv.setBackgroundResource(R.drawable.fixed);

                    if (bean.isNovice())
                        typeTv.setBackgroundResource(R.drawable.newter);
                    else
                        typeTv.setBackgroundResource(R.drawable.fixed);
                    break;
                default:
                    //                      myCircleProgress.setProgress(100);

                    myCircleProgress.setVisibility(View.GONE);
                    getMyCircleProgressfull.setVisibility(View.VISIBLE);
                    saledIv.setVisibility(View.VISIBLE);
                    moneyTv.setTextColor(mContext.getResources().getColor(R.color.black_3));
                    nameTv.setTextColor(mContext.getResources().getColor(R.color.black_3));
                    timeTv.setTextColor(mContext.getResources().getColor(R.color.black_3));
                    rateTv.setTextColor(mContext.getResources().getColor(R.color.black_3));
                    yujiTv.setTextColor(mContext.getResources().getColor(R.color.black_3));
                    mujiDestTv.setTextColor(mContext.getResources().getColor(R.color.black_3));
                    lockedDesTv.setTextColor(mContext.getResources().getColor(R.color.black_3));
                    typeTv.setBackgroundResource(R.drawable.fixed_saled);
                    myCircleProgress.setProgress(0);
                    projDesTv.setTextColor(mContext.getResources().getColor(R.color.black_3));
                    projDesTv.setText("100%");

                    if (bean.isNovice())
                        typeTv.setBackgroundResource(R.drawable.newter_sailed);

            }

        }
    }
}


