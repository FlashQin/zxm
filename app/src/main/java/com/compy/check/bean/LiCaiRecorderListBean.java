package com.compy.check.bean;

import java.util.List;

public class LiCaiRecorderListBean {
    /**
     * head : {"code":1,"count":1,"message":"Success"}
     * body : {"data":[{"id":2,"memberId":14,"type":0,"contractId":7,"contractDays":7,"interestRate":0.05,"contractAmount":1000,"contractCharge":0,"contractIncome":50,"effectDate":"2021-01-17","expireDate":"2021-01-24","platformShareIncome":0,"primaryRefereeId":0,"primaryShareIncome":0,"secondaryRefereeId":0,"secondaryShareIncome":0,"recessiveRefereeId":0,"recessiveShareIncome":0,"status":0,"accounted":false,"separated":false,"enabled":true,"summary":"","version":0}]}
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
         * count : 1
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
             * id : 2
             * memberId : 14
             * type : 0
             * contractId : 7
             * contractDays : 7
             * interestRate : 0.05
             * contractAmount : 1000.0
             * contractCharge : 0.0
             * contractIncome : 50.0
             * effectDate : 2021-01-17
             * expireDate : 2021-01-24
             * platformShareIncome : 0.0
             * primaryRefereeId : 0
             * primaryShareIncome : 0.0
             * secondaryRefereeId : 0
             * secondaryShareIncome : 0.0
             * recessiveRefereeId : 0
             * recessiveShareIncome : 0.0
             * status : 0
             * accounted : false
             * separated : false
             * enabled : true
             * summary :
             * version : 0
             */

            private int id;
            private int memberId;
            private int type;
            private int contractId;
            private int contractDays;
            private double interestRate;
            private double contractAmount;
            private double contractCharge;
            private double contractIncome;
            private String effectDate;
            private String expireDate;
            private double platformShareIncome;
            private int primaryRefereeId;
            private double primaryShareIncome;
            private int secondaryRefereeId;
            private double secondaryShareIncome;
            private int recessiveRefereeId;
            private double recessiveShareIncome;
            private int status;
            private boolean accounted;
            private boolean separated;
            private boolean enabled;
            private String summary;
            private int version;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getMemberId() {
                return memberId;
            }

            public void setMemberId(int memberId) {
                this.memberId = memberId;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getContractId() {
                return contractId;
            }

            public void setContractId(int contractId) {
                this.contractId = contractId;
            }

            public int getContractDays() {
                return contractDays;
            }

            public void setContractDays(int contractDays) {
                this.contractDays = contractDays;
            }

            public double getInterestRate() {
                return interestRate;
            }

            public void setInterestRate(double interestRate) {
                this.interestRate = interestRate;
            }

            public double getContractAmount() {
                return contractAmount;
            }

            public void setContractAmount(double contractAmount) {
                this.contractAmount = contractAmount;
            }

            public double getContractCharge() {
                return contractCharge;
            }

            public void setContractCharge(double contractCharge) {
                this.contractCharge = contractCharge;
            }

            public double getContractIncome() {
                return contractIncome;
            }

            public void setContractIncome(double contractIncome) {
                this.contractIncome = contractIncome;
            }

            public String getEffectDate() {
                return effectDate;
            }

            public void setEffectDate(String effectDate) {
                this.effectDate = effectDate;
            }

            public String getExpireDate() {
                return expireDate;
            }

            public void setExpireDate(String expireDate) {
                this.expireDate = expireDate;
            }

            public double getPlatformShareIncome() {
                return platformShareIncome;
            }

            public void setPlatformShareIncome(double platformShareIncome) {
                this.platformShareIncome = platformShareIncome;
            }

            public int getPrimaryRefereeId() {
                return primaryRefereeId;
            }

            public void setPrimaryRefereeId(int primaryRefereeId) {
                this.primaryRefereeId = primaryRefereeId;
            }

            public double getPrimaryShareIncome() {
                return primaryShareIncome;
            }

            public void setPrimaryShareIncome(double primaryShareIncome) {
                this.primaryShareIncome = primaryShareIncome;
            }

            public int getSecondaryRefereeId() {
                return secondaryRefereeId;
            }

            public void setSecondaryRefereeId(int secondaryRefereeId) {
                this.secondaryRefereeId = secondaryRefereeId;
            }

            public double getSecondaryShareIncome() {
                return secondaryShareIncome;
            }

            public void setSecondaryShareIncome(double secondaryShareIncome) {
                this.secondaryShareIncome = secondaryShareIncome;
            }

            public int getRecessiveRefereeId() {
                return recessiveRefereeId;
            }

            public void setRecessiveRefereeId(int recessiveRefereeId) {
                this.recessiveRefereeId = recessiveRefereeId;
            }

            public double getRecessiveShareIncome() {
                return recessiveShareIncome;
            }

            public void setRecessiveShareIncome(double recessiveShareIncome) {
                this.recessiveShareIncome = recessiveShareIncome;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public boolean isAccounted() {
                return accounted;
            }

            public void setAccounted(boolean accounted) {
                this.accounted = accounted;
            }

            public boolean isSeparated() {
                return separated;
            }

            public void setSeparated(boolean separated) {
                this.separated = separated;
            }

            public boolean isEnabled() {
                return enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public int getVersion() {
                return version;
            }

            public void setVersion(int version) {
                this.version = version;
            }
        }
    }
}
