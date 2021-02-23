package com.compy.check.bean;

import java.util.List;

public class HomeListBean {
    /**
     * head : {"code":1,"count":6,"message":"Success"}
     * body : {"data":[{"id":1,"name":"Level 1","balanceLowerLimit":500,"balanceUpperLimit":2500,"incomeRate":0.006,"tradeTimesLimit":30,"priceLowerLimit":1,"priceUpperLimit":2500,"upgradePercent":0.2,"equityMargin":500,"withdrawTimesLimit":1,"minimalWithdrawAmount":2000},{"id":2,"name":"Level 2","balanceLowerLimit":2500,"balanceUpperLimit":5000,"incomeRate":0.0065,"tradeTimesLimit":30,"priceLowerLimit":2500,"priceUpperLimit":5000,"upgradePercent":0.18,"equityMargin":2500,"withdrawTimesLimit":2,"minimalWithdrawAmount":2000},{"id":3,"name":"Level 3","balanceLowerLimit":5000,"balanceUpperLimit":10000,"incomeRate":0.0065,"tradeTimesLimit":30,"priceLowerLimit":5000,"priceUpperLimit":10000,"upgradePercent":0.15,"equityMargin":5000,"withdrawTimesLimit":2,"minimalWithdrawAmount":2000},{"id":4,"name":"Level 4","balanceLowerLimit":10000,"balanceUpperLimit":30000,"incomeRate":0.007,"tradeTimesLimit":30,"priceLowerLimit":10000,"priceUpperLimit":30000,"upgradePercent":0.12,"equityMargin":10000,"withdrawTimesLimit":3,"minimalWithdrawAmount":2000},{"id":5,"name":"Level 5","balanceLowerLimit":30000,"balanceUpperLimit":50000,"incomeRate":0.007,"tradeTimesLimit":30,"priceLowerLimit":30000,"priceUpperLimit":50000,"upgradePercent":0.08,"equityMargin":30000,"withdrawTimesLimit":4,"minimalWithdrawAmount":2000},{"id":6,"name":"Level 6","balanceLowerLimit":50000,"balanceUpperLimit":1.0E7,"incomeRate":0.007,"tradeTimesLimit":30,"priceLowerLimit":50000,"priceUpperLimit":100000,"upgradePercent":0.05,"equityMargin":50000,"withdrawTimesLimit":5,"minimalWithdrawAmount":2000}]}
     */

    private HeadBean head;
    private BodyBean body;

    public HeadBean getHead() {
        return head;
    }

    public void setHead(HeadBean head) {
        this.head = head;
    }

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public static class HeadBean {
        /**
         * code : 1
         * count : 6
         * message : Success
         */

        private int code;
        private int count;
        private String message;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class BodyBean {
        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 1
             * name : Level 1
             * balanceLowerLimit : 500.0
             * balanceUpperLimit : 2500.0
             * incomeRate : 0.006
             * tradeTimesLimit : 30
             * priceLowerLimit : 1.0
             * priceUpperLimit : 2500.0
             * upgradePercent : 0.2
             * equityMargin : 500.0
             * withdrawTimesLimit : 1
             * minimalWithdrawAmount : 2000.0
             */

            private int id;
            private String name;
            private double balanceLowerLimit;
            private double balanceUpperLimit;
            private double incomeRate;
            private int tradeTimesLimit;
            private double priceLowerLimit;
            private double priceUpperLimit;
            private double upgradePercent;
            private double equityMargin;
            private int withdrawTimesLimit;
            private double minimalWithdrawAmount;

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            private String summary;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public double getBalanceLowerLimit() {
                return balanceLowerLimit;
            }

            public void setBalanceLowerLimit(double balanceLowerLimit) {
                this.balanceLowerLimit = balanceLowerLimit;
            }

            public double getBalanceUpperLimit() {
                return balanceUpperLimit;
            }

            public void setBalanceUpperLimit(double balanceUpperLimit) {
                this.balanceUpperLimit = balanceUpperLimit;
            }

            public double getIncomeRate() {
                return incomeRate;
            }

            public void setIncomeRate(double incomeRate) {
                this.incomeRate = incomeRate;
            }

            public int getTradeTimesLimit() {
                return tradeTimesLimit;
            }

            public void setTradeTimesLimit(int tradeTimesLimit) {
                this.tradeTimesLimit = tradeTimesLimit;
            }

            public double getPriceLowerLimit() {
                return priceLowerLimit;
            }

            public void setPriceLowerLimit(double priceLowerLimit) {
                this.priceLowerLimit = priceLowerLimit;
            }

            public double getPriceUpperLimit() {
                return priceUpperLimit;
            }

            public void setPriceUpperLimit(double priceUpperLimit) {
                this.priceUpperLimit = priceUpperLimit;
            }

            public double getUpgradePercent() {
                return upgradePercent;
            }

            public void setUpgradePercent(double upgradePercent) {
                this.upgradePercent = upgradePercent;
            }

            public double getEquityMargin() {
                return equityMargin;
            }

            public void setEquityMargin(double equityMargin) {
                this.equityMargin = equityMargin;
            }

            public int getWithdrawTimesLimit() {
                return withdrawTimesLimit;
            }

            public void setWithdrawTimesLimit(int withdrawTimesLimit) {
                this.withdrawTimesLimit = withdrawTimesLimit;
            }

            public double getMinimalWithdrawAmount() {
                return minimalWithdrawAmount;
            }

            public void setMinimalWithdrawAmount(double minimalWithdrawAmount) {
                this.minimalWithdrawAmount = minimalWithdrawAmount;
            }
        }
    }
}
