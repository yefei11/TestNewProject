package com.itheima.testnewproject.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2016/12/2.
 */
public class ProductDetailBean implements Serializable {
    public String money;
    public ProductDetail productDetail;
    public List<BorrowUserBasicInfo> borrowUserBasicInfo;
    public int isActivityTime;
    public int isClick;
    public String link;
    public String marqueeInfo;

    public static class ProductDetail implements Serializable {
        public String allowTenderTime;
        public String annualInterestRate;
        public String balanceMoney;
        public String borrowDes;
        public String borrowEid;
        public String borrowFullAddtime;
        public String borrowId;
        public int borrowStatus;//状态（0.正常 1.售磬）
        public long borrowSum;
        public String borrowSumUnit;
        public String borrowTimeLimit;
        public String borrowTimeLimitUnit;
        public String borrowTitle;
        public String calInterestType;
        public String fullReviewTime;
        public String minAmount;
        public int maxAmount;
        public String productType;
        public String repaymentTime;
        public String repaymentWay;
        public String repaymentWayDisplay;
        public String tenderProgressRate;
        public String templateBussId;
        public String contractPath;
        public String exitExplain;
        public String riskLevelDisplay;//  风险等级
        public String investorRequirementDisplay;//  投资人要求
        public String productInformation;
    }

}
