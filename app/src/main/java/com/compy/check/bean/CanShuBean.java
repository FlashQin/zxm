package com.compy.check.bean;

public class CanShuBean {
    /**
     * head : {"code":1,"count":1,"message":"Success"}
     * body : {"data":{"appId":1,"payChannel":"964","payoutChannel":"965","taskReward":0,"refereeReward":50,"registerReward":500,"rechargeReward":0,"rechargeRewardRequire":500,"primaryRefereeShareRate":0.12,"secondaryRefereeShareRate":0.08,"recessiveRefereeShareRate":0.05,"minimalRechargeAmount":500,"minimalWithdrawAmount":2000,"expectedShutdownTime":"2025-01-01 00:00:00"}}
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
         * data : {"appId":1,"payChannel":"964","payoutChannel":"965","taskReward":0,"refereeReward":50,"registerReward":500,"rechargeReward":0,"rechargeRewardRequire":500,"primaryRefereeShareRate":0.12,"secondaryRefereeShareRate":0.08,"recessiveRefereeShareRate":0.05,"minimalRechargeAmount":500,"minimalWithdrawAmount":2000,"expectedShutdownTime":"2025-01-01 00:00:00"}
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
             * appId : 1
             * payChannel : 964
             * payoutChannel : 965
             * taskReward : 0.0
             * refereeReward : 50.0
             * registerReward : 500.0
             * rechargeReward : 0.0
             * rechargeRewardRequire : 500.0
             * primaryRefereeShareRate : 0.12
             * secondaryRefereeShareRate : 0.08
             * recessiveRefereeShareRate : 0.05
             * minimalRechargeAmount : 500.0
             * minimalWithdrawAmount : 2000.0
             * expectedShutdownTime : 2025-01-01 00:00:00
             */

            private int appId;
            private String payChannel;
            private String payoutChannel;
            private double taskReward;
            private double refereeReward;
            private double registerReward;
            private double rechargeReward;
            private double rechargeRewardRequire;
            private double primaryRefereeShareRate;
            private double secondaryRefereeShareRate;
            private double recessiveRefereeShareRate;
            private double minimalRechargeAmount;
            private double minimalWithdrawAmount;
            private String expectedShutdownTime;

            public int getAppId() {
                return appId;
            }

            public void setAppId(int appId) {
                this.appId = appId;
            }

            public String getPayChannel() {
                return payChannel;
            }

            public void setPayChannel(String payChannel) {
                this.payChannel = payChannel;
            }

            public String getPayoutChannel() {
                return payoutChannel;
            }

            public void setPayoutChannel(String payoutChannel) {
                this.payoutChannel = payoutChannel;
            }

            public double getTaskReward() {
                return taskReward;
            }

            public void setTaskReward(double taskReward) {
                this.taskReward = taskReward;
            }

            public double getRefereeReward() {
                return refereeReward;
            }

            public void setRefereeReward(double refereeReward) {
                this.refereeReward = refereeReward;
            }

            public double getRegisterReward() {
                return registerReward;
            }

            public void setRegisterReward(double registerReward) {
                this.registerReward = registerReward;
            }

            public double getRechargeReward() {
                return rechargeReward;
            }

            public void setRechargeReward(double rechargeReward) {
                this.rechargeReward = rechargeReward;
            }

            public double getRechargeRewardRequire() {
                return rechargeRewardRequire;
            }

            public void setRechargeRewardRequire(double rechargeRewardRequire) {
                this.rechargeRewardRequire = rechargeRewardRequire;
            }

            public double getPrimaryRefereeShareRate() {
                return primaryRefereeShareRate;
            }

            public void setPrimaryRefereeShareRate(double primaryRefereeShareRate) {
                this.primaryRefereeShareRate = primaryRefereeShareRate;
            }

            public double getSecondaryRefereeShareRate() {
                return secondaryRefereeShareRate;
            }

            public void setSecondaryRefereeShareRate(double secondaryRefereeShareRate) {
                this.secondaryRefereeShareRate = secondaryRefereeShareRate;
            }

            public double getRecessiveRefereeShareRate() {
                return recessiveRefereeShareRate;
            }

            public void setRecessiveRefereeShareRate(double recessiveRefereeShareRate) {
                this.recessiveRefereeShareRate = recessiveRefereeShareRate;
            }

            public double getMinimalRechargeAmount() {
                return minimalRechargeAmount;
            }

            public void setMinimalRechargeAmount(double minimalRechargeAmount) {
                this.minimalRechargeAmount = minimalRechargeAmount;
            }

            public double getMinimalWithdrawAmount() {
                return minimalWithdrawAmount;
            }

            public void setMinimalWithdrawAmount(double minimalWithdrawAmount) {
                this.minimalWithdrawAmount = minimalWithdrawAmount;
            }

            public String getExpectedShutdownTime() {
                return expectedShutdownTime;
            }

            public void setExpectedShutdownTime(String expectedShutdownTime) {
                this.expectedShutdownTime = expectedShutdownTime;
            }
        }
    }
}
