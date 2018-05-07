package com.itheima.testnewproject.bean;


import android.text.TextUtils;

import com.chad.library.adapter.base.entity.SectionEntity;

import java.util.List;

//public class BorrowPage implements MultiItemEntity,Comparable<BorrowPage> {
public class BorrowPage extends SectionEntity<BorrowPage> {

    private String moduleName;
    private int isMore;
    private int moduleId;
    private List<ModuleListBean> moduleList;
    private String titleImg;

    public String getTitleImg() {
        return titleImg;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg;
    }

    public BorrowPage(boolean isHeader, String header, int isMore , int moduleId, String titleImg) {
        super(isHeader, header);
        this.isMore = isMore;
        this.moduleId = moduleId;
        this.titleImg = titleImg;
    }

    public BorrowPage(BorrowPage borrowPage) {
        super(borrowPage);
    }


    private String productType; //产品类型 1-定期，4-新手
    private String productTypeDisplay; //产品类型文字
    private String borroStatusDisplay; //标的状态显示

    //以下为自定义字段
    private long countDownTimeTotal = 12 * 60 * 60 * 1000;
    private boolean hasSubed;
    private int itemType = 0;//视图类型

    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_NOVICE = 1;
    public static final int TYPE_FINANCE = 2;


    private String investMinAmount;
    private String limitUnit;
    private String borrowAmount;
    private String annualRate;
    private String productId;
    private String actualPublishTime;
    private String repaymentAway;
    private String repaymentAwayDisplay;
    private float investProgressRate;
    private String actualAmount;
    private String investMaxAmount;
    private String borrowTimeLimit;
    private String title;
    private String borrowId;
    private String limitUnitDisplay;
    private String borrowStatus;

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductTypeDisplay() {
        return productTypeDisplay;
    }

    public void setProductTypeDisplay(String productTypeDisplay) {
        this.productTypeDisplay = productTypeDisplay;
    }

    public String getBorroStatusDisplay() {
        return borroStatusDisplay;
    }

    public void setBorroStatusDisplay(String borroStatusDisplay) {
        this.borroStatusDisplay = borroStatusDisplay;
    }

    public long getCountDownTimeTotal() {
        return countDownTimeTotal;
    }

    public void setCountDownTimeTotal(long countDownTimeTotal) {
        this.countDownTimeTotal = countDownTimeTotal;
    }

    public boolean isHasSubed() {
        return hasSubed;
    }

