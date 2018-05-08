package com.itheima.testnewproject.bean;

import java.util.List;

/**
 * 描述说明  <br/>
 * Author : zhongw <br/>
 * CreateDate : 2017/5/10 9:54 <br/>
 * Version 1.0
 */
public class HomeIndex {

    private List<InnerData> featureList;
    private List<Finance> financeList;
    private List<InnerData> iurList;
    private List<InnerData> bigBannerList;
    private List<InnerData> mallList;
    private List<InnerData> entrepreneurList;
    private List<InnerData> investmentList;
    private List<Trade> tradeList;

    public List<InnerData> getFeatureList() {
        return featureList;
    }

    public List<Finance> getFinanceList() {
        return financeList;
    }

    public List<InnerData> getIurList() {
        return iurList;
    }

    public List<InnerData> getBigBannerList() {
        return bigBannerList;
    }

    public List<InnerData> getMallList() {
        return mallList;
    }

    public List<InnerData> getEntrepreneurList() {
        return entrepreneurList;
    }

    public List<InnerData> getInvestmentList() {
        return investmentList;
    }

    public List<Trade> getTradeList() {
        return tradeList;
    }

    public static class Finance {
        private String investMinAmount;
        private String borrowStatus;
        private String limitUnit;
        private long borrowAmount;
        private String annualRate;
        private String productId;
        private String productType;
        private String actualPublishTime;
        private String title;
        private String repaymentAway;
        private String borrowId;
        private String investProgressRate;
        private String limitUnitDisplay;
        private String actualAmount;
        private String investMaxAmount;
        private String productTypeDisplay;
        private String repaymentAwayDisplay;
        private String borrowTimeLimit;
        private String borrowStatusDisplay;

        public String getInvestMinAmount() {
            return investMinAmount;
        }

        public String getBorrowStatus() {
            return borrowStatus;
        }

        public String getLimitUnit() {
            return limitUnit;
        }

        public long getBorrowAmount() {
            return borrowAmount;
        }

        public String getAnnualRate() {
            return annualRate;
        }

        public String getProductId() {
            return productId;
        }

        public String getProductType() {
            return productType;
        }

        public String getActualPublishTime() {
            return actualPublishTime;
        }

        public String getTitle() {
            return title;
        }

        public String getRepaymentAway() {
            return repaymentAway;
        }

        public String getBorrowId() {
            return borrowId;
        }

        public String getInvestProgressRate() {
            return investProgressRate;
        }

        public String getLimitUnitDisplay() {
            return limitUnitDisplay;
        }

        public String getActualAmount() {
            return actualAmount;
        }

        public String getInvestMaxAmount() {
            return investMaxAmount;
        }

        public String getProductTypeDisplay() {
            return productTypeDisplay;
        }

        public String getRepaymentAwayDisplay() {
            return repaymentAwayDisplay;
        }

        public String getBorrowTimeLimit() {
            return borrowTimeLimit;
        }

        public String getBorrowStatusDisplay() {
            return borrowStatusDisplay;
        }
    }

    public static class InnerData {
        private int id;
        private String title;
        private String url;
        private String introduction;
        private String photoName;
        private int proBussType;
        private String proBussId;
        private int type;

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getUrl() {
            return url;
        }

        public String getIntroduction() {
            return introduction;
        }

        public String getPhotoName() {
            return photoName;
        }

        public int getProBussType() {
            return proBussType;
        }

        public String getProBussId() {
            return proBussId;
        }

        public int getType() {
            return type;
        }
    }

    public static class Trade {
        private String id;
        private String name;
        private int circleId;
        private String createDate;

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getCircleId() {
            return circleId;
        }

        public String getCreateDate() {
            return createDate;
        }
    }
}
