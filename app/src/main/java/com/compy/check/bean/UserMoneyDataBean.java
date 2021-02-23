package com.compy.check.bean;

public class UserMoneyDataBean {
    /**
     * head : {"code":1,"count":1,"message":"Success"}
     * body : {"data":{"id":7,"name":"flashqin","level":1,"mobile":"123321","shareCode":"Vr1W89E","reward":500,"balance":0,"todayTradeTimes":0,"todayEarnings":0,"yesterdayEarnings":0,"cumulativeEarnings":0,"todayShareAmount":0,"yesterdayShareAmount":0}}
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
         * data : {"id":7,"name":"flashqin","level":1,"mobile":"123321","shareCode":"Vr1W89E","reward":500,"balance":0,"todayTradeTimes":0,"todayEarnings":0,"yesterdayEarnings":0,"cumulativeEarnings":0,"todayShareAmount":0,"yesterdayShareAmount":0}
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
             * id : 7
             * name : flashqin
             * level : 1
             * mobile : 123321
             * shareCode : Vr1W89E
             * reward : 500.0
             * balance : 0.0
             * todayTradeTimes : 0.0
             * todayEarnings : 0.0
             * yesterdayEarnings : 0.0
             * cumulativeEarnings : 0.0
             * todayShareAmount : 0.0
             * yesterdayShareAmount : 0.0
             */

            private int id;
            private String name;
            private int level;
            private String mobile;
            private String shareCode;
            private boolean  bitcoinMember;
            public boolean getBitcoinMember() {
                return bitcoinMember;
            }

            public void setBitcoinMember(boolean bitcoinMember) {
                this.bitcoinMember = bitcoinMember;
            }



            public double getAvailableBalance() {
                return availableBalance;
            }

            public void setAvailableBalance(double availableBalance) {
                this.availableBalance = availableBalance;
            }

            private double availableBalance;
            public String getRegisterDate() {
                return registerDate;
            }

            public void setRegisterDate(String registerDate) {
                this.registerDate = registerDate;
            }

            private String  registerDate;
            private double reward;
            private double balance;
            private double todayTradeTimes;
            private double todayEarnings;
            private double yesterdayEarnings;
            private double cumulativeEarnings;
            private double todayShareAmount;
            private double yesterdayShareAmount;

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

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getShareCode() {
                return shareCode;
            }

            public void setShareCode(String shareCode) {
                this.shareCode = shareCode;
            }

            public double getReward() {
                return reward;
            }

            public void setReward(double reward) {
                this.reward = reward;
            }

            public double getBalance() {
                return balance;
            }

            public void setBalance(double balance) {
                this.balance = balance;
            }

            public double getTodayTradeTimes() {
                return todayTradeTimes;
            }

            public void setTodayTradeTimes(double todayTradeTimes) {
                this.todayTradeTimes = todayTradeTimes;
            }

            public double getTodayEarnings() {
                return todayEarnings;
            }

            public void setTodayEarnings(double todayEarnings) {
                this.todayEarnings = todayEarnings;
            }

            public double getYesterdayEarnings() {
                return yesterdayEarnings;
            }

            public void setYesterdayEarnings(double yesterdayEarnings) {
                this.yesterdayEarnings = yesterdayEarnings;
            }

            public double getCumulativeEarnings() {
                return cumulativeEarnings;
            }

            public void setCumulativeEarnings(double cumulativeEarnings) {
                this.cumulativeEarnings = cumulativeEarnings;
            }

            public double getTodayShareAmount() {
                return todayShareAmount;
            }

            public void setTodayShareAmount(double todayShareAmount) {
                this.todayShareAmount = todayShareAmount;
            }

            public double getYesterdayShareAmount() {
                return yesterdayShareAmount;
            }

            public void setYesterdayShareAmount(double yesterdayShareAmount) {
                this.yesterdayShareAmount = yesterdayShareAmount;
            }
        }
    }
}
