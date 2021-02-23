package com.compy.check.bean;

public class DanInfoBean {
    /**
     * head : {"code":1,"count":1,"message":"Success"}
     * body : {"data":{"id":12,"type":0,"baseDate":"2021-01-15","memberId":7,"level":1,"productId":1,"productName":"Level 1 Product","tradeNumber":1,"tradePrice":7167.11,"tradeAmount":7167.11,"incomeRate":0.006,"tradeIncome":43,"chargeRate":0,"tradeCharge":0,"platformShareIncome":0,"primaryRefereeId":0,"primaryShareIncome":0,"secondaryRefereeId":0,"secondaryShareIncome":0,"recessiveRefereeId":0,"recessiveShareIncome":0,"status":2,"accounted":true,"separated":false,"enabled":true,"summary":"","version":0}}
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
        /**
         * data : {"id":12,"type":0,"baseDate":"2021-01-15","memberId":7,"level":1,"productId":1,"productName":"Level 1 Product","tradeNumber":1,"tradePrice":7167.11,"tradeAmount":7167.11,"incomeRate":0.006,"tradeIncome":43,"chargeRate":0,"tradeCharge":0,"platformShareIncome":0,"primaryRefereeId":0,"primaryShareIncome":0,"secondaryRefereeId":0,"secondaryShareIncome":0,"recessiveRefereeId":0,"recessiveShareIncome":0,"status":2,"accounted":true,"separated":false,"enabled":true,"summary":"","version":0}
         */

        private DataBean data;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 12
             * type : 0
             * baseDate : 2021-01-15
             * memberId : 7
             * level : 1
             * productId : 1
             * productName : Level 1 Product
             * tradeNumber : 1
             * tradePrice : 7167.11
             * tradeAmount : 7167.11
             * incomeRate : 0.006
             * tradeIncome : 43.0
             * chargeRate : 0.0
             * tradeCharge : 0.0
             * platformShareIncome : 0.0
             * primaryRefereeId : 0
             * primaryShareIncome : 0.0
             * secondaryRefereeId : 0
             * secondaryShareIncome : 0.0
             * recessiveRefereeId : 0
             * recessiveShareIncome : 0.0
             * status : 2
             * accounted : true
             * separated : false
             * enabled : true
             * summary :
             * version : 0
             */

            private String id;
            private int type;
            private String baseDate;
            private int memberId;
            private int level;
            private int productId;
            private String productName;
            private int tradeNumber;
            private double tradePrice;
            private double tradeAmount;
            private double incomeRate;
            private double tradeIncome;
            private double chargeRate;
            private double tradeCharge;
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

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getBaseDate() {
                return baseDate;
            }

            public void setBaseDate(String baseDate) {
                this.baseDate = baseDate;
            }

            public int getMemberId() {
                return memberId;
            }

            public void setMemberId(int memberId) {
                this.memberId = memberId;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public int getTradeNumber() {
                return tradeNumber;
            }

            public void setTradeNumber(int tradeNumber) {
                this.tradeNumber = tradeNumber;
            }

            public double getTradePrice() {
                return tradePrice;
            }

            public void setTradePrice(double tradePrice) {
                this.tradePrice = tradePrice;
            }

            public double getTradeAmount() {
                return tradeAmount;
            }

            public void setTradeAmount(double tradeAmount) {
                this.tradeAmount = tradeAmount;
            }

            public double getIncomeRate() {
                return incomeRate;
            }

            public void setIncomeRate(double incomeRate) {
                this.incomeRate = incomeRate;
            }

            public double getTradeIncome() {
                return tradeIncome;
            }

            public void setTradeIncome(double tradeIncome) {
                this.tradeIncome = tradeIncome;
            }

            public double getChargeRate() {
                return chargeRate;
            }

            public void setChargeRate(double chargeRate) {
                this.chargeRate = chargeRate;
            }

            public double getTradeCharge() {
                return tradeCharge;
            }

            public void setTradeCharge(double tradeCharge) {
                this.tradeCharge = tradeCharge;
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