    public void setHasSubed(boolean hasSubed) {
        this.hasSubed = hasSubed;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getInvestMinAmount() {
        return investMinAmount;
    }

    public void setInvestMinAmount(String investMinAmount) {
        this.investMinAmount = investMinAmount;
    }

    public String getLimitUnit() {
        return limitUnit;
    }

    public void setLimitUnit(String limitUnit) {
        this.limitUnit = limitUnit;
    }

    public String getBorrowAmount() {
        return borrowAmount;
    }

    public void setBorrowAmount(String borrowAmount) {
        this.borrowAmount = borrowAmount;
    }

    public String getAnnualRate() {
        return annualRate;
    }

    public void setAnnualRate(String annualRate) {
        this.annualRate = annualRate;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getActualPublishTime() {
        return actualPublishTime;
    }

    public void setActualPublishTime(String actualPublishTime) {
        this.actualPublishTime = actualPublishTime;
    }

    public String getRepaymentAway() {
        return repaymentAway;
    }

    public void setRepaymentAway(String repaymentAway) {
        this.repaymentAway = repaymentAway;
    }

    public String getRepaymentAwayDisplay() {
        return repaymentAwayDisplay;
    }

    public void setRepaymentAwayDisplay(String repaymentAwayDisplay) {
        this.repaymentAwayDisplay = repaymentAwayDisplay;
    }

    public float getInvestProgressRate() {
        return investProgressRate;
    }

    public void setInvestProgressRate(float investProgressRate) {
        this.investProgressRate = investProgressRate;
    }

    public String getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(String actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getInvestMaxAmount() {
        return investMaxAmount;
    }

    public void setInvestMaxAmount(String investMaxAmount) {
        this.investMaxAmount = investMaxAmount;
    }

    public String getBorrowTimeLimit() {
        return borrowTimeLimit;
    }

    public void setBorrowTimeLimit(String borrowTimeLimit) {
        this.borrowTimeLimit = borrowTimeLimit;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(String borrowId) {
        this.borrowId = borrowId;
    }

    public String getLimitUnitDisplay() {
        return limitUnitDisplay;
    }

    public void setLimitUnitDisplay(String limitUnitDisplay) {
        this.limitUnitDisplay = limitUnitDisplay;
    }

    public String getBorrowStatus() {
        return borrowStatus;
    }

    public void setBorrowStatus(String borrowStatus) {
        this.borrowStatus = borrowStatus;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public int getIsMore() {
        return isMore;
    }

    public void setIsMore(int isMore) {
        this.isMore = isMore;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public List<ModuleListBean> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<ModuleListBean> moduleList) {
        this.moduleList = moduleList;
    }

    /**
     * 是否新手理财
     *
     * @return
     */
    public boolean isNovice() {
        return "4".equals(getProductType())||"12".equals(getProductType());
    }

    /**
     * 已售罄?
     */
    public boolean saleOff() {
        return !"0".equals(getBorrowStatus());
    }

    public static class ModuleListBean {
        private String moduleName;
        private int isMore;
        private int moduleId;
        private String investMinAmount;
        private String limitUnit;
        private String borrowAmount;
        private String annualRate;
        private String productId;
        private String actualPublishTime;
        private String repaymentAway;
        private String repaymentAwayDisplay;
        private float investProgressRate;
        private String actualAmount;
        private String investMaxAmount;
        private String borrowTimeLimit;
        private String title;
        private String borrowId;
        private String limitUnitDisplay;
        private String borrowStatus;//标的状态(0.投资中;1.售磬;2.产品计息;3.产品到期)

        private String productType; //产品类型 1-定期，4-新手
        private String productTypeDisplay; //产品类型文字
        private String borroStatusDisplay; //标的状态显示

        //以下为自定义字段
        private long countDownTimeTotal = 12 * 60 * 60 * 1000;
        private boolean hasSubed;
        private int itemType = 0;//视图类型

        public static final int TYPE_NORMAL = 0;
        public static final int TYPE_NOVICE = 1;
        public static final int TYPE_FINANCE = 2;


        public String getModuleName() {
            return moduleName;
        }

        public void setModuleName(String moduleName) {
            this.moduleName = moduleName;
        }

        public int getIsMore() {
            return isMore;
        }

        public void setIsMore(int isMore) {
            this.isMore = isMore;
        }

        public int getModuleId() {
            return moduleId;
        }

        public void setModuleId(int moduleId) {
            this.moduleId = moduleId;
        }

        public String getLimitUnitDisplay() {
            return limitUnitDisplay;
        }

        public void setLimitUnitDisplay(String limitUnitDisplay) {
            this.limitUnitDisplay = limitUnitDisplay;
        }

        public String getBorrowId() {
            return borrowId;
        }

        public void setBorrowId(String borrowId) {
            this.borrowId = borrowId;
        }

        public boolean isHasSubed() {
            return hasSubed;
        }

        public void setHasSubed(boolean hasSubed) {
            this.hasSubed = hasSubed;
        }

        public String getBorrowStatus() {
            if (TextUtils.isEmpty(borrowStatus))
                return "0";
            return borrowStatus;
        }

        public void setBorrowStatus(String borrowStatus) {
            this.borrowStatus = borrowStatus;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getInvestMinAmount() {
            return investMinAmount;
        }

        public void setInvestMinAmount(String investMinAmount) {
            this.investMinAmount = investMinAmount;
        }

        public String getLimitUnit() {
            if ("1".equals(limitUnit)) {
                return "天";
            }
            return "个月";
        }

        public void setLimitUnit(String limitUnit) {
            this.limitUnit = limitUnit;
        }

        public String getBorrowAmount() {
            return borrowAmount;
        }

        public void setBorrowAmount(String borrowAmount) {
            this.borrowAmount = borrowAmount;
        }

        public String getAnnualRate() {
            return annualRate;
        }

        public void setAnnualRate(String annualRate) {
            this.annualRate = annualRate;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getActualPublishTime() {
            return actualPublishTime;
        }

        public void setActualPublishTime(String actualPublishTime) {
            this.actualPublishTime = actualPublishTime;
        }

        public String getRepaymentAway() {
            return repaymentAway;
        }

        public void setRepaymentAway(String repaymentAway) {
            this.repaymentAway = repaymentAway;
        }

        public String getRepaymentAwayDisplay() {
            return repaymentAwayDisplay;
        }

        public void setRepaymentAwayDisplay(String repaymentAwayDisplay) {
            this.repaymentAwayDisplay = repaymentAwayDisplay;
        }

        public float getInvestProgressRate() {
            return investProgressRate;
        }

        public void setInvestProgressRate(float investProgressRate) {
            this.investProgressRate = investProgressRate;
        }

        public String getActualAmount() {
            return actualAmount;
        }

        public void setActualAmount(String actualAmount) {
            this.actualAmount = actualAmount;
        }

        public String getInvestMaxAmount() {
            return investMaxAmount;
        }

        public void setInvestMaxAmount(String investMaxAmount) {
            this.investMaxAmount = investMaxAmount;
        }

        public String getBorrowTimeLimit() {
            return borrowTimeLimit;
        }

        public void setBorrowTimeLimit(String borrowTimeLimit) {
            this.borrowTimeLimit = borrowTimeLimit;
        }

        public long getCountDownTimeTotal() {
            return countDownTimeTotal;
        }

        public void setCountDownTimeTotal(long countDownTimeTotal) {
            this.countDownTimeTotal = countDownTimeTotal;
        }

        public String getProductType() {
            return productType;
        }

        public String getProductTypeDisplay() {
            return productTypeDisplay;
        }

        public String getBorroStatusDisplay() {
            return borroStatusDisplay;
        }

        public int getItemType() {
            return itemType;
        }

        public void setItemType(int type) {
            this.itemType = type;
        }

        /**
         * 是否新手理财
         *
         * @return
         */
        public boolean isNovice() {
            return "4".equals(getProductType());
        }

        /**
         * 已售罄?
         */
        public boolean saleOff() {
            return !"0".equals(getBorrowStatus());
        }

        //排序，先看后台做不。。
        //    @Override
        //    public int compareTo(@NonNull BorrowPage o) {
        //        return 0;
        //    }
    }
}
